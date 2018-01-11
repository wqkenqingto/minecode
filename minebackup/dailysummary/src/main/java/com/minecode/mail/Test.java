package com.minecode.mail;

import com.minecode.day.WeatherNoticeApi;
import com.minecode.util.EmailProduct;
import org.apache.lucene.search.suggest.tst.TSTAutocomplete;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqkenqing on 2017/9/20.
 */
public class Test {
    public static void main(String[] args) throws URISyntaxException {
        Test test = new Test();
        String path = test.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().toString();
        int lastIndexOf = path.lastIndexOf("/");
        path.substring(0, lastIndexOf);
        System.out.println(path);

    }
}
