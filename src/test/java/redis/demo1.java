package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static redis.untils.redis.getJedis;

/**
 * @author tqj
 * @Description
 * @create 2019/6/12 15:32
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis.xml")
public class demo1 {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存入值
     */
    @Test
    public void setValue(){
        redisTemplate.boundSetOps("nameset").add("曹操");
        redisTemplate.boundSetOps("nameset").add("刘备");
        redisTemplate.boundSetOps("nameset").add("孙权");
    }

    /**
     * 提取值
     */
    @Test
    public void getValue(){
        Set members = redisTemplate.boundSetOps("nameset").members();
        System.out.println(members);
    }


    @Test
    public void demo1() throws IOException, ClassNotFoundException {
        Jedis jedis = new Jedis("localhost",6379);

        //读取
        byte[] personByte = jedis.get("redis1".getBytes());
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        //转换成输入字节流
        bis = new ByteArrayInputStream(personByte);
        oii = new ObjectInputStream(bis);
        Map<String, String> result = (Map<String, String>) oii.readObject();
        System.out.println(result.toString());
    }

    @Test
    public void demo2() throws IOException {

        //从池子中获取redis连接资源
        Jedis jedis = getJedis();
        Map<String, String> as = new HashMap<String, String>();
        as.put("a","b");
        as.put("a1","b");
        as.put("a2","b");
        as.put("a3","b");
        as.put("a4","b");

        //反序列化
        ByteArrayOutputStream bai = new ByteArrayOutputStream();
        ObjectOutputStream obi = new ObjectOutputStream(bai);
        obi.writeObject(as);
        byte[] byt = bai.toByteArray();

        //操作数据库
        jedis.set("redis1".getBytes(),byt);

//        //关闭数据库
//        jedisPool.close();

    }

    @Test
    public void demo3() throws IOException {
        //从池子中获取redis连接资源
        Jedis jedis = getJedis();
        jedis.setex("user:id:1:username:tqj:username",60*60*24*30*12*12,"tqj");
        jedis.setex("user:id:1:username:tqj:age",10,"21");
        jedis.setex("user:id:2:username:tt:username",10,"tt");
        jedis.setex("user:id:2:username:tt:age",10,"17");

     /* jedis.keys("user:id:1*");
        System.out.println(jedis.keys("*age"));
        Set<String> set = jedis.keys("*age");
        for (String key :set){
            System.out.println( jedis.get(key));
        }*/


    }

}
