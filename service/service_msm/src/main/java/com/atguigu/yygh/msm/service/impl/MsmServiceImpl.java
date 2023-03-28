package com.atguigu.yygh.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.yygh.msm.service.MsmService;
import com.atguigu.yygh.msm.utils.ConstantPropertiesUtils;
import com.atguigu.yygh.vo.msm.MsmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:厚积薄发
 * @create:2022-07-31-12:41
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    JavaMailSender javaMailSender;

    ConstantPropertiesUtils constantPropertiesUtils;

    //===发送手机验证码===
    @Override
    public boolean send(String phone, String code) {
        //1.判断手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        //2.整合阿里云短信服务，设置相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantPropertiesUtils.REGION_Id,
                        ConstantPropertiesUtils.ACCESS_KEY_ID,
                        ConstantPropertiesUtils.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //TODO 签名名称
        request.putQueryParameter("SignName", "阿里云短信测试");
        //TODO 模板code
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        request.putQueryParameter("RegionId", "cn-shandong");
        //验证码  使用json格式   {"code":"123456"}
        Map<String, Object> param = new HashMap();
        param.put("code", code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
    //===发送邮箱验证码===
    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("尚医通项目登录验证码");
        simpleMailMessage.setText("尊敬的:" + email + "您的注册校验验证码为: " + code + " 有效期5分钟");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("741807366@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    //MQ使用发送短信
    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())){
            boolean isSend = this.send(msmVo.getPhone(), msmVo.getParam());
            System.out.println("isSend：" + isSend);
            System.out.println("电话：" + msmVo.getPhone());
            return isSend;
        }
        return false;
    }
    //发送短信？
    private boolean send(String phone, Map<String,Object> param) {
        //1.判断手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            return false;
        }

        //2.整合阿里云短信服务，设置相关参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantPropertiesUtils.REGION_Id,
                        ConstantPropertiesUtils.ACCESS_KEY_ID,
                        ConstantPropertiesUtils.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //TODO 签名名称
        request.putQueryParameter("SignName", "阿里云短信测试");
        //TODO 模板code
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        request.putQueryParameter("RegionId", "cn-shandong");

        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
