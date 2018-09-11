package com.gs.crms.common.access;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *  拼接通知url
 * Created by chenwei on 2017/9/4.
 */
@Service
public class NoticeUrl {
    /**
     * 空闲用户url
     * @return
     */
    @Value("${service.onlineUserList}")
    private   String  onlineUserList;

    public String getOnlineUserList() {
        return onlineUserList;
    }

    public void setOnlineUserList(String onlineUserList) {
        this.onlineUserList = onlineUserList;
    }
}
