package com.gs.crms.apportion.service.serviceimpl.handle;

import com.gs.crms.apportion.service.serviceimpl.ProcessHandle;
import com.gs.crms.apportion.service.serviceimpl.remote.CrmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengyali on 2018/3/23.
 */
@Service("AuditorSuccessHandle")
public class CrmAuditorSucessProcessHandle  extends ProcessHandle {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrmsService crmsService;

    @Override
    protected boolean verify() {
        if (processHandleContext.getWaitDeleteAuditorQueue() != null && !processHandleContext.getWaitDeleteAuditorQueue().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 验证不通过，执行本次操作
     */
    @Override
    protected boolean processFalseHandle() {
        logger.info("not exist  waitDeleteAuditorQueue,process end");
        return false;
    }

    /**
     * 验证通过，执行本次操作
     *
     * @return the boolean
     */
    @Override
    protected boolean processTrueHandle() {
        //根据待删除id 集合删除数据
        try {
            logger.error("delete  waitDeleteAuditorQueue By Ids, process end ");
            crmsService.deleteApportionByIds(processHandleContext.getWaitDeleteAuditorQueue());
            return true;
        } catch (Exception ex) {
            //记录日志
            logger.error("delete waitDeleteAuditorQueue Apportion By Ids error:", ex);
        }
        return false;
    }
}
