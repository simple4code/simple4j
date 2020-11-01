package com.simple4code.simple4j.demo.company.service.impl;


import com.simple4code.simple4j.SystemApplicationTest;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import com.simple4code.simple4j.demo.system.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemServiceImplTest extends SystemApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {

        UserVO byMobile = userService.findByMobile("12344123");
        System.out.println(byMobile);
    }
}