package com.gd.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的内存的map，用于存储security中<token,权限>
 */
public class MySessionMapUtils {
    private static Map map = null;
    public static Map<String,Collection> getMySessionMap(){
        if(map == null){
            map= new HashMap<String,Collection>();
        }
        return  map;
    }
}
