package com.gd.controller.file;

import com.gd.domain.userinfo.UserInfo;
import com.gd.service.userinfo.IUserInfoService;
import com.gd.util.FtpUtils;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理文件上传下载的
 */
@Controller
@RequestMapping("/file")
public class FileUploadDownload {
    @Value("${ftp.hostname}")
    private String ftpHostname;
    @Value("${ftp.port}")
    private int ftpPort;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;
    @RequestMapping("/fileUI")
    public String greeting() {
        return "fileUI";
    }
    @Autowired
    private IUserInfoService userInfoService;
    private static final Logger logger = LoggerFactory.getLogger(FileUploadDownload.class);
    //上传文件(图片)代码
    @RequestMapping(value = "/fileOrImageUpload",method = RequestMethod.POST)
    @ApiOperation(value = "文件或图片上传", httpMethod = "POST", notes = "文件或图片上传")
    @ResponseBody
    public String fileOrImageUpload(@RequestParam("id") String id,@RequestParam("file") MultipartFile file,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        Gson gson = new Gson();
        Map<String, Object> resultMap = new HashMap<>();
        if (file.isEmpty()) {
            resultMap.put("code", "0001");
            resultMap.put("data", "file is empty");
            return gson.toJson(resultMap);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String savePath = "commandFile/"+id;
        //文件上传后的名称
        String saveName = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date())+"_"+fileName;
        try {
            UserInfo userInfo = userInfoService.queryForObjectById(id);
            //首先移除已存在的文件
            FtpUtils.deleteFile(ftpHostname,ftpPort,ftpUsername,ftpPassword,userInfo.getPicture());
            //使用ftp工具来上传文件
            FtpUtils.uploadFile(ftpHostname,ftpPort,ftpUsername,ftpPassword,savePath,saveName,file.getInputStream());

            userInfo.setPicture(savePath+"/"+saveName);
            userInfoService.updateForObject(userInfo); ;
            resultMap.put("code", "0000");
            resultMap.put("data", "success");
            return gson.toJson(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("code", "0001");
            resultMap.put("data", "faild");
            return gson.toJson(resultMap);
        }
    }
    //查看上传的图片
    @RequestMapping(value = "/showImage",method = RequestMethod.GET)
    @ApiOperation(value = "查看图片", httpMethod = "GET", notes = "查看图片")
    @ResponseBody
    public void showImage(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods","GET"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type"); //允许哪些请求头可以跨域
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
        UserInfo userInfo = userInfoService.queryForObjectById(id);
        String picturePath = userInfo.getPicture();
        OutputStream os = response.getOutputStream();
        try
        {
            FtpUtils.downloadFile(ftpHostname,ftpPort,ftpUsername,ftpPassword,picturePath,os);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (os != null)
                os.close();
        }
    }

}
