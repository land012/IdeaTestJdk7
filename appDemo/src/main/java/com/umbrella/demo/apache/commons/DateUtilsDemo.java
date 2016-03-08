package com.umbrella.demo.apache.commons;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xudazhou on 2016/1/12.
 */
public class DateUtilsDemo {

    private static final Logger log = LoggerFactory.getLogger(DateUtilsDemo.class);

    @Test
    public void test1() {
        // Calendar.getInstance() 2016-01-12 15:29
        log.info(DateUtils.getFragmentInMinutes(Calendar.getInstance(), Calendar.HOUR_OF_DAY) + "");  // 29
        log.info(DateUtils.getFragmentInMinutes(Calendar.getInstance(), Calendar.DAY_OF_MONTH) + ""); // 929 = 15*60 + 29
    }

    /**
     * DurationFormatUtils
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Date d1 = DateUtils.parseDate("2016-01-29 14:20:00", "yyyy-MM-dd HH:mm:ss");
        Date d2 = DateUtils.parseDate("2016-01-29 14:24:59", "yyyy-MM-dd HH:mm:ss");
        log.info(DurationFormatUtils.formatDuration(d2.getTime()-d1.getTime(), "H")); // 0
        log.info(DurationFormatUtils.formatDuration(d2.getTime()-d1.getTime(), "m")); // 4
        log.info(DurationFormatUtils.formatDuration(d2.getTime()-d1.getTime(), "s")); // 299
        log.info(DurationFormatUtils.formatPeriod(d1.getTime(), d2.getTime(), "s"));  // 299
        log.info(DurationFormatUtils.formatPeriod(d2.getTime(), d1.getTime(), "s"));  // 2678101
    }

    /**
     * DateUtils 常量
     */
    @Test
    public void test3() {
        log.info(DateUtils.MILLIS_PER_SECOND + ""); // 1000
        log.info(DateUtils.MILLIS_PER_MINUTE + ""); // 60000
        log.info(DateUtils.SEMI_MONTH + ""); // 1001
    }

    /**
     * ceiling
     */
    @Test
    public void test4() throws Exception {
        Date d1 = DateUtils.parseDate("2016-01-31 12:12:12", "yyyy-MM-dd HH:mm:ss");
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1));
        log.info(DateUtils.ceiling(d1, Calendar.MINUTE) + ""); // Sun Jan 31 12:13:00 CST 2016
        log.info(DateUtils.ceiling(d1, Calendar.DATE) + "");   // Mon Feb 01 00:00:00 CST 2016
        log.info(DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH) + "");
    }

    /**
     * truncate
     */
    @Test
    public void test5() {
        Date d1 = new Date();
        log.info(DateUtils.truncate(d1, Calendar.DAY_OF_MONTH) + ""); // Tue Feb 02 00:00:00 CST 2016
        log.info(DateUtils.truncate(d1, Calendar.HOUR_OF_DAY) + "");  // Tue Feb 02 14:00:00 CST 2016
    }

    @Test
    public void test6() {
        Date d1 = new Date();
        log.info(d1+"");                         // Tue Mar 08 15:47:51 CST 2016
        log.info(DateUtils.addDays(d1, 1) + ""); // Wed Mar 09 15:47:51 CST 2016
    }
}
