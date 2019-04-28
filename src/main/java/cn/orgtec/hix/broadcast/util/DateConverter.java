package cn.orgtec.hix.broadcast.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * <p>DateConverter.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/02 <p>
 * <p>@remark:</p>
 */
@UtilityClass
public class DateConverter {

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime localDateTime
     * @return Date
     */
    public Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     *  String 转换 LocalDateTime
     * @param str   2019-03-29 14:03:29.0
     * @return      LocalDateTime
     */
    public LocalDateTime str2LocalDateTime(String str){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strSub = str.substring(0, str.indexOf('.') );
        LocalDateTime ldt = LocalDateTime.parse(strSub,df);
        return ldt;
    }



    public static void main(String[] args) {
        DateTime dateTime = DateUtil.offsetMinute(localDateTime2Date(LocalDateTime.now()), 10);
        System.out.println(dateTime);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime t = LocalDateTime.now().plusMinutes(3);
        Long between = ChronoUnit.MILLIS.between(now, t);
        int i = between.intValue();
        System.out.println(between);
        System.out.println(i);
        Integer integer = Convert.toInt(between);
        System.out.println(integer);
        String ss = "2019-03-29 14:03:29.0";
        LocalDateTime localDateTime = str2LocalDateTime(ss);
        System.out.println(localDateTime);
    }
}
