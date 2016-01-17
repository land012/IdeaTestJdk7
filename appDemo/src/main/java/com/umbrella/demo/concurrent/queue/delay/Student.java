package com.umbrella.demo.concurrent.queue.delay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/1/16.
 */
public class Student implements Delayed {

    private static final Logger log = LoggerFactory.getLogger(Student.class);

    private String name;
    private long workDuration;  // 考试时长 s
    private long delayTime; // 到期的时间点 ns

    public Student() {
    }

    public Student(String name, long workDuration, long beginTime) {
        this.name = name;
        this.workDuration = workDuration;
        this.delayTime = TimeUnit.NANOSECONDS.convert(this.workDuration, TimeUnit.SECONDS) + beginTime;
    }

    /**
     * 这个方法 Queue 用来获取还剩多长时间
     * 这个方法会被 Queue 调用两次，每一次判断有效期是多久，第二次判断是否过期
     * 所以每次应该返回一个动态的值
     * @param unit TimeUnit.NANOSECONDS
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long t = this.delayTime-System.nanoTime();
        long res = unit.convert(t, TimeUnit.NANOSECONDS);

//        long t = this.workDuration;
//        long res = unit.convert(t, TimeUnit.SECONDS);
        log.info(name + " delay=" + t + ", res=" + res);
        return res;
    }

    @Override
    public int compareTo(Delayed o) {
        Student s = (Student)o;
        return this.workDuration>s.workDuration ? 1 : (this.workDuration<s.workDuration ? -1 : 0);
    }

    public void process() {
        log.info(this.name + " 交卷，用时 " + this.workDuration + "秒");
    }

    public static class EndExam extends Student {

        public EndExam(long workDuration, long beginTime) {
            super(null, workDuration, beginTime);
        }

        @Override
        public void process() {
            log.info("考试结束");
        }
    }
}
