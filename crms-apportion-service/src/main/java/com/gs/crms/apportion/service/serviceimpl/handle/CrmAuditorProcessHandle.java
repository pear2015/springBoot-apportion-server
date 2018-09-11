package com.gs.crms.apportion.service.serviceimpl.handle;
import com.gs.crms.apportion.contract.model.User;
import com.gs.crms.apportion.service.serviceimpl.ProcessHandle;
import com.gs.crms.apportion.service.serviceimpl.remote.NoticeService;
import com.gs.crms.common.enums.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 获取空闲用户列表
 * Created by chenwei on 2017/9/2.
 */
@Service("AuditorHandle")
public class CrmAuditorProcessHandle extends ProcessHandle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NoticeService noticeService;
      /**
       * 验证是否可以进行操作
       * 1.获取在线socket列表
       * 1.1 在线人数大于0 加入列表
       */
    @Override
    protected boolean verify() {
        //获取空闲用户列表，如果等于0或空，不执行
        List<User> users= noticeService.getUserList(RoleType.AUDITOR);
        if(!CollectionUtils.isEmpty(users)){
            processHandleContext.setAuditorList(users);
            return true;
        }
        return false;
    }

    /**
     * 验证不通过，执行本次操作
     */
    @Override
    protected boolean processFalseHandle() {
        //记录日志
        logger.error("not exist auditor,AuditorHandle process quit");
        return false;
    }

    /**
     * 验证通过，执行本次操作
     *
     * @return the boolean
     */
    @Override
    protected boolean processTrueHandle() {
        logger.info("exist auditor process continue");
        return true;
    }
}
