package com.gs.crms.apportion.contract.model;

/**
 * Created by qianqi on 2017/8/25.
 */
public class ApportionTarget {

    /**
     * 主键
     */
    private String id;
    /**
     * 分派目标的主键(人员编号)
     */
    private String targetId;

    /**
     * 分派目标的姓名
     */
    private String targetName;

    /**
     *  分派的申请编号
     */
    private String applyInfoId;
    /**
     * 中心编码
     */
    private String centerCode;

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
     * Gets target id.
     *
     * @return the target id
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * Sets target id.
     *
     * @param targetId the target id
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
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
     * Gets apply info id.
     *
     * @return the apply info id
     */
    public String getApplyInfoId() {
        return applyInfoId;
    }

    /**
     * Sets apply info id.
     *
     * @param applyInfoId the apply info id
     */
    public void setApplyInfoId(String applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

    /**
     * Gets target name.
     *
     * @return the target name
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * Sets target name.
     *
     * @param targetName the target name
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}