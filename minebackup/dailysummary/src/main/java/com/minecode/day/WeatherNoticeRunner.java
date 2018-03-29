package com.minecode.day;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.minecode.util.EmailProduct;

import java.io.IOException;
import java.util.List;

/**
 * Created by wqkenqing on 2017/9/19.
 */
public class WeatherNoticeRunner {
    public static void run(String city) throws IOException {
        String result = WeatherNoticeApi.getWeatherInfoAll(city);
        String result1 = WeatherNoticeApi.getWeatherInfoN(result);
        String result2 = WeatherNoticeApi.checkWeather(result1);

        String reciever = "wqkenqingto@163.com";
        String desc = "今天有雨，记得带伞";
        if (!StringUtils.isBlank(result2)) {
            EmailProduct emailProduct = new EmailProduct(reciever, desc, result2, "");
            emailProduct.run();
        }
        System.out.println(result1);
    }

    public static void main(String[] args) throws IOException {
//        String city = args[0];
        String city = "北京";
        run(city);
    }

}
