package com.gd.controller.userinfo;

import com.gd.domain.account.Account;
import com.gd.domain.account_user.AccountUser;
import com.gd.domain.base.BaseModel;
import com.gd.domain.org.Org;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.account.IAccountService;
import com.gd.service.account_user.IAccountUserService;
import com.gd.service.org.IOrgService;
import com.gd.service.userinfo.IUserInfoService;
import com.gd.util.ExcelUtils;
import com.gd.util.TimeUtils;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


/**
 * Created by dell on 2017/4/5.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController",description = "用户相关api")
public class UserController {
    String filePath = "file/";

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrgService orgService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountUserService accountUserService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询用户列表",notes = "查询用户列表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String queryForUserList() {
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList = this.userInfoService.queryForObject(new UserInfo());
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("code", "0000");
        resultMap.put("data", userInfoList);
        //resultMap.put("total_count", userInfoList.size());
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增用户",notes = "新增用户",httpMethod = "POST",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String add(@RequestBody UserInfo user) {
        if (StringUtils.isEmpty(user.getId())) {
            user.setId(UUID.randomUUID().toString());
        }
        if (StringUtils.isEmpty(user.getCreateTime())) {
            user.setCreateTime(TimeUtils.getCurrentTime());
        }
        user.setUpdateTime(TimeUtils.getCurrentTime());
        if (StringUtils.isEmpty(user.getIfuse())) {
            user.setIfuse("y");
        }
        Map<String, Object> resultMap = new HashMap<>();
        int flag = 0;
        try {
            flag = this.userInfoService.insertUser(user);

        }catch (DuplicateKeyException e){
            resultMap.put("code", "0001");
            resultMap.put("data", "phone or policeNum repeat");
            Gson gson = new Gson();
            return gson.toJson(resultMap);
        }

        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0001");
            resultMap.put("data", "user insert failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户",notes = "删除用户",httpMethod = "DELETE",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    })
    public String delete(@PathVariable("id") String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        int flag = this.userInfoService.deleteUser(userInfo);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0011");
            resultMap.put("data", "user delete failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户状态",notes = "更新用户状态",httpMethod = "PUT",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType = "path")
    })
    public String updateUserStatus(@PathVariable("id") String id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户",notes = "更新用户",httpMethod = "PUT",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    public String updateUser(@RequestBody UserInfo userInfo) {
        BaseModel baseModel = new BaseModel();
        baseModel.setId(userInfo.getId());
        UserInfo oldUserInfo = userInfoService.queryObject(baseModel);
        if (StringUtils.isEmpty(userInfo.getCreateTime())) {
            String createTime = oldUserInfo.getCreateTime();
            userInfo.setCreateTime(createTime);
        }
        if(StringUtils.isEmpty(userInfo.getOrderNum())){
            String ordernum = oldUserInfo.getOrderNum();
            userInfo.setOrderNum(ordernum);
        }
        if (StringUtils.isEmpty(userInfo.getIfuse())) {
            userInfo.setIfuse("y");
        }
        userInfo.setUpdateTime(TimeUtils.getCurrentTime());
        int flag = this.userInfoService.updateUser(userInfo);
        Map<String, Object> resultMap = new HashMap<>();
        if (flag == 1) {
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
        } else {
            resultMap.put("code", "0011");
            resultMap.put("data", "user update failed");
        }
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }

    /*
    上传数据删除
    DELETE FROM sys_userinfo WHERE org LIKE '%北京%'
    DELETE FROM sys_account WHERE username LIKE '%user%'
    DELETE FROM sys_account_user WHERE id != 'au1' AND accountid!='a1'
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String ngUpload(HttpServletRequest request, HttpServletResponse res) {
        List<Map<String, String>> fileContentList = new ArrayList<>();
        Gson gson = new Gson();
        System.out.println("in");
        String userExcel = "";
//        //接收参数
//        int id= Integer.parseInt(request.getParameter("id"));
//        System.out.println("id=="+id);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //解析器解析request的上下文
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        //先判断request中是否包涵multipart类型的数据，
        if (multipartResolver.isMultipart(request)) {
            //再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //这里的name为fileItem的alias属性值，相当于form表单中name
                String name = (String) iter.next();
                System.out.println("name:" + name);
                //根据name值拿取文件
                MultipartFile file = multiRequest.getFile(name);
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    String path = filePath + fileName;
                    userExcel = path;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()) {
                        //如果目标文件所在的目录不存在，则创建父目录
                        localFile.getParentFile().mkdirs();
                        System.out.println("parent:" + localFile.getParentFile().getPath());
                    }
                    //写文件到本地
                    try {
                        //file.transferTo(localFile);
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        resultMap.put("code", "10001");
                        resultMap.put("data", "upload file error");
                        return gson.toJson(resultMap);
                    }
                }
            }
        }
        File file = new File(userExcel);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            try {
                fileContentList = ExcelUtils.readExcel(file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                resultMap.put("code", "10002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            } catch (FileFormatException e) {
                e.printStackTrace();
                resultMap.put("code", "10002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            }

            if (fileContentList.size() == 0) {
                resultMap.put("code", "10004");
                resultMap.put("data", "excel is null");
                return gson.toJson(resultMap);
            }
            for (int i = 0; i < fileContentList.size(); i++) {
                Map<String, String> userMap = new HashMap<>();
                userMap = fileContentList.get(i);
                if(userMap.size() == 22){
                    UserInfo userInfo = new UserInfo();
                    Account account = new Account();
                    userInfo.setId(UUID.randomUUID().toString());
                    userInfo.setCreateTime(TimeUtils.getCurrentTime());
                    userInfo.setUpdateTime(TimeUtils.getCurrentTime());
                    userInfo.setIfuse("y");
                    account.setId(UUID.randomUUID().toString());

                    //0：手机号
                    userInfo.setPhone(userMap.get("0"));
                    //1：用户id
                    account.setUsername(userMap.get("1"));
                    //2：密码
                    account.setPassword(userMap.get("2"));
                    //3：警号
                    userInfo.setPoliceNum(userMap.get("3"));
                    //4：即时通信ID
                    userInfo.setCommunicationId(userMap.get("4"));
                    //5：姓名
                    userInfo.setRealName(userMap.get("5"));
                    //6：性别代码
                    userInfo.setGender(userMap.get("6"));
                    //7：民族代码
                    userInfo.setNation(userMap.get("7"));
                    //8：籍贯代码
                    userInfo.setNativePlace(userMap.get("8"));
                    //9：出生日期
                    userInfo.setBirthDate(userMap.get("9"));
                    //10：政治面貌
                    userInfo.setPoliticalStatus(userMap.get("10"));
                    //11：婚姻状况
                    userInfo.setMaritalStatus(userMap.get("11"));
                    //12：身份证号
                    userInfo.setIdentityCode(userMap.get("12"));
                    //13：部门
                    userInfo.setOrg(userMap.get("13"));
                    //14：职位
                    userInfo.setStation(userMap.get("14"));
                    //15：家庭地址
                    userInfo.setHomeAddress(userMap.get("15"));
                    //16:办公地址
                    userInfo.setOfficeAddress(userMap.get("16"));
                    //17：办公电话
                    userInfo.setOfficeTelephone(userMap.get("17"));
                    //18：其他联系电话
                    userInfo.setOtherTelephone(userMap.get("18"));
                    //19：邮箱
                    userInfo.setMail(userMap.get("19"));
                    //20:appId
                    account.setAppId(userMap.get("20"));
                    int flag1 = this.userInfoService.insertUser(userInfo);
                    int flag2 = this.accountService.insertForObject(account);
                    AccountUser accountUser = new AccountUser();
                    accountUser.setId(UUID.randomUUID().toString());
                    accountUser.setAccountId(account.getId());
                    accountUser.setUserId(userInfo.getId());
                    int flag3 = this.accountUserService.insertForObject(accountUser);
                    if (flag1 != 1 && flag2 != 1 && flag3 != 1) {
                        resultMap.put("code", "10005");
                        resultMap.put("data", "insert error");
                        return gson.toJson(resultMap);
                    }
                }
            }

            file.delete();
            resultMap.put("code", "10000");
            resultMap.put("data", "excel upload success");
            return gson.toJson(resultMap);
        } else {
            resultMap.put("code", "10003");
            resultMap.put("data", "file not exists");
            return gson.toJson(resultMap);
        }


    }


    @RequestMapping(value = "/org/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取部门下所有用户",notes = "获取部门下所有用户",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
            @ApiResponse(code = 405, message = "Invalid input")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", required = true, dataType = "String", paramType = "path")
    })
    public String getUsersByOrg(@PathVariable("id") String id) {
        List<Org> orgList = new ArrayList<>();
        List<UserInfo> userList = new ArrayList<>();
        orgList = this.orgService.getAllLeaves(id);
        for (int i = 0; i < orgList.size(); i++) {
            List<UserInfo> uList = new ArrayList<>();
            UserInfo user = new UserInfo();
            user.setOrgId(orgList.get(i).getId());
            uList = this.userInfoService.queryForObject(user);
            userList.addAll(uList);
        }
        Gson gson = new Gson();
        return gson.toJson(userList);
    }
}
