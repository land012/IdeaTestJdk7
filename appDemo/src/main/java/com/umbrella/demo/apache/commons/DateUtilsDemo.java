package com.umbrella.demo.apache.commons;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

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
}
