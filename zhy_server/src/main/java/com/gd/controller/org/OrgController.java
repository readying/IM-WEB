package com.gd.controller.org;

import com.gd.domain.base.BaseModel;
import com.gd.domain.org.Org;
import com.gd.domain.userinfo.UserInfo;
import com.gd.service.org.IOrgService;
import com.gd.service.orgtree.IOrgTreeService;
import com.gd.util.ExcelUtils;
import com.gd.util.TimeUtils;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by dell on 2017/4/30.
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
@RequestMapping("/org")
@Api(value = "OrgController",description = "组织机构相关api")
public class OrgController {
    String filePath = "file/";
    @Autowired
    IOrgService orgService;
    @Autowired
    IOrgTreeService orgTreeService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询组织机构列表",notes = "查询组织机构列表",httpMethod = "GET",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "No Privilege"),
    })
    public String queryForList() {
        List<Org> userInfoList = new ArrayList<>();
        userInfoList = this.orgService.queryForObject(new Org());
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("code", "0000");
        resultMap.put("data", userInfoList);
        //resultMap.put("total_count", userInfoList.size());
        Gson gson = new Gson();
        return gson.toJson(resultMap);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除组织机构",notes = "删除组织机构",httpMethod = "DELETE",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "组织机构id", required = true, dataType = "String", paramType = "path")
    })
    public String delete(@PathVariable("id") String id) {
        BaseModel baseModel = new BaseModel();
        baseModel.setId(id);
        //查询这个org下面是否存在org
        List<Org> orgList = this.orgService.queryChildernByOrg(baseModel);
        //查询这个org下面是否存在人
        List<UserInfo> userInfoList = this.orgService.queryChildernByUserInfo(baseModel);
        Map<String, Object> resultMap = new HashMap<>();
        Gson gson = new Gson();
        if(orgList.size() != 0){
            resultMap.put("code", "0001");
            resultMap.put("data", "org has org children by num = "+orgList.size());
            return gson.toJson(resultMap);
        }else if(userInfoList.size() != 0){
            resultMap.put("code", "0001");
            resultMap.put("data", "org has userinfo children by num = "+userInfoList.size());
            return gson.toJson(resultMap);
        }else {
            int flag = this.orgService.deleteOrg(baseModel);
            if(flag == 1){
                resultMap.put("code", "0000");
                resultMap.put("data", "delete success");
                return gson.toJson(resultMap);
            }else {
                resultMap.put("code", "0001");
                resultMap.put("data", "delete faild");
                return gson.toJson(resultMap);
            }
        }
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String ngUpload(HttpServletRequest request, HttpServletResponse res) {
        List<Map<String, String>> fileContentList = new ArrayList<>();
        Gson gson = new Gson();
        String orgExcel = "";
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
                //根据name值拿取文件
                MultipartFile file = multiRequest.getFile(name);
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    String path = filePath + fileName;
                    orgExcel = path;
                    File localFile = new File(path);
                    if (!localFile.getParentFile().exists()) {
                        localFile.getParentFile().mkdirs();
                    }
                    try {
                        //file.transferTo(localFile);
                        if (!localFile.exists()) {
                            localFile.createNewFile();
                        }
                        FileUtils.copyInputStreamToFile(file.getInputStream(), localFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        resultMap.put("code", "11001");
                        resultMap.put("data", "upload file error");
                        return gson.toJson(resultMap);
                    }
                }
            }
        }
        File file = new File(orgExcel);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            try {
                fileContentList = ExcelUtils.readExcel(file.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                resultMap.put("code", "11002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            } catch (FileFormatException e) {
                e.printStackTrace();
                resultMap.put("code", "11002");
                resultMap.put("data", "parase excel error");
                return gson.toJson(resultMap);
            }

            if (fileContentList.size() == 0) {
                resultMap.put("code", "11004");
                resultMap.put("data", "excel is null");
                return gson.toJson(resultMap);
            }

            for (int i = 0; i < fileContentList.size(); i++) {
                Map<String, String> orgMap = new HashMap<>();
                orgMap = fileContentList.get(i);
                Org org = new Org();
                org.setId(UUID.randomUUID().toString());
                org.setCreateTime(TimeUtils.getCurrentTime());
                org.setUpdateTime(TimeUtils.getCurrentTime());
                org.setIfuse("y");

                org.setLeader(orgMap.get("1"));

                String content = orgMap.get("0");

                int left = content.indexOf("-");
                int right = content.lastIndexOf("-");
                if (left == -1 || left != right) {
                    resultMap.put("code", "11007");
                    resultMap.put("data", "org invalid");
                    return gson.toJson(resultMap);
                }
                String[] array = new String[2];
                array = content.split("-");

                String parentName = array[0];
                String name = array[1];

                if (StringUtils.isEmpty(parentName) || StringUtils.isEmpty(name)) {
                    resultMap.put("code", "11006");
                    resultMap.put("data", "unkonwn error");
                    return gson.toJson(resultMap);
                }
                Org o = new Org();
                o.setOrgName(parentName);
                List<Org> orgList = this.orgService.queryForObject(o);
                if (orgList.size() == 0) {
                    resultMap.put("code", "11007");
                    resultMap.put("data", "parent org not exists");
                    return gson.toJson(resultMap);
                }
                Map<String, String> paraMap = new HashedMap();
                paraMap.put("id", UUID.randomUUID().toString());
                paraMap.put("orgId", orgList.get(0).getId());
                paraMap.put("childrenId", org.getId());
                int flag2 = this.orgTreeService.insert(paraMap);
                if (flag2 !=1){
                    resultMap.put("code", "11008");
                    resultMap.put("data", "insert org fail");
                    return gson.toJson(resultMap);
                }
                org.setParentName(parentName);
                org.setParentId(orgList.get(0).getId());
                org.setOrgName(array[1]);
                int flag = this.orgService.insertOrg(org);
                if (flag != 1) {
                    resultMap.put("code", "11005");
                    resultMap.put("data", "insert error");
                    return gson.toJson(resultMap);
                }
            }

            file.delete();
            resultMap.put("code", "10002");
            resultMap.put("data", "parase excel error");
            return gson.toJson(resultMap);
        } else {
            resultMap.put("code", "10003");
            resultMap.put("data", "file not exists");
            return gson.toJson(resultMap);
        }


    }
}
