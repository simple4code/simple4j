package com.simple4j.demo.sys.controller;


import com.simple4j.core.common.entity.RestResult;
import com.simple4j.demo.sys.entity.SysUser;
import com.simple4j.demo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author simple4j
 * @since 2020-10-29
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户列表")
    @PostMapping("/v1/list")
    public RestResult list() {
        List<SysUser> list = sysUserService.list();
        return new RestResult().success(list);
    }
}
