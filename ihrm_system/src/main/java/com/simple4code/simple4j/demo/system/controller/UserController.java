package com.simple4code.simple4j.demo.system.controller;


import com.simple4code.simple4j.core.controller.BaseController;
import com.simple4code.simple4j.core.entity.Result;
import com.simple4code.simple4j.core.entity.ResultCode;
import com.simple4code.simple4j.core.utils.JwtUtils;
import com.simple4code.simple4j.core.utils.PermissionConstants;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.response.ProfileResult;
import com.simple4code.simple4j.demo.system.entity.vo.RoleVO;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import com.simple4code.simple4j.demo.system.service.PermissionService;
import com.simple4code.simple4j.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@CrossOrigin
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用户登录
     * 1.通过service根据mobile查询用户
     * 2.比较password
     * 3.生成jwt信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        UserVO user = userService.findByMobile(mobile);
        //登录失败
        if (user == null || !user.getPassword().equals(password)) {
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        } else {
            //登录成功
            //api权限字符串
            StringBuilder sb = new StringBuilder();
            //获取到所有的可访问API权限
            for (RoleVO role : user.getRoles()) {
                for (Permission perm : role.getPermissions()) {
                    if (perm.getType() == PermissionConstants.PY_API) {
                        sb.append(perm.getCode()).append(",");
                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            //可访问的api权限字符串
            map.put("apis", sb.toString());
            map.put("companyId", user.getCompanyId());
            map.put("companyName", user.getCompanyName());
            String token = jwtUtils.createJwt(user.getId(), user.getUsername(), map);
            return new Result(ResultCode.SUCCESS, token);
        }
    }

    /**
     * 前后端约定:前端请求微服务时需要添加头信息Authorization ,内容为Bearer+空格+token
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST, name = "employees")
    public Result profile(HttpServletRequest request) throws Exception {
        String userid = (String) claims.get("id");
        //获取用户信息
        User user = userService.getById(userid);
        //根据不同的用户级别获取用户权限

        ProfileResult result = null;

        Map map = new HashMap();

        map.put("enVisible", "1");

        List<Permission> list = permissionService.findAll(map);
        result = new ProfileResult(user, list);
        return new Result(ResultCode.SUCCESS, result);
    }

    /**
     * 简单注册功能
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(String username, String password) {
        userService.register(username, password);
        return Result.SUCCESS();
    }
}
