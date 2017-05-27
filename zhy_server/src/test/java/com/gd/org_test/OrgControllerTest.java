//package com.gd.org_test;
//
//import com.gd.MaApplication;
//import com.gd.controller.OrgTreeController;
//import com.gd.controller.org.OrgController;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
///**
// * Created by Administrator on 2017/5/3.
// */
//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringApplicationConfiguration(classes = MaApplication.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//public class OrgControllerTest {
//    @Autowired
//    private OrgController orgController;
//    @Autowired
//    private OrgTreeController orgTreeController;
//    @Test
//    public void queryOrgList(){
//        String result = orgController.queryForList();
//        System.out.println("查询组织结构列表："+result);
//        /*
//        {"code":"0000","data":[
//        {"orgName":"航天长峰","leader":"张三","parentId":"root","parentName":"root","path":"root-航天长峰","id":"1","ifuse":"y","createTime":"2017-04-30 12:12:12","updateTime":"2017-04-30 12:12:12","orderNum":"1"},
//        {"orgName":"研发部","leader":"张三","parentId":"1","parentName":"航天长峰","id":"a07d1b93-855d-4636-a5ae-575f45d0f6b5","ifuse":"y","createTime":"2017-05-05 14:50:49","updateTime":"2017-05-05 14:50:49"},
//        {"orgName":"项目部","leader":"李四","parentId":"1","parentName":"航天长峰","id":"d6be153d-a133-43bd-a945-bf2570391e07","ifuse":"y","createTime":"2017-05-08 11:24:27","updateTime":"2017-05-08 11:24:27"}]}
//         */
//    }
//    @Test
//    public void deleteOrg(){
//        String id = "11";
//        String result = orgController.delete(id);
//        System.out.println("删除部门"+result);
//    }
//    @Test
//    public void queryOrgTree(){
//        String result = orgTreeController.getTree("1");
//        System.out.println("result:"+result);
//        /*
//        result:[{"children":[
//        {"orgName":"项目部","leader":"李四","parentId":"1","parentName":"航天长峰","id":"d6be153d-a133-43bd-a945-bf2570391e07","ifuse":"y","createTime":"2017-05-08 11:24:27","updateTime":"2017-05-08 11:24:27"},
//        {"orgName":"研发部","leader":"张三","parentId":"1","parentName":"航天长峰","id":"a07d1b93-855d-4636-a5ae-575f45d0f6b5","ifuse":"y","createTime":"2017-05-05 14:50:49","updateTime":"2017-05-05 14:50:49"}],
//        "orgName":"航天长峰","leader":"张三","parentId":"root","parentName":"root","path":"root-航天长峰","id":"1","ifuse":"y","createTime":"2017-04-30 12:12:12","updateTime":"2017-04-30 12:12:12","orderNum":"1"}]
//         */
//    }
//}
