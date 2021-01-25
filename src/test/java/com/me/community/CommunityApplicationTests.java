package com.me.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;
import org.thymeleaf.util.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void dateFormatTest(){
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(new Date()));
//        System.out.println(System.currentTimeMillis());
        System.out.println(DateUtils.format(System.currentTimeMillis(), Locale.CHINA));
    }
}
