package com.gd.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * 用于连接ftp服务器，上传和下载文件
 */
public class FtpUtils {
    /**
     * 上传文件（可供Action/Controller层使用）
     * @param hostname FTP服务器地址
     * @param port FTP服务器端口号
     * @param username FTP登录帐号
     * @param password FTP登录密码
     * @param pathname FTP服务器保存目录
     * @param fileName 上传到FTP服务器后的文件名称
     * @param inputStream 输入文件流
     * @return
     */
    public static boolean uploadFile(String hostname, int port, String username, String password, String pathname, String fileName, InputStream inputStream){
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //是否成功登录FTP服务器
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }

            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            for(String s:pathname.split("/")){
                ftpClient.makeDirectory(s);
                ftpClient.changeWorkingDirectory(s);
            }
            //不用，因为centos无法创建多个目录
            //ftpClient.makeDirectory(pathname);
            //ftpClient.changeWorkingDirectory(pathname);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
    /**
     * 删除文件
     * @param hostname FTP服务器地址
     * @param port FTP服务器端口号
     * @param username FTP登录帐号
     * @param password FTP登录密码
     * @param pathnameAndFilename 删除文件全路径
     * @return
     */
    public static boolean deleteFile(String hostname, int port, String username, String password, String pathnameAndFilename){
        String[] strings = pathnameAndFilename.split("/");
        String filename=strings[strings.length - 1];
        String pathname=pathnameAndFilename.replace(filename,"");
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            ftpClient.dele(filename);
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }
    /**
     * 下载文件
     * @param hostname FTP服务器地址
     * @param port FTP服务器端口号
     * @param username FTP登录帐号
     * @param password FTP登录密码
     * @@param pathnameAndFilename 文件全路径
     * @param os 回写流
     * @return
     */
    public static boolean downloadFile(String hostname, int port, String username, String password,String pathnameAndFilename,OutputStream os){
        String[] strings = pathnameAndFilename.split("/");
        String filename=strings[strings.length - 1];
        String pathname=pathnameAndFilename.replace(filename,"");
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        try {
            //连接FTP服务器
            ftpClient.connect(hostname, port);
            //登录FTP服务器
            ftpClient.login(username, password);
            //验证FTP服务器是否登录成功
            int replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                return flag;
            }
            //切换FTP目录
            for(String s:pathname.split("/")){
                ftpClient.changeWorkingDirectory(s);
            }
            //ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    ftpClient.retrieveFile(file.getName(), os);
                }
            }
            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try {
                    ftpClient.logout();
                } catch (IOException e) {

                }
            }
        }
        return flag;
    }
}
