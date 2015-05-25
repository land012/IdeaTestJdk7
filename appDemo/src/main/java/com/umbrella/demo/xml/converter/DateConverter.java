package com.umbrella.demo.xml.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 大洲 on 15-4-22.
 */
public class DateConverter implements Converter {

    private Locale locale;

    public DateConverter(Locale locale) {
        this.locale = locale;
    }

    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        Date d = (Date)o;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hierarchicalStreamWriter.setValue(df.format(d));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return null;
    }

    @Override
    public boolean canConvert(Class aClass) {
        return Date.class.isAssignableFrom(aClass);
    }
}
