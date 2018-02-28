package com.minecode.day;

import com.alibaba.dubbo.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by wqkenqing on 2017/9/19.
 * 天气预报接口
 * target:实现天气的推送，预想效果是实现当查询到有雨天气时，发送邮件提醒
 */
public class WeatherNoticeApi {
    static String city = "";
    static String time = "";
    static Map<String, Object> needMap;

    static {
        needMap = new HashMap<String, Object>();
        needMap.put("yd_s", "运动指数");
        needMap.put("status1", "天气状况");
        needMap.put("status1", "天气状况");
        needMap.put("city", "城市");
        needMap.put("temperature2", "最低温度");
        needMap.put("temperature1", "最高温度");
        needMap.put("zwx_s", "紫外线强度");
        needMap.put("udatetime", "时间");
    }

    public static String getWeatherInfoAll(String city) throws IOException {
        String url = "http://php.weather.sina.com.cn/xml.php?city=";
        city = URLEncoder.encode(city, "gbk");
        String suffix = "&password=DJOYnieT8234jlsK&day=0 ";
        url = url + city + suffix;
        System.out.println(url);
        InputStreamReader reader = null;
        BufferedReader in = null;
        JSONObject data = new JSONObject();
        try {
            URL url1 = new URL(url);
            URLConnection connection = url1.openConnection();
            connection.setConnectTimeout(1000);
            reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            in = new BufferedReader(reader);
            String line = null; // 每行内容
            StringBuffer content = new StringBuffer();
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            SAXBuilder sb = new SAXBuilder();
            InputStream in1 = new ByteArrayInputStream(content.toString().getBytes());
            org.jdom.Document doc = sb.build(in1);
            Element root = doc.getRootElement();
            data.put(root.getName(), iterateElement(root));

        } catch (Exception e) {


        }
        if (!data.getJSONObject("Profiles").containsKey("Weather")) {
            return "";
        }
        JSONArray jarry = data.getJSONObject("Profiles").getJSONArray("Weather");
        JSONObject jobj = jarry.getJSONObject(0);
        Iterator iterator = jobj.keySet().iterator();

        return com.alibaba.fastjson.JSONObject.toJSONString(jobj);

    }

    public static String getWeatherInfoN(String result) {
        if (StringUtils.isBlank(result)) {
            return "";
        }
        JSONObject jobj = JSONObject.fromObject(result);
        JSONObject jobj2 = new JSONObject();
        Iterator iterator = jobj.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (needMap.get(key) != null) {
                jobj2.put(needMap.get(key), jobj.get(key));
            }
        }
        return com.alibaba.fastjson.JSONObject.toJSONString(jobj2);
    }

    private static Map<String, Object> iterateElement(Element element) {
        List<Element> jiedian = element.getChildren();
        Element et = null;
        Map<String, Object> obj = new HashMap<String, Object>();
        List<Object> list = null;
        for (int i = 0; i < jiedian.size(); i++) {
            list = new LinkedList<Object>();
            et = (Element) jiedian.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    list = (List<Object>) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List<Object>) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }

    public static String checkWeather(String s) {
        String result = "";
        if (s.contains("雨")) {
            result = "今天有雨,记得带伞";
            return s;
        }
        return "";
    }
}
