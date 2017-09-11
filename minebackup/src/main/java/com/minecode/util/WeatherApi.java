package com.minecode.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.jsoup.Jsoup;

/**
 * Created by wqkenqing on 2017/9/1.
 */
public class WeatherApi {


    public static String getCityWeather(String cityname) throws Exception {
        String cityid = getCityIdFromHbase(cityname);
//        String cityid = "101320101";
        if (StringUtils.isBlank(cityid)) {
            return "";
        }
        String apipre = "http://www.weather.com.cn/data/cityinfo/";
        String sufix = ".html";
        String sufixt = "?_";
        long time = System.currentTimeMillis();
        String api = apipre + cityid + sufix + sufixt + time;
//        System.out.println(api);
        String s = Jsoup.connect(api).ignoreContentType(true).get().text();
        return s;
    }

    public static String getCityIdFromHbase(String cname) throws Exception {

        Result result = HbaseUtil.getRow("city_id_list", cname);
        if (result == null) {
            return "";
        }
        String id = "";
        try {
            id = new String(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("id")));
        } catch (Exception e) {
            id = "";
        }
        return id;
    }

    public static void main(String[] args) throws Exception {
//        //通过城市名称获取地点天气信息
        String cityname = "海淀";
        String weather = getCityWeather(cityname);
        System.out.println(weather);

//        String id = getCityIdFromHbase(cityname);
//        System.out.println(id);
    }
}
