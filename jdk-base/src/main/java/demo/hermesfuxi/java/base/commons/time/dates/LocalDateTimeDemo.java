package demo.hermesfuxi.java.base.commons.time.dates;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

/**
 * Instant <-- Date，
 * LocalDateTime <-- Calendar，
 * DateTimeFormatter <-- SimpleDateFormat
 * <p>
 * LocalDateTime：获取年月日时分秒等于LocalDate+LocalTime
 * LocalDateTime 与 时间戳 互转
 * LocalDateTime 与 Date 互转
 * LocalDateTime 与 字符串 互转
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        // 创建、格式化、解析、计算、修改
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime); // 2021-01-03T15:02:57.094667200

        // 时间戳 转 localDataTime  在中国的时区偏移是8小时
        Long remindTime = 1534825831L;
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(remindTime, 0, ZoneOffset.ofHours(8));

        // localDataTime 转 时间戳
        long secondLong1 = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        long secondLong2 = localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();

        System.out.println("时间戳1: " + secondLong1);
        System.out.println("时间戳2: " + secondLong2);

        LocalDateTime dateTime1 = LocalDateTime.parse("2021-03-06 09:30:35", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime1.toEpochSecond(ZoneOffset.ofHours(8)) * 1000);


        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        System.out.println(localDate); // 2021-01-21
        System.out.println(LocalDate.now()); // 2021-01-21

        System.out.println(localTime); // 23:36:58.351092500
        System.out.println(LocalTime.now()); // 23:36:58.352094500

        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        System.out.println(localDate1); // 2019-09-10

        // --------- 查询获取 -----------
        // LocalDate 获取年、月、日、星期几
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);

        // LocalTime获取时分秒
        //获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        //获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        //获取秒
        int second1 = localTime.getMinute();
        int second2 = localTime.get(ChronoField.SECOND_OF_MINUTE);

        // LocalDateTime 获取年月日时分秒，等于LocalDate+LocalTime
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);

        Instant instant = Instant.now();
        System.out.println(instant);
        // 获取秒数
        long currentSecond = instant.getEpochSecond();
        // 获取毫秒数
        long currentMilli = instant.toEpochMilli();

        long timeMillis = System.currentTimeMillis();
        // 纳米时间
        long nanoTime = System.nanoTime();
        System.out.println(nanoTime);

        instant = Instant.parse("1995-10-23T10:12:35Z");

        // --------- 修改 -----------
        // LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本

        LocalDateTime time = LocalDateTime.of(2019, Month.SEPTEMBER, 10,
                14, 46, 56);
        //增加一年
        time = time.plusYears(1);
        time = time.plus(1, ChronoUnit.YEARS);
        //减少一个月
        time = time.minusMonths(1);
        time = time.minus(1, ChronoUnit.MONTHS);

        // 通过with修改某些值
        time = time.withYear(2020);
        time = time.with(ChronoField.YEAR, 2022);

        // 时间计算
        System.out.println(localDate.with(firstDayOfYear()));

        // 格式化时间
        String s1 = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        //自定义格式化
        String s2 = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String s3 = time.format(dateTimeFormatter);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // 解析时间
        LocalDate localDate3 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate localDate4 = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);


        Instant instant1 = instant.plus(Duration.ofHours(5).plusMinutes(4));
        //计算5天前的时间
        instant.minus(5, ChronoUnit.DAYS); // Option 1　方法1
        instant.minus(Duration.ofDays(5)); // Option 2  方法2
        //计算两个Instant之间的分钟数
        long diffAsMinutes2 = ChronoUnit.MINUTES.between(instant, instant1);
    }
}
