package com.gs.crms.apportion.contract.model;

/**
 * Created by qianqi on 2017/8/28.
 * 出生证明临时件信息
 */
public class BirthCertificateTemporary {

    /**
     * 出生证明临时件信息主键
     */
    private String id;
    /**
     * 中心编码
     */
    private String centerCode;

    /**
     * 审核员Id
     */
    private String auditorId;

    /**
     * 审核员姓名
     */
    private String auditorName;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
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
     * Gets auditor id.
     *
     * @return the auditor id
     */
    public String getAuditorId() {
        return auditorId;
    }

    /**
     * Sets auditor id.
     *
     * @param auditorId the auditor id
     */
    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * Gets auditor name.
     *
     * @return the auditor name
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * Sets auditor name.
     *
     * @param auditorName the auditor name
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }
}
