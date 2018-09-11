package com.gs.crms.apportion.service.serviceimpl.handle;
import com.gs.crms.apportion.contract.model.AnalystAndProcess;
import com.gs.crms.apportion.contract.model.User;
import com.gs.crms.apportion.contract.model.WaitApportion;
import com.gs.crms.apportion.service.serviceimpl.ProcessHandle;
import com.gs.crms.apportion.service.serviceimpl.remote.CrmsService;
import com.gs.crms.apportion.service.serviceimpl.remote.WorkFlowService;
import com.gs.crms.common.enums.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhengyali on 2017/10/31.
 */
@Service("AnalystWaitHandle")
public class CrmAnalystWaitProcessHandle extends ProcessHandle {
    @Autowired
    private CrmsService crmsService;
    @Autowired
    private WorkFlowService workFlowService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 分析员分派数据
     * 前置节点:存在分析员
     * 是否存在待分析的任务列表
     * 存在 调用工作流 激活分析流程
     */
    @Override
    protected boolean verify() {
        List<WaitApportion> analystWaitApportionList = crmsService.getAllWaitApportion("1");
        if (CollectionUtils.isEmpty(analystWaitApportionList)) {
            return false;
        }
        processHandleContext.setAnalystWaitApportionList(analystWaitApportionList);
        return true;
    }
    /**
     * 验证不通过，执行本次操作
     */
    @Override
    protected boolean processFalseHandle() {
        logger.info("not exist waitApportion AnalystWaitHandle process quit");
        return false;
    }

    /**
     * 验证通过，执行本次操作
     *  调用工作流 激活分析流程
     */
    @Override
    protected boolean processTrueHandle() {
        logger.info("exist waitApportion AnalystWaitHandle process continue");
        return apportionData(processHandleContext.getAnalystList(), processHandleContext.getAnalystWaitApportionList());

    }
    /**
     * 激活分析流程
     * @return
     */
    private boolean apportionData(List<User> userList, List<WaitApportion> waitApportionList) {
        if (CollectionUtils.isEmpty(userList) || CollectionUtils.isEmpty(waitApportionList)) {
            logger.info("analyst is Empty or waitApportionList is Empty AnalystWaitHandle process quit");
            return false;
        }
        List<String> analystOnline = new ArrayList<>();
        List<String> analystFree = new ArrayList<>();
        List<User> analystList = userList.stream().filter(f -> !StringUtils.isEmpty(f.getRoleType()) && f.getRoleType().equals(RoleType.ANALYST)).collect(Collectors.toList());
        //在线空闲分析员
        if (!CollectionUtils.isEmpty(analystList)) {
            analystList.forEach(user -> analystOnline.add(user.getUserId())
            );
            analystFree = crmsService.getAnalystFreeList(analystOnline);
        }
        //分析员分派
        if (!CollectionUtils.isEmpty(analystFree) && !CollectionUtils.isEmpty(waitApportionList)) {
           return  analystActiveWorkFlow(waitApportionList, analystFree);
        } else {
            logger.info(" free userList  is Empty AnalystWaitHandle process quit");
            return false;
        }
    }

    /**
     *  调用工作流进行激活分析流程
     * */
    private boolean  analystActiveWorkFlow(List<WaitApportion> analystWaitApportionList, List<String> analystFree) {
        List<AnalystAndProcess> analystAndProList = new ArrayList<>();
        for (int t = 0; t < analystFree.size(); t++) {
            if (t > analystWaitApportionList.size() - 1) {
                break;
            }
            AnalystAndProcess analystAndProcess = new AnalystAndProcess();
            analystAndProcess.setAnalystId(analystFree.get(t));
            analystAndProcess.setProcessId(analystWaitApportionList.get(t).getProcessId());
            analystAndProcess.setApplyId(analystWaitApportionList.get(t).getApplyInfoId());
            analystAndProcess.setPriority(analystWaitApportionList.get(t).getApplyPriority());
            analystAndProcess.setBusinessType(analystWaitApportionList.get(t).getBusinessType());
            analystAndProList.add(analystAndProcess);
        }
        // 调用工作流进行激活分析流程
        List<String> waitDeleteQueueList = workFlowService.workFlowAnalystAssign(analystAndProList);
        if (CollectionUtils.isEmpty(waitDeleteQueueList)) {
            logger.info("waitDeleteAnalystQueue  is Empty AnalystWaitHandle process quit");
            return false;
        } else {
            //加入等待删除列表
            logger.info("waitDeleteAnalystQueue  is not Empty AnalystWaitHandle process continue");
            processHandleContext.setWaitDeleteAnalystQueue(waitDeleteQueueList);
            return true;
        }
    }
}
