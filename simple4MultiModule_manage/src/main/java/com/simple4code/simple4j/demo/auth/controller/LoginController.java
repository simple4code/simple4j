package com.simple4code.simple4j.demo.auth.controller;


import com.simple4code.simple4j.core.common.controller.BaseController;
import com.simple4code.simple4j.core.common.entity.RestResult;
import com.simple4code.simple4j.demo.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen
 * @created 2020-10-13-13:52.
 */
@Api(tags = "用户登录及token认证接口")
@Slf4j
@RestController
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public RestResult login(
            @ApiParam("Username") @RequestParam String username,
            @ApiParam("Password") @RequestParam String password) {
        String result = sysUserService.login(username, password);
        return new RestResult().success(result);
    }

}
