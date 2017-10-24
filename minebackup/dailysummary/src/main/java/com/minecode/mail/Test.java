package com.minecode.mail;

import com.minecode.day.WeatherNoticeApi;
import com.minecode.util.EmailProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqkenqing on 2017/9/20.
 */
public class Test {
    public static void main(String[] args) {
        String reciever = "wqkenqingto@163.com";
        String desc = "天气状况";
        WeatherNoticeApi.checkWeather("");
//        EmailProduct emailProduct = new EmailProduct(reciever,desc,);
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            sb.append("name" + i);
            map.put(sb.toString(), "jj");
            sb.setLength(0);
        }
        System.out.println(map);
    }
}
