package com.minecode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minecode.base.BaseController;
import com.minecode.model.EnumClass;
import com.minecode.utils.EmailProduct;
import com.minecode.utils.HbaseUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wqkenqing on 2017/9/19.
 */
@Controller
@RequestMapping("/test")
public class TestCrontller extends BaseController {


    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result rs = HbaseUtil.getRow("myself_introduce", "wangkuiqing");


        StringBuffer name = new StringBuffer();
        StringBuffer fname = new StringBuffer();
        StringBuffer value = new StringBuffer();


        Map<String, String> resultMap = new HashMap<String, String>();
        JSONObject jsonObject = new JSONObject();
        JSONObject skillObject = new JSONObject();
        JSONObject workObject = new JSONObject();
        for (KeyValue keyValue : rs.raw()) {

            name.append(new String(keyValue.getQualifier()));
            value.append(new String(keyValue.getValue()));
            fname.append(new String(keyValue.getFamily()));
            if (fname.toString().equals("skills")) {
                skillObject.put(name.toString(), value.toString());
            }
            if (fname.toString().equals("wexperience")) {
                workObject.put(name.toString(), value.toString());
            } else {
                jsonObject.put(name.toString(), value.toString());
            }
            name.setLength(0);
            value.setLength(0);
            fname.setLength(0);
        }

        ResultScanner scanner = HbaseUtil.getAllRows("blog_backup");

        int i = 1;
        Map<String, String> map;
        List<Map<String, String>> mlist = new ArrayList<Map<String, String>>();
        for (Result rss : scanner) {
            String blogname = "blog";
            blogname = blogname + i;
            map = new HashMap<>();

            for (KeyValue keyValue : rss.raw()) {
                name.append(new String(keyValue.getQualifier()));
                value.append(new String(keyValue.getValue()));
                map.put(name.toString(), value.toString());
                name.setLength(0);
                value.setLength(0);
            }
            mlist.add(map);
            i++;
            if (i == 11) {
                break;
            }

        }
        ResultScanner scanner1 = HbaseUtil.getAllRows("workintroduce");
        Map<String, String> map1;
        List<Map<String, String>> mlist1 = new ArrayList<Map<String, String>>();

        for (Result rr : scanner1) {
            map1 = new HashMap<>();
            for (KeyValue keyValue : rr.raw()) {
                name.append(new String(keyValue.getQualifier()));
                value.append(new String(keyValue.getValue()));
                map1.put(name.toString(), value.toString());
                name.setLength(0);
                value.setLength(0);
            }
            mlist1.add(map1);
        }


        String jarry = JSONArray.toJSONString(mlist);
        String jwork = JSONArray.toJSONString(mlist1);
        jsonObject.put("jarray", jarry);
        jsonObject.put("jwork", jwork);
        jsonObject.put("skill", skillObject);
        jsonObject.put("experience", workObject);
        printJson(response, JSON.toJSONString(jsonObject));

    }

    @RequestMapping(value = "sendinfo.htm", method = RequestMethod.POST)
    public void sendinformation(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        EmailProduct emailProduct = new EmailProduct(EnumClass.reciever, email, message, "");
        emailProduct.run();
        String result = "success";
        printJson(response, JSON.toJSONString(result));
    }

}
