package com.gs.crms.apportion.service.serviceimpl.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gs.crms.apportion.contract.model.AnalystAndProcess;
import com.gs.crms.apportion.contract.model.AuditorApportion;
import com.gs.crms.common.access.CrmsUrl;
import com.gsafety.springboot.common.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyali on 2017/9/28.
 */
@Service
public class WorkFlowService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${service.workFlowServiceApi}")
    private String serviceUrl;
    @Autowired
    private CrmsUrl serviceApiUrl;
    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 调用工作流接口进行分析员任务分派
     */
    public List<String> workFlowAnalystAssign(List<AnalystAndProcess> analystAndProcessList) {
        List<String> result = new ArrayList<>();
        try {
            result = httpClientUtil.httpPostReturnTypeRef(serviceUrl + serviceApiUrl.getWorkFlowAnalystAssign(), analystAndProcessList, new TypeReference<List<String>>() {
            }, false);
            return result;
        } catch (Exception e) {
            logger.info("analyst assign error ——>connect refused",e);
            return result;
        }

    }

    /**
     * 调用工作流接口进行审核员任务分派
     */
    public List<String> workFlowAuditApplyAssign(AuditorApportion auditorApportion) {
        List<String> result = new ArrayList<>();
        try {
            result = httpClientUtil.httpPostReturnTypeRef(serviceUrl + serviceApiUrl.getWorkFlowAuditApplyAssign(), auditorApportion,
                    new TypeReference<List<String>>() {
                    }, false);
            return result;
        } catch (Exception e) {
            logger.info("auditor assign error——>connect refused",e);
            return result;
        }
    }
}
