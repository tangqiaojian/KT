package rabbitMQ;

import com.KT.system.rabbitMQ.queuProduct.Product;
import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author tqj
 * @Description
 * @create 2019/6/14 16:29
 **/
public class MQtest {

    private Logger logger = LoggerFactory.getLogger(MQtest.class);

    private ApplicationContext context = null;




    //生产者
    @Test
    public void demo1() throws IOException {
        Connection connection = Connections.getConnection();
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare("test.queue",false,false,false,null);
        channel.basicPublish("","test.queue",null,"我真帅啊".getBytes());
        channel.close();
        connection.close();
    }

    //消费者
    @Test
    public void demo2() throws IOException, InterruptedException {
        Connection connection = Connections.getConnection();
        Channel channel = connection.createChannel();

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        channel.basicConsume("tqj_mq_queue",true,queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();

            String s = new String(delivery.getBody());

            System.out.println(s);
        }
    }

    @Test
    @SuppressWarnings("degrecation")
    public void demo3() throws IOException {
        Connection connection = Connections.getConnection();
        Channel channel = connection.createChannel();
        //创建队列声明
        channel.queueDeclare("test.queue",false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            public void handleDlivery(String a,Envelope envelope,BasicProperties basicProperties,byte[] bytes)throws IOException{

            }
        };

        channel.basicPublish("","test.queue",null,"我真帅啊".getBytes());
        channel.close();
        connection.close();
    }

}
