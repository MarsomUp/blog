package com.immyc.blog.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 统一处理日期
 * @Author:mayc
 * @Date:2019/1/16 22:48
 */
public class DateConverterConfig implements Converter<String, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateConverterConfig.class);

    private static final List<String> formaters = new ArrayList<>(4);
    static {
        formaters.add("yyyy-MM");
        formaters.add("yyyy-MM-dd");
        formaters.add("yyyy-MM-dd HH:mm");
        formaters.add("yyyy-MM-dd HH:mm:ss");
    }
    @Nullable
    @Override
    public Date convert(String s) {
        String value = s.trim();
        if ("".equals(value)) return null;
        if (s.matches("^\\\\d{4}-\\\\d{1,2}$")) {
            return parseDate(s, formaters.get(0));
        } else if (s.matches("^\\\\d{4}-\\\\d{1,2}-\\\\d{1,2}$")) {
            return parseDate(s, formaters.get(1));
        } else if (s.matches("^\\\\d{4}-\\\\d{1,2}-\\\\d{1,2} {1}\\\\d{1,2}:\\\\d{1,2}$")) {
            return parseDate(s, formaters.get(2));
        } else if (s.matches("^\\\\d{4}-\\\\d{1,2}-\\\\d{1,2} {1}\\\\d{1,2}:\\\\d{1,2}:\\\\d{1,2}$")){
            return parseDate(s, formaters.get(3));
        } else {
            throw new IllegalArgumentException("Invalid date value, value is:"+s);
        }
    }

    public Date parseDate(String dateStr, String format) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error("日期转换出现异常！", e);
        }
        return date;
    }
}
