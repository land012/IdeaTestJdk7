package com.umbrella.demo.quartz;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 大洲 on 15-5-29.
 * Quartz入门
 */
public class Demo1 {

    private static final Logger log = Logger.getLogger(Demo1.class);

    public static void main(String[] args) {
        log.info("I am main begin");
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();

            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("hellojob", "jgroup1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("hellotrigger", "tgroup1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever()) // 不加这句，只执行一次
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.info("", e);
        }
    }

    public static class HelloJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            log.info("I am HelloJob begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I am HelloJob end");
        }
    }
}
