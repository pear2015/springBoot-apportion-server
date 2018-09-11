package com.gs.crms.apportion.contract.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/9/8.
 */
public class SearchInfo {
    /**
     * 角色
     * */
    public  String   roleId;
    /**
     * analyst 犯罪--分析角色
     * */
    public  enum Role{
        analyst("2");
        private String num;
        private  Role(String num){
            this.num = num;
        }
        public String getNum() {
            return num;
        }
    }
    /**
     * 状态
     * */
    public String status;
    /**
     * analyst 犯罪--分析角色
     * */
    public  enum Status{
        free("0");
        private String num;
        private  Status(String num){
            this.num = num;
        }
        public String getNum() {
            return num;
        }
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
