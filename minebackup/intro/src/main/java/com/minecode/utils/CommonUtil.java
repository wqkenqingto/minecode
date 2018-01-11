package com.minecode.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minecode.ip.ProxyCralwerUnusedVPN;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
*@className:CommonUtil
*@author:wqkenqing
*@describe:一些常用的工具方法
*@date:2017/8/18
**/
public class CommonUtil {
    static List<String> iplist = new ArrayList<String>();

    public static String stream2String(InputStream in, String charset) {
        StringBuffer sb = new StringBuffer();
        try {
            Reader r = new InputStreamReader(in, charset);
            int length = 0;
            for (char[] c = new char[1024]; (length = r.read(c)) != -1; ) {
                sb.append(c, 0, length);
            }
            r.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    //输出对应的url
    public static void outputHtml(String listurl, String tag, String outbaseurl) throws IOException {
        String outpath = outbaseurl + tag + ".txt";
        OutputStream out = new FileOutputStream(outpath);
        out.write(listurl.getBytes());
    }

    //切换ip代理
    public static List<String> changeIp(int number) {
        ProxyCralwerUnusedVPN proxyChange = new ProxyCralwerUnusedVPN();

        String proxyIpInfo = "";
        while (proxyIpInfo.equals("")) {
            proxyIpInfo = proxyChange.startCrawler(number);
        }

        JSONObject jsonObject = JSONObject.parseObject(proxyIpInfo);
        JSONObject jsonData = jsonObject.getJSONObject("data");
        JSONArray jsonProxy = jsonData.getJSONArray("proxy");
        List<String> iplist = new ArrayList<String>();
        for (Object object : jsonProxy) {
            JSONObject j = JSONObject.parseObject(object.toString());
            String port = (String) j.get("port");
            String ip = (String) j.get("ip");
            iplist.add(port + "!" + ip);
        }
        return iplist;
    }


}
