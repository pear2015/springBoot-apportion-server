package com.gs.crms.common.configs;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/3/3.
 */
@ConfigurationProperties(prefix = "xseed")
public class XseedSettings {
   private String noticeServiceUrl;

    /**
     * Gets notice service url.
     *
     * @return the notice service url
     */
    public String getNoticeServiceUrl() {
        return noticeServiceUrl;
    }

    /**
     * Sets notice service url.
     *
     * @param noticeServiceUrl the notice service url
     */
    public void setNoticeServiceUrl(String noticeServiceUrl) {
        this.noticeServiceUrl = noticeServiceUrl;
    }
}
