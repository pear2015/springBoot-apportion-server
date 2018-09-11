package com.gs.crms.apportion.service.serviceimpl;

import com.gs.crms.apportion.contract.model.ProcessNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenwei on 2017/9/2.
 */
public abstract class ProcessHandle {
    /**
     * 日志对象
     */
    Logger log = LoggerFactory.getLogger(this.getClass());

    protected ProcessHandleContext processHandleContext;
    /**
     * 下一个流程（条件验证正确时执行）
     */
    protected   ProcessHandle  trueProcessHandle;

    /**
     * 下一个流程（条件错误时执行）
     */
    protected   ProcessHandle falseProcessHandle;

    /*
     *  条件验证
     */
   protected abstract  boolean verify();

    /**
     * 验证不通过，执行本次操作
     */
   protected abstract boolean processFalseHandle();

    /**
     * 验证通过，执行本次操作
     *
     * @return the boolean
     */
   protected abstract boolean processTrueHandle();

    /**
     * 初始化下一个执行流程
     * 当前节点走完后
     * 1、当前验证的结果 为 true
     * 1.1 判断该节点是否存在子节点
     *    存在   继续走
     *    不存在 判断 该节点是否为根节点的左半边节点  判断是否存在右节点
     * 2、当前验证的结果 为 false 左半边节点走完    开始走右半边节点
     * @param handleFactory
     */
    protected   void  initNextProcessHandle(ProcessHandleFactory handleFactory,ProcessNode node){
        try {
            // 左节点不为空
             if(node!=null&&node.getLeftNode()!=null){
                    ProcessHandle processHandle= handleFactory.getProgressHandle(node.getLeftNode().getNodeValue());
                    if(processHandle!=null){
                        this.trueProcessHandle=processHandle;
                    }
                }
            // 右节点不为空
             if(node!=null&&node.getRightNode()!=null){
                    ProcessHandle processHandle= handleFactory.getProgressHandle(node.getRightNode().getNodeValue());
                    if(processHandle!=null){
                        this.falseProcessHandle=processHandle;
                    }
                }
        }catch (Exception ex){
            // 记录异常日志
            log.error("init Next ProcessHandle error:",ex);
        }
    }
    /**
     * 执行本次流程步骤
     */
    public void exec(ProcessHandleFactory handleFactory, ProcessNode node,ProcessHandleContext processHandleContext){
        this.processHandleContext=processHandleContext;
        initNextProcessHandle(handleFactory,node);
        if(verify()){
             //执行本次逻辑，本次逻辑执行失败，记录日志
             if(processTrueHandle()){
                  //执行成功 执行下一个流程
                  if(trueProcessHandle!=null){
                      trueProcessHandle.exec(handleFactory,node.getLeftNode(),processHandleContext);
                  }
             }else{
                 //记录日志中
                 log.error("exec processTrueHandle not in process:",node.getNodeValue());
             }
        }else{
            //记录日志 执行成功执行下一步流程
            if(processFalseHandle()){
                if(falseProcessHandle!=null){
                    falseProcessHandle.exec(handleFactory,node.getRightNode(),processHandleContext);
                }
            }else {
                //记录日志中
                log.error("exec processFalseHandle not in process:",node.getNodeValue());
            }

        }
    }
}
