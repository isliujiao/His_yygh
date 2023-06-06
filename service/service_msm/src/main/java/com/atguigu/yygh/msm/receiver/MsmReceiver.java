package com.atguigu.yygh.msm.receiver;

import com.atguigu.yygh.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:厚积薄发
 * @create:2022-08-11-16:18
 */
@Component
public class MsmReceiver {

    @Autowired
    private MsmService msmService;

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = MqConst.QUEUE_MSM_ITEM, durable = "true"),
//            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_MSM),
//            key = {MqConst.ROUTING_MSM_ITEM}
//    ))
//    public void send(MsmVo msmVo, Message message, Channel channel) {
//        msmService.send(msmVo);
//    }

}
