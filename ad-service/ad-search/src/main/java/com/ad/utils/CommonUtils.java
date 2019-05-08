package com.ad.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * created by Mr.F on 2019/5/4
 */
@Slf4j
public class CommonUtils {
    /**
     * 当传进来的key不存在的时候，我们就不能直接通过map得到对象，而是通过factory来获取一个新对象V
     * @param key
     * @param map
     * @param factory
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V getorCreate(K key, Map<K, V> map,
                                       Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args) {

        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    // Tue Jan 01 08:00:00 CST 2019
    public static Date parseStringDate(String dateString) {

        try {

            DateFormat dateFormat = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss zzz yyyy",
                    Locale.US
            );
            return DateUtils.addHours(
                    dateFormat.parse(dateString),
                    -8
            );

        } catch (ParseException ex) {
            log.error("parseStringDate error: {}", dateString);
            return null;
        }
    }
}
