package com.sample.common.model;

public class SysRole {
    private Long roleId;

    private String roleName;

    private String available;

    public SysRole(Long roleId, String roleName, String available) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.available = available;
    }

    public SysRole() {
        super();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}