package com.gs.crms.apportion.contract.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/10/10.
 */
public class AuditorApportion {
    private  List<WaitApportion> waitApportionList=new ArrayList<>();
    private List<String> userIds =new ArrayList<>();

//    public List<String> getProcessInstanceIds() {
//        return processInstanceIds;
//    }
//
//    public void setProcessInstanceIds(List<String> processInstanceIds) {
//        this.processInstanceIds = processInstanceIds;
//    }


    public List<WaitApportion> getWaitApportionList() {
        return waitApportionList;
    }

    public void setWaitApportionList(List<WaitApportion> waitApportionList) {
        this.waitApportionList = waitApportionList;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

}
