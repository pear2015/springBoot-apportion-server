package com.gs.crms.apportion.service.serviceimpl;

import com.gs.crms.apportion.contract.model.ProcessNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenwei on 2017/9/6.
 * 分派入口函数
 */
@Component
public class ApportionFactory {
    @Autowired
    private ProcessHandleFactory processHandleFactory;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 执行分派
     */
    public void startApportion() {
        List<ProcessNode>  nodes=processHandleFactory.nodes;
        for (int i=0;i<nodes.size();i++){
            ApportionThread apportionThread=new ApportionThread(processHandleFactory,nodes.get(i));
            Thread thread=new Thread(apportionThread);
            thread.start();
        }
    }
}
