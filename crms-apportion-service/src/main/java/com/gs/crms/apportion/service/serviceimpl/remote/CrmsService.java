package com.gs.crms.apportion.service.serviceimpl.remote;

import com.gs.crms.apportion.contract.model.WaitApportion;
import com.gs.crms.common.access.CrmsUrl;
import com.gsafety.springboot.common.utils.HttpClientUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * 犯罪服务相关请求
 * Created by chenwei on 2017/9/4.
 */
@Service
public class CrmsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${service.crimeServiceApi}")
    private String serviceUrl;
    @Autowired
    private CrmsUrl serviceApiUrl;

    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 获取所有待分派数据
     *
     * @return
     */
    public List<WaitApportion> getAllWaitApportion(String waitReasonType ) {
        List<WaitApportion> waitApportionList=new ArrayList<>();
        try {
            waitApportionList= httpClientUtil.httpGetWithType(serviceUrl + serviceApiUrl.getAllWaitApportionUrl()+"?waitReasonType="+waitReasonType, new TypeReference<List<WaitApportion>>() {
        });
            return waitApportionList;
        }catch (Exception e){
            logger.info("get wait Apportion List error !",e);
            return waitApportionList;
        }
    }

    /**
     * 调用犯罪服务筛选无申请的分析员
     *
     * @param users
     */

    public List<String> getAnalystFreeList(List<String> users) {
        List<String> userList=new ArrayList<>();
        try {
            userList= httpClientUtil.httpPostReturnTypeRef(serviceUrl + serviceApiUrl.getAnalystFreeList(), users, new TypeReference<List<String>>() {
            }, false);
            return  userList;
        } catch (Exception e) {
            logger.info("get analyst free List error !",e);
            return userList;
        }
    }

    /**
     * 删除待分派数据中已分派的数据
     *
     * @param ids
     * @return
     */

    public boolean deleteApportionByIds(List<String> ids) {
        try {
            Boolean rtn = httpClientUtil.httpPostReturnTypeRef(serviceUrl + serviceApiUrl.getDeleteApportionUrl(), ids, new TypeReference<Boolean>() {
            }, false);
            if (rtn == null) {
                return false;
            } else
                return rtn;
        } catch (Exception e) {
            logger.info("delete wait apportion error !",e);
            return false;
        }
    }

}
