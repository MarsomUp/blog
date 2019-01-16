package com.immyc.blog.controller;

import com.immyc.blog.admin.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description:
 * @Author:mayc
 * @Date:2019/1/16 22:29
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Test
    public void addUser() {
        User user = new User();
        user.setUserName("马玉财");
        user.setLoginAccount("mayc");
        user.setGender(0);
        user.setDegree(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.of(1992, 11, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("19920315");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthday(date);

    }
}
