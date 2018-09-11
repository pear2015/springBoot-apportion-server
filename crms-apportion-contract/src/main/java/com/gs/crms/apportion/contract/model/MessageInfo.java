package com.gs.crms.apportion.contract.model;

import java.util.Date;

/**
 * Created by zhengyali on 2017/9/8.
 */
public class MessageInfo {
    /**
     * 接收人
     * */
    private String receiverId;

    /**
     * 用户状态
     * */
    private String status;

    /**
     * 用户角色
     * */
    private String roleId;

    /**
     * 消息事件
     * */
    private String event;

    /**
     * 消息体内容
     * */
    private String content;


    /**
     * 创建时间
     * */
    private Date createDate;

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
