package com.gd.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 保存<username,token>对应的map，用户控制用户重复登录
 */
public class MyUsernameTokenUtils {
    private static Map map = null;
    public static Map<String,String> getMyUsernameTokenMap(){
        if(map == null){
            map= new HashMap<String,String>();
        }
        return  map;
    }
}
