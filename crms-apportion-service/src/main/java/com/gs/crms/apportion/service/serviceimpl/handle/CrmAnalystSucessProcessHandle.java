package com.gs.crms.apportion.service.serviceimpl.handle;
import com.gs.crms.apportion.service.serviceimpl.ProcessHandle;
import com.gs.crms.apportion.service.serviceimpl.remote.CrmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengyali on 2017/10/31.
 */
@Service("AnalystSuccessHandle")
public class CrmAnalystSucessProcessHandle extends ProcessHandle {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CrmsService crmsService;

    @Override
    protected boolean verify() {
        if (processHandleContext.getWaitDeleteAnalystQueue() != null && !processHandleContext.getWaitDeleteAnalystQueue().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 验证不通过，执行本次操作
     */
    @Override
    protected boolean processFalseHandle() {
        logger.info("not exist  waitDeleteAnalystQueue,process end");
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
            logger.info("delete  waitDeleteAnalystQueue By Ids, process end ");
            crmsService.deleteApportionByIds(processHandleContext.getWaitDeleteAnalystQueue());
            return true;
        } catch (Exception ex) {
            //记录日志
            logger.error("delete waitDeleteAnalystQueue Apportion By Ids error:", ex);
        }
        return false;
    }
}
