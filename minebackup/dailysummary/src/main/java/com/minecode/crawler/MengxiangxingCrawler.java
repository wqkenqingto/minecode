package com.minecode.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/19
 * @desc
 */
public class MengxiangxingCrawler {
    public int test() throws IOException {
        int flag = 0;
        Document doc;
        for (int i = 0; i < 100; i++) {
            try {
                doc = Jsoup.connect("http://www.mxtrip.cn/poi/kWg2hW08hUg/").get();
                System.out.println(doc);
                System.out.println("访问了" + i + "次");
            } catch (Exception e) {
                flag = 1;
                System.out.println("访问了" + i + "次");
                System.out.println(e);
            }
        }
        return flag;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        MengxiangxingCrawler crawler = new MengxiangxingCrawler();
        int flag;
        long begin = System.currentTimeMillis();
        System.out.println("开始了.....");
        while (true) {
            flag = crawler.test();
            if (flag == 1) {
                break;
            }
            Thread.sleep(10 * 1000);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("结束了.....");
        System.out.println(endtime - begin);
    }
}
