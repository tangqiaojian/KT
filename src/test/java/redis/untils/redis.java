package redis.untils;

import jdk.nashorn.internal.objects.annotations.Property;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tqj
 * @Description
 * @create 2019/6/12 15:48
 **/
public class redis {
    private  static JedisPool pool = null;

    static {
//        //加载配置文件
//        InputStream inputStream = redis.class.getClassLoader().getResourceAsStream("配置文件名称");
//        Properties properties = new Properties();
//        try {
//            properties.load(inputStream);
//            System.out.println(properties.get("名称"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //创建配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //创建redis连接池
        pool = new JedisPool(jedisPoolConfig,"localhost",6379);
    }

    public static Jedis getJedis(){

        return pool.getResource();
    }

}
