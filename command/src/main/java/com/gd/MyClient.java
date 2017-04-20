package com.gd;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/4/11.
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        String username = "Lion_account";
        String password = "111111";
        String loginUrl = "http://localhost:9090/v1.0/ma/login";
        //登录,登录的方式就是POST提交请求
        String token = myLoginRequest(username,password,loginUrl);
        System.out.println("登录成功："+token);
        //这里使用GET请求来获取数据
        System.out.println("获取get请求的message！！");
        String url = "http://localhost:9090/v1.0/ma/userinfo/userinfos";
        String messageByGet = useTokenGetMessageForGet(url,token);
        System.out.println(messageByGet);
    }
    //POST请求，就是讲请求参数数据放到请求体里面
    public static String myLoginRequest(String username, String password,String loginUrl) throws IOException {
        String parameterData = "username="+username+"&password="+password;
        URL localURL = new URL(loginUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) localURL.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset","utf-8");
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length",String.valueOf(parameterData.length()));
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(parameterData.toString());
            outputStreamWriter.flush();
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null){
                resultBuffer.append(tempLine);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultBuffer.toString();
    }
    //GET请求就是讲请求参数，也就是token放在请求url的？后面
    public static String useTokenGetMessageForGet(String forUrl,String token) throws  Exception{
        //final String spec = forUrl+"?token="+token;
        final String spec = forUrl;
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        //向请求头中加入token
        httpURLConnection.setRequestProperty("token",token);
        if (httpURLConnection.getResponseCode() == 200) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            httpURLConnection.getInputStream()))) {
                String tempLine = null;
                StringBuffer resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                }
                return resultBuffer.toString();
            }
        }else{
                return "获取信息失败"+httpURLConnection.getResponseCode();
        }
    }
}
