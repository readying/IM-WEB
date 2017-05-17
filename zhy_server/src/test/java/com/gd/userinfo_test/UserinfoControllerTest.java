//package com.gd.userinfo_test;
//
///**
// * 测试UserController
// */
//import com.gd.MaApplication;
//import com.gd.controller.userinfo.UserController;
//import com.gd.domain.userinfo.UserInfo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringApplicationConfiguration(classes = MaApplication.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//public class UserinfoControllerTest {
//    @Autowired
//    private UserController userController;
//    //查询列表
//    @Test
//    public void queryUserList(){
//        String userListJson = userController.queryForUserList();
//        System.out.println(userListJson);
//    }
//    //新增用户
//    @Test
//    public void addUser(){
//        UserInfo userInfo = new UserInfo("xx_realName", "xx_sphone", "xx_mail", "xx_org", "xx_orgId", "xx_station", "xx_gender", "xx_nation", "xx_nativePlace", "xx_birthDate", "xx_politicalStatus", "xx_maritalStatus", "xx_nickName", "xx_picture", "xx_s111", "xx_identityCode", "xx_qr", "xx_qq", "xx_weChat", "xx_jobCode", "xx_autoGraph", "xx_homeAddress", "xx_communicationId", "xx_officeAddress", "xx_otherAddress", "xx_officeTelephone", "xx_otherTelephone", "xx_ifHideNum");
//        String result = userController.add(userInfo);
//        System.out.println("执行结果："+result);
//    }
//    //更新用户
//    @Test
//    public void updateUser(){
//        UserInfo userInfo = new UserInfo("cc_realName", "ss_phone", "ss_mail", "ss_org", "ss_orgId", "ss_station", "ss_gender", "ss_nation", "ss_nativePlace", "ss_birthDate", "ss_politicalStatus", "ss_maritalStatus", "ss_nickName", "ss_picture", "ss_policeNum", "ss_identityCode", "ss_qr", "ss_qq", "ss_weChat", "ss_jobCode", "ss_autoGraph", "ss_homeAddress", "ss_communicationId", "ss_officeAddress", "ss_otherAddress", "ss_officeTelephone", "ss_otherTelephone", "ss_ifHideNum");
//        userInfo.setId("fa737640-883c-4211-b8ac-28b6c5a12843");
//        String result = userController.updateUser(userInfo);
//        System.out.println("执行结果："+result);
//    }
//    //删除用户
//    @Test
//    public void deleteUser(){
//        String id = "fa737640-883c-4211-b8ac-28b6c5a12843";
//        String result = userController.delete(id);
//        System.out.println("执行结果："+result);
//    }
//}
