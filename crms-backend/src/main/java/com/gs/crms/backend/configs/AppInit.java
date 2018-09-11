package com.gs.crms.backend.configs;

import com.gs.crms.apportion.service.serviceimpl.ApportionFactory;
import com.gs.crms.common.configs.XseedSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Locale;

/**
 * Created by xiaodiming on 2016/5/20.
 */
@EnableConfigurationProperties({XseedSettings.class})
@ComponentScan({"com.gs.crms.*"})
public class AppInit implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${language}")
    private String language;

    @Autowired
    private ApportionFactory apportionFactory;
    /**
     * onApplicationEvent
     *
     * @param event ContextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            setLocale();
        }
        apportionFactory.startApportion();
    }

    /**
     * Sets locale by config language.
     *
     * @return the locale
     */
    public String setLocale() {
        String[] lan = language.split("_");
        Locale locale;
        if (lan.length > 1) {
            locale = new Locale(lan[0], lan[1]);
            Locale.setDefault(locale);
        }
        return null;
    }
}
