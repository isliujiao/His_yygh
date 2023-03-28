package com.atguigu.yygh.order.service;

import com.atguigu.yygh.model.order.OrderInfo;
import com.atguigu.yygh.vo.order.OrderQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author:厚积薄发
 * @create:2022-08-11-12:48
 */
public interface OrderService extends IService<OrderInfo> {
    //根据排班id和就诊人id生成订单
    Long saveOrder(String scheduleId, Long patientId);

    //根据订单di查询订单详情
    OrderInfo getOrder(String orderId);

    //订单列表（条件查询带分页）
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    Map<String,Object> show(Long id);

    //取消预约
    Boolean cancelOrder(Long orderId);
}
