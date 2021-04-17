package com.simple4j.demo.auth.entity;


import com.simple4j.demo.sys.entity.SysRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chen
 * @created 2020-10-13-13:28.
 */

@ApiModel("登录用户")
@Data
public class UserRoleDTO implements Serializable {
    private String username;
    private String password;
    private List<SysRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
