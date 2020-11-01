package com.simple4code.simple4j.demo.system.entity.response;


import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.vo.RoleVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoleResult implements Serializable {
    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;

    /**
     * 权限id
     */
    private List<String> permIds = new ArrayList<>();

    public RoleResult(RoleVO role) {
        BeanUtils.copyProperties(role, this);
        for (Permission perm : role.getPermissions()) {
            this.permIds.add(perm.getId());
        }
    }
}
