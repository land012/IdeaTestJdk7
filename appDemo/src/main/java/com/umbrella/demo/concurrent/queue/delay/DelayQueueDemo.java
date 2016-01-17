package com.umbrella.demo.concurrent.queue.delay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * Created by xudazhou on 2016/1/16.
 * 模拟考试 3秒后交卷，考试时间为 12秒
 */
public class DelayQueueDemo {

    private static final Logger log = LoggerFactory.getLogger(DelayQueueDemo.class);

    private static final int STUDENT_NUM = 5;

    public static void main(String[] args) throws Exception {
        DelayQueue<Student> students = new DelayQueue<>();
        Random r = new Random();
        long beginTime = System.nanoTime();
        for(int i=0; i<STUDENT_NUM; i++) {
            long k = 3 + r.nextInt(10);
            students.put(new Student("student" + i, k, beginTime));
        }
        students.put(new Student.EndExam(12, beginTime));
        log.info("考试开始...");
        while (!students.isEmpty()) {
            Student s = students.take();
            s.process();
        }
        log.info("考试结束...");
    }
}
