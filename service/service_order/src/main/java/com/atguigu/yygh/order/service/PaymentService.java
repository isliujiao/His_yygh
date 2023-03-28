package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.atguigu.yygh.model.order.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author:厚积薄发
 * @create:2022-08-13-22:04
 */
public interface PaymentService extends IService<PaymentInfo> {
    //向支付记录表添加信息
    void savePaymentInfo(OrderInfo order, Integer status);

    //更新订单状态
    void paySuccess(String out_trade_no, Map<String, String> resultMap);

    //获取支付记录
    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);

}
