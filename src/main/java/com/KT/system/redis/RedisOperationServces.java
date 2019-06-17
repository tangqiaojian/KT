package com.KT.system.redis;

/**
 * @author tqj
 * @Description
 * @create 2019/6/12 18:51
 **/
public interface RedisOperationServces {

    /**
     * key value 储存策略
     * key: 键名
     * value: 键值
     */
    void set(String key, Object value);

    /**
     * key value 储存策略
     * key: 键名
     * value: 键值
     * time :存储时间
     */
    void setTime(String key, Object value, Long time);

    /**
     * sort set 有序集合储存策略
     * key: 键名
     * value: 键值
     * sorttype:排序类型
     */

    Object zset(String key, Object value, int sorttype);

    /**
     * sort set 有序集合获取
     * key: 键名
     */
    Object zget(String key);

    /**
     * key  获取
     * key: 键名
     */
    Object get(String key);

    /**
     * 清空指定redis缓存
     */
    void clean(String cacheName);

}
