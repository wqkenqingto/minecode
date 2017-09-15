package com.minecode.test;

import com.minecode.util.HbaseUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by wqkenqing on 2017/9/10.
 */
public class UsualTest {
    public static void main(String[] args) throws Exception {
//       //参数url化
        String city = java.net.URLEncoder.encode("北京", "utf-8");

        //拼地址
        String apiUrl = String.format("http://www.sojson.com/open/api/weather/json.shtml?city=%s",city);
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        Thread.sleep(3 * 1000);
        //这里转换为String，带上包名，怕你们引错包
        String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");

        //输出
        String text = Jsoup.connect(apiUrl).ignoreContentType(true).get().text();
        System.out.println(text);
        System.out.println(result);
    }
}
