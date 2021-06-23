package com.example.nacoshttpconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class NacosHttpConsumerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("---------------");
    }

    public static void main(String[] args) {
        Duration duration = Duration.ofDays(1);//设置一天时间
        long timeHours = duration.toHours();//小时
        long timeMinutes = duration.toMinutes();//分钟
        long timeMillis = duration.toMillis();//毫秒
        long timeNanos = duration.toNanos();//纳秒
        String timeString = duration.toString(); //此持续时间的字符串表示形式,使用基于ISO-8601秒*的表示形式,例如 PT8H6M12.345S
        System.out.println("timeHours时间="+timeHours);
        System.out.println("timeMinutes时间="+timeMinutes);
        System.out.println("timeMillis时间="+timeMillis);
        System.out.println("timeNanos时间="+timeNanos);
        System.out.println("timeString时间="+timeString);
        String s="ss";
    }

}
