package com.KT.system.rabbitMQ.QueueDemo;

import com.KT.system.rabbitMQ.queuProduct.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tqj
 * @Description
 * @create 2019/6/17 11:14
 **/
public class demo {
    @Autowired
    Product product;
    public void QueueTest(){
        product.setRoutingKey("key名称");
        //发送消息
        product.sendDataToQueue("队列消息内容");
    }
}
