package com.gs.crms.apportion.contract.model;

/**
 * Created by qianqi on 2017/8/25.
 * 出生审核分派目标信息
 */
public class BirthAuditTarget {

    /**
     * 审核员id
     */
    private String auditId;
    /**
     * 中心编码
     */
    private String centerCode;
    /**
     * 未审核数量
     */
    private int unAuditCount;
    /**
     * 审核员姓名
     */
    private String auditName;


    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getAuditId() {
        return auditId;
    }

    /**
     * Sets user id.
     *
     * @param auditId the user id
     */
    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    /**
     * Gets center code.
     *
     * @return the center code
     */
    public String getCenterCode() {
        return centerCode;
    }

    /**
     * Sets center code.
     *
     * @param centerCode the center code
     */
    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    /**
     * Gets un audit count.
     *
     * @return the un audit count
     */
    public int getUnAuditCount() {
        return unAuditCount;
    }

    /**
     * Sets un audit count.
     *
     * @param unAuditCount the un audit count
     */
    public void setUnAuditCount(int unAuditCount) {
        this.unAuditCount = unAuditCount;
    }

    /**
     * Gets audit name.
     *
     * @return the audit name
     */
    public String getAuditName() {
        return auditName;
    }

    /**
     * Sets audit name.
     *
     * @param auditName the audit name
     */
    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }
}
