package com.umbrella.demo.quartz;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 大洲 on 15-5-29.
 * Cron
 * 任务执行时长大于执行间隔
 * 任务会再指定的间隔执行，不管上一次任务是否结束
 */
public class QuartzDemo3 {

    private static final Logger log = Logger.getLogger(QuartzDemo3.class);

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
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")) // 不加这句，只执行一次
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.info("", e);
        }
    }

    public static class HelloJob implements Job {

        private static long count = 1;

        private long id;

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            id = count;
            count++;
            log.info(String.format("I am HelloJob %s begin", id));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("I am HelloJob %s end", id));
        }
    }
}
