package com.gs.crms.apportion.contract.model;

import com.gs.crms.common.enums.RoleType;

/**
 * Created by zhengyali on 2017/9/1.
 * 空闲 用戶列表
 */
public class User {
    private String userId;
    private String status;
    private String event;
    private String roleId;
    /**
     * 暂时用roleType 不用roleId
     * roleId 无法区分角色
     * */
    private RoleType  roleType;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}