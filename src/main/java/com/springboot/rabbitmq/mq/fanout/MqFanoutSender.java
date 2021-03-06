package com.springboot.rabbitmq.mq.fanout;

import com.springboot.rabbitmq.common.constant.RabbitMqConstant;
import com.springboot.rabbitmq.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqFanoutSender {
    private static final Logger logger = LoggerFactory.getLogger(MqFanoutSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(SysUser user){
        logger.info("------------发送队列消息usermsg：{}",user.toString());
        //交换机接收到消息后，就根据消息的key和已经设置的binding，进行消息路由，将消息投递到一个或多个队列里。
        rabbitTemplate.convertAndSend(RabbitMqConstant.FANOUT_EXCHANGE,RabbitMqConstant.FANOUT_QUEUE,user.toString());
    }
}
