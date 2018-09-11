package com.gs.crms.apportion.service.serviceimpl;
import com.gs.crms.apportion.contract.model.User;
import com.gs.crms.apportion.contract.model.WaitApportion;
import java.util.List;

/**
 *  流程处理上下文
 * Created by chenwei on 2017/9/2.
 */
public class ProcessHandleContext {

    /**
     * 分析员  待分派申请数量
     */
    private List<WaitApportion> analystWaitApportionList;
    /**
     * 审核员  待分派申请数量
     */
    private List<WaitApportion> auditorWaitApportionList;
    /**
     *  在线分析员集合
     */
    private List<User> analystList;
    /**
     *  在线分析员审核员集合
     */
    private List<User> auditorList;

    /**
     * 待删除队列数据集合
     */
    private List<String> waitDeleteAnalystQueue;
    /**
     * 待删除队列数据集合
     */
    private List<String> waitDeleteAuditorQueue;

    public List<WaitApportion> getAnalystWaitApportionList() {
        return analystWaitApportionList;
    }

    public void setAnalystWaitApportionList(List<WaitApportion> analystWaitApportionList) {
        this.analystWaitApportionList = analystWaitApportionList;
    }

    public List<WaitApportion> getAuditorWaitApportionList() {
        return auditorWaitApportionList;
    }

    public void setAuditorWaitApportionList(List<WaitApportion> auditorWaitApportionList) {
        this.auditorWaitApportionList = auditorWaitApportionList;
    }

    public List<User> getAnalystList() {
        return analystList;
    }

    public void setAnalystList(List<User> analystList) {
        this.analystList = analystList;
    }

    public List<User> getAuditorList() {
        return auditorList;
    }

    public void setAuditorList(List<User> auditorList) {
        this.auditorList = auditorList;
    }

    public List<String> getWaitDeleteAnalystQueue() {
        return waitDeleteAnalystQueue;
    }

    public void setWaitDeleteAnalystQueue(List<String> waitDeleteAnalystQueue) {
        this.waitDeleteAnalystQueue = waitDeleteAnalystQueue;
    }

    public List<String> getWaitDeleteAuditorQueue() {
        return waitDeleteAuditorQueue;
    }

    public void setWaitDeleteAuditorQueue(List<String> waitDeleteAuditorQueue) {
        this.waitDeleteAuditorQueue = waitDeleteAuditorQueue;
    }
}
