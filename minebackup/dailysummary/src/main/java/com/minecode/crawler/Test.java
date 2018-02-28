package com.minecode.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/2/2
 * @desc
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://blog.csdn.net/u010936936/article/details/77945084").get();
        String text = doc.text();
        System.out.println(text);
    }
}
