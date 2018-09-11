package com.gs.crms.common.access;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 拼接犯罪url
 * Created by chenwei on 2017/9/4.
 */
@Service
public class CrmsUrl {
    /**
     * 获取所有待分派数据url(/awaitApportion/get)
     */
    @Value("${service.allWaitApportionUrl}")
    private String allWaitApportionUrl;
    /**
     * 删除待分派数据通过id 集合url(waitApportion/delete )
     */
    @Value("${service.deleteApportionUrl}")
    private String deleteApportionUrl ;

    /**
     * 分析员分派激活(/process/active/analyst)
     */
    @Value("${service.workFlowAnalystAssign}")
    private String workFlowAnalystAssign ;
    /**
     * 审核员分派激活(/process/active )
     */
    @Value("${service.workFlowAuditAssign}")
    private String workFlowAuditApplyAssign ;
    /**
     * 审核员分派激活(/process/active)
     */
    @Value("${service.workFlowAuditNoticeAssign}")
    private String workFlowAuditNoticeAssign;
    /**
     * 无任务在线分析员(/process/active/analyst)
     */
    @Value("${service.analystFreeList}")
    private String analystFreeList ;

    public String getAllWaitApportionUrl() {
        return allWaitApportionUrl;
    }

    public void setAllWaitApportionUrl(String allWaitApportionUrl) {
        this.allWaitApportionUrl = allWaitApportionUrl;
    }

    public String getDeleteApportionUrl() {
        return deleteApportionUrl;
    }

    public void setDeleteApportionUrl(String deleteApportionUrl) {
        this.deleteApportionUrl = deleteApportionUrl;
    }

    public String getWorkFlowAnalystAssign() {
        return workFlowAnalystAssign;
    }

    public void setWorkFlowAnalystAssign(String workFlowAnalystAssign) {
        this.workFlowAnalystAssign = workFlowAnalystAssign;
    }

    public String getWorkFlowAuditApplyAssign() {
        return workFlowAuditApplyAssign;
    }

    public void setWorkFlowAuditApplyAssign(String workFlowAuditApplyAssign) {
        this.workFlowAuditApplyAssign = workFlowAuditApplyAssign;
    }

    public String getWorkFlowAuditNoticeAssign() {
        return workFlowAuditNoticeAssign;
    }

    public void setWorkFlowAuditNoticeAssign(String workFlowAuditNoticeAssign) {
        this.workFlowAuditNoticeAssign = workFlowAuditNoticeAssign;
    }

    public String getAnalystFreeList() {
        return analystFreeList;
    }

    public void setAnalystFreeList(String analystFreeList) {
        this.analystFreeList = analystFreeList;
    }
}
