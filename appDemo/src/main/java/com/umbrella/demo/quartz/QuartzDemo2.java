package com.umbrella.demo.quartz;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 大洲 on 15-5-29.
 * Cron
 * 每次执行都会new 一个新的 Job
 */
public class QuartzDemo2 {

    private static final Logger log = Logger.getLogger(QuartzDemo2.class);

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

        private static long i = 1;

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            log.info(String.format("I am HelloJob %s begin", i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("I am HelloJob %s end", i));
            i++;
        }
    }
}
