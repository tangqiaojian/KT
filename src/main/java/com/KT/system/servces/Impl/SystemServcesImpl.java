package com.KT.system.servces.Impl;

import com.KT.system.mapper.SystemMapper;
import com.KT.system.redis.RedisOperationServces;
import com.KT.system.servces.SystemServces;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author tqj
 * @Description
 * @create 2019/6/13 14:27
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemServcesImpl implements SystemServces {
    @Autowired
    SystemMapper systemMapper;
    @Autowired
    RedisOperationServces redisOperationServces;

    public List<Map<String,Object>> selectMeu() throws Exception{
        //分页
       // PageHelper.startPage(1, 10);
        JSONArray result;
        Object ts_log = redisOperationServces.get("t_s_log");
        if (ts_log==null){
        result = JSONArray.fromObject(systemMapper.selectLog());
        redisOperationServces.set("t_s_log",result);
        }else {
            result = JSONArray.fromObject(ts_log);
        }
        return result;
    }




}
