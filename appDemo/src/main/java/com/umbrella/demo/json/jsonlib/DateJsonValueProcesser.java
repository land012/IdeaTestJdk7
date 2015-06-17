package com.umbrella.demo.json.jsonlib;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by 大洲 on 15-4-23.
 */
public class DateJsonValueProcesser implements JsonValueProcessor {

    private static final Logger log = Logger.getLogger(DateJsonValueProcesser.class);

    private DateFormat dateFormat;

    public DateJsonValueProcesser(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        if(o!=null && o instanceof Date) return dateFormat.format(o);
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        log.info("===============================" + s);
        if(o!=null && o instanceof Date) return dateFormat.format(o);
        return null;
    }
}
