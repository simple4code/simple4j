package com.simple4code.simple4j.demo.company.service.impl;


import com.simple4code.simple4j.SystemApplicationTest;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import com.simple4code.simple4j.demo.system.mapper.PermissionMapper;
import com.simple4code.simple4j.demo.system.service.PermissionService;
import com.simple4code.simple4j.demo.system.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SystemServiceImplTest extends SystemApplicationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void test() {

        UserVO byMobile = userService.findByMobile("12344123");
        System.out.println(byMobile);
        User user = userService.getUserByUserName("123");
        System.out.println(user);
        //List<Permission> permissions = permissionService.selectListByPath("/system/user/delete");

        List<Permission> permissions = permissionMapper.selectListByUser("1066370498633486336");
        //permissions.forEach(System.out::println);
    }

}