package com.atguigu.yygh.msm.service;

import com.atguigu.yygh.vo.msm.MsmVo;

/**
 * @author:厚积薄发
 * @create:2022-07-31-12:40
 */
public interface MsmService {
    //发送手机验证码
    boolean send(String phone, String code);

    //发送邮箱验证码
    void sendEmail(String email, String code);

    //MQ使用发送短信
    boolean send(MsmVo msmVo);

}
