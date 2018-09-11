package com.gs.crms.apportion.service.serviceimpl;

import com.gs.crms.apportion.contract.model.ProcessNode;
import com.gs.crms.common.component.CommonBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenwei on 2017/9/2.
 */
@Service
public class ProcessHandleFactory extends CommonBase {
    @Autowired
    private ApplicationContext context;
    /**
     * 节点列表
     */
    public final List<ProcessNode> nodes=new ArrayList<>();

    /**
     *初始化节点
     */
    public ProcessHandleFactory(){
        initProcessNode();
    }
    /**
     * 初始化流程节点
     * 根节点检测用户(节点1)
     * 节点1的左节点(存在在线分析员（节点2）)   节点1的右节点（存在在线审核员（节点3））
     * 节点2的左节点(查询分析员待分析列表   节点4)
     * 节点3的左节点（查询审核员待审核员列表  节点5）
     * 节点4的左节点（删除分析员等待分派列表  节点6）
     * 节点5的左节点（删除审核员等待分派列表 节点7）
     */
    private  void  initProcessNode(){
        ProcessNode threeNode=new ProcessNode("AnalystSuccessHandle",null,null);
        ProcessNode secondNode=new ProcessNode("AnalystWaitHandle",threeNode,null);
        ProcessNode node=new ProcessNode("AnalystHandle",secondNode,null);
        nodes.add(node);
        ProcessNode threeNode1=new ProcessNode("AuditorSuccessHandle",null,null);
        ProcessNode secondNode1=new ProcessNode("AuditorWaitHandle",threeNode1,null);
        ProcessNode node1=new ProcessNode("AuditorHandle",secondNode1,null);
        nodes.add(node1);
    }

    /**
     *  获取流程处理对象
     * @param beanName
     * @return
     */
    public ProcessHandle getProgressHandle(String beanName){
      try {
          if (!StringUtils.isEmpty(beanName)) {
              return   (ProcessHandle)this.context.getBean(beanName);
          }
          this.log.error("ProcessHandleFactory->getProgressHandle:para beanName is empty");
      }catch (Exception ex){
          this.log.error("ProcessHandleFactory->getProgressHandle:get ProcessHandle have exception"+ex);
      }
        return  null;
    }
}
