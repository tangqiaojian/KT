package com.KT.system.redis;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author tqj
 * @Description redis缓存操作
 * @create 2019/6/12 18:06
 **/
public class RedisOperationServcesImpl implements RedisOperationServces {
    private static final Logger log = LoggerFactory.getLogger(RedisOperationServcesImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;


    //默认一天保存时间
    private long timeStart = 1l;

    /**
     * key value 储存策略
     * key: 键名
     * value: 键值
     */
    public void set(String key,Object value){
        log.debug("从redis存储"+key);
            redisTemplate.boundValueOps(key).set(serialize(value));
    }

    /**
     * key value 储存策略
     * key: 键名
     * value: 键值
     * time :存储时间
     */
    public void setTime(String key,Object value,Long time){
        log.debug("从redis存储"+key);
        if (time!=null){
            timeStart = time;
        }
        redisTemplate.boundValueOps(key).set(serialize(value),timeStart, TimeUnit.DAYS);
    }
    /**
     * key  获取
     * key: 键名
     */
    public Object get(String key){
        log.debug("从redis缓存中获取"+key);
        Object obj = redisTemplate.boundValueOps(key).get();
        //判断值是否为空或者是byte类型
        if(obj!=null){
            obj = unserizlize((byte [])obj);
            return obj;
        }
        return  null;
    }
    /**
     * sort set 有序集合储存策略
     * key: 键名
     * value: 键值
     * sorttype:排序类型
     */
    public Object zset(String key,Object value,int sorttype){
        Object obj =  redisTemplate.opsForZSet().add(key,serialize(value),sorttype);
        return obj;
    }

    /**
     * sort set 有序集合获取
     * key: 键名
     */
    public Object zget(String key){
        Object obj =  redisTemplate.opsForZSet().zCard(key);
        return obj;
    }


    /**
     * 清空指定redis缓存
     */
    public void clean(String cacheName){
        Set dictKeys = redisTemplate.keys(cacheName+"*");
        if(StringUtils.isNotEmpty(cacheName)){redisTemplate.delete(dictKeys);}
    }


    //序列化
    private static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化
    private static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

}
