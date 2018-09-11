package com.gs.crms.apportion.service.serviceimpl;

import com.gs.crms.apportion.contract.model.ProcessNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by chenwei on 2017/9/6.
 */
public class ApportionThread implements  Runnable {

    private int apportionInterval = 20000;

    private  ProcessHandle handle;
    private  ProcessNode  processNode;
    private ProcessHandleFactory processHandleFactory;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final   Object syncObject=new Object();

    /**
     * 初始化构造
     * @param processHandleFactory
     * @param processNode
     */
    public ApportionThread(ProcessHandleFactory processHandleFactory,ProcessNode processNode){
        if(processHandleFactory!=null&&processNode!=null&&!StringUtils.isEmpty(processNode.getNodeValue())){
            this.processHandleFactory=processHandleFactory;
            this.handle=processHandleFactory.getProgressHandle(processNode.getNodeValue());
            this.processNode=processNode;
        }
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true) {
            try {
                if(StringUtils.isEmpty(processNode.getNodeValue())){
                    break;
                }
                ProcessHandleContext processHandleContext=new ProcessHandleContext();
                synchronized (syncObject){
                    handle.exec(processHandleFactory,processNode,processHandleContext);
               }
                Thread.sleep(apportionInterval);
            } catch (Exception e) {
                logger.error(Thread.currentThread().getName()+"IncidentApportionThread run", e);
            }
        }
    }
}
