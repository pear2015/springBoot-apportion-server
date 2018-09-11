package com.gs.crms.apportion.service.serviceimpl.handle;
import com.gs.crms.apportion.contract.model.AuditorApportion;
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
@Service("AuditorWaitHandle")
public class CrmAuditorWaitProcessHandle extends ProcessHandle {
    @Autowired
    private CrmsService crmsService;
    @Autowired
    private WorkFlowService workFlowService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected boolean verify() {
        List<WaitApportion> auditorWaitApportionList = crmsService.getAllWaitApportion("2");
        if (CollectionUtils.isEmpty(auditorWaitApportionList)) {
            return false;
        }
        processHandleContext.setAuditorWaitApportionList(auditorWaitApportionList);
        return true;
    }

    @Override
    protected boolean processFalseHandle() {
        logger.info("not exist waitApportion AuditorWaitHandle process quit");
        return false;

    }

    @Override
    protected boolean processTrueHandle() {
        logger.info("exist waitApportion AuditorWaitHandle process continue");
        return apportionData(processHandleContext.getAuditorList(), processHandleContext.getAuditorWaitApportionList());
    }

    /**
     * 数据分派数据
     *
     * @return
     */
    private boolean apportionData(List<User> userList, List<WaitApportion> waitApportionList) {
        if (CollectionUtils.isEmpty(userList) || CollectionUtils.isEmpty(waitApportionList)) {
            logger.info("auditorList is Empty or waitApportionList is Empty AuditorWaitHandle process quit");
            return false;
        }
        List<String> auditorOnline = new ArrayList<>();
        List<User> auditUser = userList.stream().filter(f -> !StringUtils.isEmpty(f.getRoleType()) && f.getRoleType().equals(RoleType.AUDITOR)).collect(Collectors.toList());
        //在线审核员
        auditUser.forEach(user -> auditorOnline.add(user.getUserId())
        );
        // 审核员分派 申请业务
        if (!CollectionUtils.isEmpty(auditUser) && !CollectionUtils.isEmpty(waitApportionList)) {
            logger.info("auditorList is not  Empty or waitApportionList is not Empty AuditorWaitHandle process continue");
            // 取和在线审核员同等数量的等待分派数据进行激活,分派数量以长度小的为准
            List<WaitApportion> newWaitApportionList = new ArrayList<>();
            int length = auditUser.size() > waitApportionList.size() ? waitApportionList.size() : auditUser.size();
            for (int i = 0; i < length; i++) {
                newWaitApportionList.add(waitApportionList.get(i));
            }
            return auditActiveWorkFlow(newWaitApportionList, auditorOnline);
        }
        return false;
    }


    /***
     * 审核员申请业务审核激活和公告业务审核激活
     *  获取等待派发队列列表
     *  调用工作流进行激活审核流程
     *  根据返回结果列表： 加入删除队列
     * */
    private boolean auditActiveWorkFlow(List<WaitApportion> auditWaitApportionList, List<String> userIds) {
        AuditorApportion auditorApportion = new AuditorApportion();
        auditorApportion.setWaitApportionList(auditWaitApportionList);
        auditorApportion.setUserIds(userIds);
        // 调用工作流进行激活审核流程
        List<String> waitDeleteQueueList = workFlowService.workFlowAuditApplyAssign(auditorApportion);
        if (!CollectionUtils.isEmpty(waitDeleteQueueList)) {
            processHandleContext.setWaitDeleteAuditorQueue(waitDeleteQueueList);
            return true;
        }
        logger.info("()->waitDeleteAnalystQueue  is   Empty  AuditorWaitHandle process quit");
        return false;
    }
}
