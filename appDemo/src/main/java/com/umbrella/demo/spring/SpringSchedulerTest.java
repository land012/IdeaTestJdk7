package com.umbrella.demo.spring;

import com.umbrella.demo.spring.factory.UserFactoryBean;
import com.umbrella.vo.User;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 定时任务 Quartz
 * Created by xudazhou on 2015/10/14.
 */
public class SpringSchedulerTest {

    private ApplicationContext context;

//    @Autowired
//    private MethodInvokingJobDetailFactoryBean jobDetailFactoryBean;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    /**
     * Spring 定时任务
     * @throws InterruptedException
     */
    @Test
    public void test4_1() throws Exception {
        Object obj1 = context.getBean("helloWorldJobDetail");
        System.out.println(obj1 instanceof JobDetail); // true
        System.out.println(obj1 instanceof MethodInvokingJobDetailFactoryBean); // false
        new CountDownLatch(1).await();
    }

    /**
     * Spring 执行任务
     * 根据 JobDetail 获取 MethodInvokingJobDetailFactoryBean
     * @throws Exception
     */
    @Test
    public void test4_2() throws Exception {
        System.out.println("======== test4_2 begin =========================================================");
        Object obj1 = context.getBean("helloWorldJobDetail");
        JobDetail jobDetail1 = (JobDetail)obj1;
        System.out.println(jobDetail1);
        System.out.println(jobDetail1.getKey());
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean1 = (MethodInvokingJobDetailFactoryBean)jobDetail1.getJobDataMap().get("methodInvoker");
        Object targetObj1 = jobDetailFactoryBean1.getTargetObject();
        Method method1 = jobDetailFactoryBean1.getPreparedMethod();
        method1.invoke(targetObj1);
        System.out.println("======== test4_2 end =========================================================");
    }

    /**
     * Spring 执行任务
     * 直接拿到 MethodInvokingJobDetailFactoryBean
     * @throws Exception
     */
    @Test
    public void test4_3() throws Exception {
        System.out.println("========= test4_3 begin ========================================");
        // 按类型注入,当有多个 MethodInvokingJobDetailFactoryBean 时会有问题
//        Method method1 = this.jobDetailFactoryBean.getPreparedMethod();
//        Object obj1 = this.jobDetailFactoryBean.getTargetObject();
//        method1.invoke(obj1);

        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean1 = (MethodInvokingJobDetailFactoryBean)context.getBean("&helloWorldJobDetail");
        Method method2 = jobDetailFactoryBean1.getPreparedMethod();
        Object obj2 = jobDetailFactoryBean1.getTargetObject();
        method2.invoke(obj2);

        System.out.println("========= test4_3 end ========================================");
    }

    /**
     * Spring 执行任务
     * 返回所有 MethodInvokingJobDetailFactoryBean 类的实例
     * @throws Exception
     */
    @Test
    public void test4_4() throws Exception {
        System.out.println("========= test4_4 begin ========================================");
        Map<String, MethodInvokingJobDetailFactoryBean> map = context.getBeansOfType(MethodInvokingJobDetailFactoryBean.class);
        System.out.println(map);
        System.out.println("========= test4_4 end ========================================");
    }

    /**
     * 实现 FactoryBean 的工厂
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        Object obj2 = context.getBean("user3");
        System.out.println(obj2 instanceof User); // true
        System.out.println(obj2 instanceof UserFactoryBean); // false
        new CountDownLatch(1).await();
    }

    @Test
    public void test7() throws Exception {
        System.out.println("============== test7 begin ==========================================");
        Object obj1 = context.getBean("helloWorldScheduler");
//        System.out.println(obj1 instanceof Scheduler);
        Scheduler scheduler1 = (Scheduler)obj1;

        Object obj2 = context.getBean("helloWorldJobDetail");
        JobDetail jobDetail1 = (JobDetail)obj2;

        scheduler1.start();
        // 在 scheduler start的状态下，立即触发 job
        scheduler1.triggerJob(jobDetail1.getKey());

        // shutdown=true后，start仍然是true，但 scheduler不能再次被start
        scheduler1.shutdown();

        System.out.println("============== test7 end ==========================================");
        new CountDownLatch(1).await();
    }

    /**
     * Groboutils 也解决不了这种多线程的情况，仍然会提前退出
     * @throws Throwable
     */
    @Test
    public void test8() throws Throwable {
        TestRunnable[] trArr = new TestRunnable[1];
        trArr[0] = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                Object obj1 = context.getBean("helloWorldScheduler");
                Scheduler scheduler1 = (Scheduler)obj1;

                Object obj2 = context.getBean("helloWorldJobDetail");
                JobDetail jobDetail1 = (JobDetail)obj2;

                scheduler1.start();
                /*
                 * 在 scheduler start=true 状态下，立即触发 job
                 * 在 start=true 且 standby=true 状态下，不会触发job
                 */
                scheduler1.triggerJob(jobDetail1.getKey());
            }
        };
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trArr);
        mttr.runTestRunnables();
    }
}
