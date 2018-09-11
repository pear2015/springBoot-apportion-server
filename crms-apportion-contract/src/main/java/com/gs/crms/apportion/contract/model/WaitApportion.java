package com.gs.crms.apportion.contract.model;

import java.util.Date;

/**
 * 等待派发队列
 * Created by zhangqiang on 2017/8/22.
 */
public class WaitApportion {
    /**
     * 申请ID
     */
    private String applyInfoId;
    /**
     * 优先级
     */
    private String applyPriority;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 等待激活的原因类型1.分析员2.审核员
     */
    private String  waitReasonType;
    /**
     * 业务类型
     * 0 个人申请
     * 1 政府申请
     * 2 公告
     */
    private  String  businessType;
    /**
     * 工作流流程ID
     */
    private String processId;

    public String getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    public String getApplyPriority() {
        return applyPriority;
    }

    public void setApplyPriority(String applyPriority) {
        this.applyPriority = applyPriority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWaitReasonType() {
        return waitReasonType;
    }

    public void setWaitReasonType(String waitReasonType) {
        this.waitReasonType = waitReasonType;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
