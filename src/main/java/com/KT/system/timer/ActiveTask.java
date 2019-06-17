package com.KT.system.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author tqj
 * @Description 动态修改定时器执行任务时间
 * @create 2019/6/14 10:59
 **/
@Lazy(false)
public class ActiveTask  implements SchedulingConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ActiveTask.class);

    private static String cron;

    public ActiveTask() {
        cron = "0/5 * * * * ?";
        // 开启新线程模拟外部更改了任务执行周期
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cron = "0/10 * * * * ?";
                System.err.println("cron change to: " + cron);
            }
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // 任务逻辑
                logger.debug("dynamicCronTask is running...");
                System.out.println(11111);
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，可修改任务的执行周期
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                System.out.println(nextExec);
                return nextExec;
            }
        });
    }
}
