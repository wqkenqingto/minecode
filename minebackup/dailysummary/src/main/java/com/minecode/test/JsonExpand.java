package com.minecode.test;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/11/6
 * @desc:按题意将多层级json，转化成单级json,并以A.B.C形式作为key
 */
public class JsonExpand {
    protected final static Log logger = LogFactory.getLog(JsonExpand.class);
    static String m = "{a:1,b:{c:{e:5}},d:[3, 4]}";
    static String common = ".";
//    static String m = "{a:1,b:{c:2,d:[3, 4]}}";


    //分析可知，主要是value为json，则value json中的值以key.*的形式转化 原始json可看成key为null,主要解题思路是当value为json时触发转换方法
    public static JSONObject convertJson(String key, String value) {
        JSONObject jsonObject = new JSONObject();

        if (checkJson(value)) {
            JSONObject jobj = (JSONObject) new JSONObject().parse(value);
            for (String k : jobj.keySet()) {
                String ktemp = "";
                if (!key.equals("")) {
                    ktemp = key + common + k;
                } else {
                    ktemp = key + k;
                }
                if (checkJson(jobj.get(k) + "")) {
                    JSONObject jobj2 = convertJson(ktemp, jobj.get(k).toString());
                    jsonObject.putAll(jobj2);
                } else {
                    jsonObject.put(ktemp, jobj.get(k));

                }

            }
        }
        return jsonObject;
    }

    public static Boolean checkJson(String val) {
        if (StringUtils.isBlank(val)) {
            return false;
        }
        try {
            JSONObject obj = (JSONObject) new JSONObject().parse(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("转化以下json串");
        System.out.println(m);
        String result = JSONObject.toJSONString(convertJson("", m));
        System.out.println("转换结果为");
        System.out.println(result);
    }
}
