package com.gs.crms.apportion.contract.model;

/**
 * 流程节点
 * Created by chenwei on 2017/9/4.
 */
public class ProcessNode {
    /**
     * 节点值
     */
    private String nodeValue;
    /**
     * 左节点
     */
    private ProcessNode leftNode;
    /**
     * 右节点
     */
    private ProcessNode rightNode;



     public ProcessNode(String nodeValue,ProcessNode leftNode,ProcessNode rightNode){
         this.nodeValue=nodeValue;
         this.leftNode=leftNode;
         this.rightNode=rightNode;
     }
    /**
     * Gets node value.
     *
     * @return the node value
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * Sets node value.
     *
     * @param nodeValue the node value
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * Gets left node.
     *
     * @return the left node
     */
    public ProcessNode getLeftNode() {
        return leftNode;
    }

    /**
     * Sets left node.
     *
     * @param leftNode the left node
     */
    public void setLeftNode(ProcessNode leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Gets right node.
     *
     * @return the right node
     */
    public ProcessNode getRightNode() {
        return rightNode;
    }

    /**
     * Sets right node.
     *
     * @param rightNode the right node
     */
    public void setRightNode(ProcessNode rightNode) {
        this.rightNode = rightNode;
    }

}
