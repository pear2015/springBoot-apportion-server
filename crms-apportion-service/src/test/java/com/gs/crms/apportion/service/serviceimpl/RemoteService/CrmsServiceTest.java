//package com.gs.crms.apportion.service.serviceimpl.RemoteService;
//
//import com.gs.crms.apportion.contract.model.WaitApportion;
//import com.gsafety.springboot.common.utils.HttpClientUtil;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.reset;
//import static org.testng.Assert.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
//
///**
// * Created by zhengyali on 2017/12/1.
// */
//public class CrmsServiceTest {
//    @Mock
//    private HttpClientUtil httpClientUtil;
//    @InjectMocks
//    private CrmsService crmsService;
//
//    @BeforeMethod
//    public void beforeMethod() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        reset(httpClientUtil);
//    }
//    @Test
//    public void testGetAllWaitApportion() throws Exception {
//        List<WaitApportion> waitApportionList=new ArrayList<>();
//        when(httpClientUtil.httpGetWithType(any(),any())).thenReturn(waitApportionList);
//        waitApportionList=crmsService.getAllWaitApportion(anyString());
//        Assert.assertEquals(waitApportionList.size(),0);
//
//        List<WaitApportion> waitApportionList1=new ArrayList<>();
//        WaitApportion waitApportion=new WaitApportion();
//        waitApportionList1.add(waitApportion);
//        when( httpClientUtil.httpGet(anyString(),any())).thenThrow(Exception.class);
//        waitApportionList1=crmsService.getAllWaitApportion(anyString());
//        Assert.assertEquals(waitApportionList1.size(),0);
//    }
//
//    @Test
//    public void testGetAnalystFreeList() throws Exception {
//    }
//
//    @Test
//    public void testDeleteApportionByIds() throws Exception {
//    }
//
//}