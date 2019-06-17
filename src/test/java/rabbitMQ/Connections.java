package rabbitMQ;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author tqj
 * @Description
 * @create 2019/6/14 16:23
 **/
public class Connections {
    public static Connection getConnection () throws IOException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/host");
        factory.setUsername("user");
        factory.setPassword("123456");

        return factory.newConnection();
    }

}
