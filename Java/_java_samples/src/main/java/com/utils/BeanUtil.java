package com.utils;

import com.domain.Policy;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BeanUtil {

    private static Set<String> patterns_datetime = new HashSet();
    private static Set<String> patterns_date = new HashSet();

    static {
        patterns_date.add(DateUtil.DATE_PATTERN_DEFAULT);
        patterns_date.add(DateUtil.DATE_PATTERN_DASH_1);
        patterns_date.add(DateUtil.DATE_PATTERN_DASH_2);
        patterns_datetime.add(DateUtil.DATE_TIME_PATTERN_DEFAULT);
        patterns_datetime.add(DateUtil.DATE_TIME_PATTERN_1);
        patterns_datetime.add(DateUtil.DATE_TIME_PATTERN_2);
    }

    public static Object getProperty(Object bean, String name) {
        try {
            return BeanUtils.getProperty(bean, name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setProperty(Object bean, String name, Object value) {
        try {
            Class propertyType = getPropertyType(bean, name);
            //日期转化的特殊处理
            //beanUtils不能处理String类型的date
            if (propertyType.equals(Date.class) || propertyType.equals(java.sql.Date.class)) {
                Date date = convert(value);
                if(date != null) {
                    BeanUtils.setProperty(bean, name, date.getTime());
                } else {
                    BeanUtils.setProperty(bean, name, value);
                }
            } else {
                BeanUtils.setProperty(bean, name, value);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Class<?> getPropertyType(Object bean, String name) {
        try {
            return PropertyUtils.getPropertyType(bean, name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Date convert(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            Date dateObj = null;
            String dateStr = ObjectUtils.toString(value);
            Iterator it = patterns_date.iterator();
            if(dateStr.length() > 10) {
                it = patterns_datetime.iterator();
            }
            while (it.hasNext()) {
                try {
                    String pattern = (String) it.next();
                    dateObj = string2Date((String)value,pattern,false);
                    break;
                } catch (ParseException ex) {
                    //do iterator continue
                }
            }

            return dateObj;
        } else {
            return null;
        }
    }

    private static Date string2Date(String originalValue, String format, boolean lenient) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(lenient);
        return dateFormat.parse(originalValue);
    }

    public static void main(String[] args) {
        Policy p = new Policy();
        String date = "4/1/2018 12:02:12";
//        String date = "2018-01-01";

        BeanUtil.setProperty(p, "cancellationDate",date);
        System.out.println(DateUtil.date2String(p.getCancellationDate(), "MM/dd/yyyy HH:mm:ss"));
    }

}

