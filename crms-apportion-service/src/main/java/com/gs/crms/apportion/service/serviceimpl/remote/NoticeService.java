package com.gs.crms.apportion.service.serviceimpl.remote;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gs.crms.apportion.contract.model.User;
import com.gs.crms.common.enums.RoleType;
import com.gs.crms.common.access.NoticeUrl;
import com.gsafety.springboot.common.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知相关访问url
 * Created by chenwei on 2017/9/4.
 */
@Service
public class NoticeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${service.socketServiceApi}")
    private String serviceUrl;
    @Autowired
    private NoticeUrl noticeApiUrl;
    @Autowired
    private HttpClientUtil httpClientUtil;

    /**
     * 获取空闲用户列表
     *
     * @return 用户列表集合
     */
    public List<User> getUserList(RoleType roleType) {
        List<User> userList = new ArrayList<>();
        try {
            userList = httpClientUtil.httpGetWithType(serviceUrl + noticeApiUrl.getOnlineUserList()+"?roleType=" + roleType, new TypeReference<List<User>>() {
            });
            return userList;
        } catch (Exception e) {
            logger.info("get socket userList error——>connect refused",e);
            return userList;
        }
    }
}
