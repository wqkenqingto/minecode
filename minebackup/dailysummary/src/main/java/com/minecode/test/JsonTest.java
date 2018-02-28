package com.minecode.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/6
 * @desc
 */
public class JsonTest {
    public static void main(String[] args) {
         String m = "{a:1,b:{c:2,d:[3, 4]}}";

        JSONObject json = JSONObject.parseObject(m);
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();

        for (String s : json.keySet()) {
            System.out.println(json.get(s));
        }

    }
}
