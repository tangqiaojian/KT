package com.KT.system.rabbitMQ.queueConsumers;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * @author tqj
 * @Description
 * @create 2019/6/14 14:44
 **/
public class Consumers implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(Consumers.class);

    @Override
    public void onMessage(Message message) {
      //  createOrder();
        logger.info("receive message:{}",message);
        String messages = null;
        try {
            messages = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] str=messages.split(",");
        System.out.println("消费了："+str);
    }


}
