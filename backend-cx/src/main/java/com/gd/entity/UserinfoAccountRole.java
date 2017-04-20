package com.gd.entity;

/**
 * Created by Administrator on 2017/3/30.
 */
public class UserinfoAccountRole {
    private String roleId;
    private String roleName;

    public UserinfoAccountRole(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public void setRoleId(String roleId) {

        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {

        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
