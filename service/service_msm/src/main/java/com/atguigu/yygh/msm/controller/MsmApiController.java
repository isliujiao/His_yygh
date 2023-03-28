package com.atguigu.yygh.msm.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.msm.service.MsmService;
import com.atguigu.yygh.msm.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author:厚积薄发
 * @create:2022-07-31-12:40
 */
@RestController
@RequestMapping("/api/msm")
@Slf4j
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送手机验证码
    @ApiOperation("发送手机短信验证码")
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {

        //1.从redis获取验证码，如果可以获取到，返回ok
        // key 手机号  value 验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            log.info("redis缓存验证码：" + code);
            return Result.ok();
        }

        //2.如果从redis获取不到，生成验证码，
        code = RandomUtil.getSixBitRandom();
        log.info(code);

        //3.调用service方法，通过整合短信服务进行发送
        boolean isSend = msmService.send(phone,code);

        //4.生成验证码放到redis里面，设置有效时间
        if(isSend) {
            redisTemplate.opsForValue().set(phone,code,20000, TimeUnit.MINUTES);//超时时间2->
            return Result.ok();
        } else {
            return Result.fail().message("发送短信失败?");
        }
    }

    //发送邮箱验证码
    @ApiOperation("发送邮箱验证码")
    @GetMapping("sendEmailCode/{email}")
    public Result sendEmailCode(@PathVariable String email) {

        //1.从redis获取验证码，如果可以获取到，返回ok
        // key 邮箱  value 验证码
        String code = (String) redisTemplate.opsForValue().get(email);
        if(!StringUtils.isEmpty(code)) {
            log.info("redis缓存验证码：" + code);
            return Result.ok();
        }

        //2.如果从redis获取不到，生成验证码，
        code = RandomUtil.getSixBitRandom();
        log.info(code);
        msmService.sendEmail(email,code);
        redisTemplate.opsForValue().set(email,code,5*60*5*30,TimeUnit.MINUTES);
        return Result.ok();
    }

}
