package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式不正确，请用 yyyy-MM-dd HH:mm 格式");
        }
    }
}
