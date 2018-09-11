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
 * Created by zhengyali on 2017/10/31.
 * 判断分析员是否在线
 */
@Service("AnalystHandle")
public class CrmAnalystProcessHandle extends ProcessHandle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NoticeService noticeService;
    /**
     * 验证是否可以进行操作
     * 判断是否存在在线分析员
     * 存在 流程继续
     */
    @Override
    protected boolean verify() {
        //获取空闲用户列表，如果等于0或空，不执行
        List<User> users= noticeService.getUserList(RoleType.ANALYST);
        if(!CollectionUtils.isEmpty(users)){
            processHandleContext.setAnalystList(users);
            return  true;
        }
        return false;
    }

    /**
     * 验证不通过，执行本次操作
     */
    @Override
    protected boolean processFalseHandle() {
        //记录日志
        logger.error("analystList is empty process handle of AnalystHandle false");
        return false;
    }

    /**
     * 验证通过，执行本次操作
     *
     * @return the boolean
     */
    @Override
    protected boolean processTrueHandle() {
        logger.error("analystList is not empty process handle of AnalystHandle continue");
        return true;
    }
}
