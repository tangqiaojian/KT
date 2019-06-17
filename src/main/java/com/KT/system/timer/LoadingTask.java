package com.KT.system.timer;

import com.KT.system.servces.SystemServces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;

/**
 * @author tqj
 * @Description 启动时加载的定时器
 * @create 2019/6/14 10:39
 **/
public class LoadingTask {

    @Autowired
    SystemServces systemServces;

    public void loadTask() throws Exception {
        getCache();
    }

    @Scheduled(cron = "*/5 * * * * ?")
    public void getCache() throws Exception {
        System.out.println("我5秒执行一次");
    }
}
