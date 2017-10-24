package com.minecode.myself.profile;

import com.minecode.util.HbaseUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/24
 * @desc 同步个人profile至hbase
 */
public class UploadInfoToHbase {
    //1.建立相关表 myself_introduce
    public static void createTable() throws Exception {
        //建表
        String[] args = new String[]{"profile"};
        HbaseUtil.createTable("myself_introduce", args);
    }

    public static void main(String[] args) throws Exception {
//        System.out.println("开始建表");
//        createTable();
//        System.out.println("建表结束");
//        Map<String, String> profileMap = new HashMap<String, String>();
//        profileMap.put("name", "王奎清");
//        profileMap.put("birthday", "1993.10.2");
//        profileMap.put("address", "北京昌平");
//        profileMap.put("email", "wqkenqingto@163.com");
//        profileMap.put("phone", "13001038696");
//        profileMap.put("selfnet", "www.wqkenqing.ren");
//        for (String key : profileMap.keySet()) {
//            System.out.println(key);
//            HbaseUtil.addRow("myself_introduce", "wangkuiqing", "profile", key, profileMap.get(key));
//        }
        HbaseUtil.getAllRowsShow("myself_introduce");
    }
}
