package com.lc.shoppingmall.teat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * @author 刘晨
 * @description
 * @create 2021/6/15 0015
 * @since 1.0.0
 */
public class TestMain {
    public static void main(String[] args) {

        /**
         * 输入一个字符串，得到不连续的字符串长度
         * 例如字符串为 abcda，则输出4，因为abcd长度为4
         */
        String s = "abcc";
        int[] last = new int[128];
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            //charAt(i)返回指定索引处的字符
            int index = s.charAt(i);
            start = Math.max(start, last[index]);
            res   = Math.max(res, i - start + 1);
            last[index] = i+1;
        }
        System.out.println(res);

        /**
         * java8 新的时间处理类 LocalDataTime
         */
        LocalDateTime now = LocalDateTime.now();
        //直接创建指定时间
        LocalDateTime startTime = LocalDateTime.of(2018, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2018, 1, 1, 15, 0);
        //Period计算两个时间相差的天数和月数，参数必须为LocalData类型
        Period period = Period.between(LocalDate.of(2018, 1, 8), LocalDate.of(2018, 2, 5));
        Integer days = period.getDays();
        Integer months = period.getMonths();
        System.out.println(days + "--" + months);   //28--0
        //Duration可以将相差时间转为天，时分秒等
        Duration duration = Duration.between(startTime,endTime);
        System.out.println(duration.toDays() + "-" + duration.toHours() + "-" + duration.toMinutes());
    }
}
