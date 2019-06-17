package com.KT.system.rabbitMQ.queuProduct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author tqj
 * @Description
 * @create 2019/6/14 14:43
 **/
/*
@Service
*/
public class Product  {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    private String routingKey;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void sendDataToQueue(Object obj) {
        amqpTemplate.convertAndSend(this.routingKey, obj);
    }

    public void sendDataToQueue2(Object obj) {
        rabbitTemplate.convertAndSend(this.routingKey, obj);
    }


}
