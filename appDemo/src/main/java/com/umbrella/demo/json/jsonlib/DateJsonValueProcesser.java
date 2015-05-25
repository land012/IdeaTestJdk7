package com.umbrella.demo.json.jsonlib;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by 大洲 on 15-4-23.
 */
public class DateJsonValueProcesser implements JsonValueProcessor {
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
        System.out.println("===============================" + s);
        if(o!=null && o instanceof Date) return dateFormat.format(o);
        return null;
    }
}
