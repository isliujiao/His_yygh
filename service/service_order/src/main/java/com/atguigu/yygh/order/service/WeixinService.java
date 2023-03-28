package com.atguigu.yygh.order.service;

import java.util.Map;

/**
 * @author:厚积薄发
 * @create:2022-08-13-21:48
 */
public interface WeixinService {
    //下单生成二维码
    Map<String,Object> createNative(Long orderId);

    //查询支付状态
    Map<String, String> queryPayStatus(Long orderId, String name);

    //退款
    Boolean refund(Long orderId);
}
