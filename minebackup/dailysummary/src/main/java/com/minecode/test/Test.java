package com.minecode.test;


import com.minecode.util.CommonUtil;
import com.minecode.util.HbaseUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;
import scala.sys.SystemProperties;
import scala.sys.process.ProcessBuilderImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/22
 * @desc
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        HbaseUtil.getRowShow("blog_backup", "1506004640065");
//        HbaseUtil.getRowShow("myself_introduce", "wangkuiqing");
//            HbaseUtil.addRow("myself_introduce","wangkuiqing",);
//        HbaseUtil.addRow("myself_introduce", "wangkuiqing", "profile", "selfnet", "www.wisdomkiki.com");
//        HbaseUtil.checkTableStructureShow("myself_introduce");
//        HbaseUtil.addRowFamily("myself_introduce","wexperience");
//                HbaseUtil.checkTableStructureShow("myself_introduce");
//        InputStream in = new FileInputStream("/Users/wqkenqing/Desktop/skills");
//        String s = CommonUtil.stream2String(in, "utf-8");
//        String[] ss = s.split("\n");
//        for (String row : ss) {
//            String skill = row.split("\t")[0];
//            String process = row.split("\t")[1];
//         HbaseUtil.addRow("myself_introduce","wangkuiqing","skills",skill,process);
//        }

        //wexperience title
//        String[] exps = {"experience"};
//        HbaseUtil.createTable("workintroduce",exps);
//        String title = "";
//        String time = "";
//        String duty = "";
//        String desc = "";
//        InputStream in = new FileInputStream("/Users/wqkenqing/Desktop/工作经验");
//        String work = CommonUtil.stream2String(in, "utf8");
//        String[] works = work.split("---");
//        for (String exps : works) {
//
//            String[] es = exps.trim().split("\n");
//            title = es[0];
//            time = es[1];
//            duty = es[2];
//            desc = es[3];
//            String row = System.currentTimeMillis() + "";
//
//            HbaseUtil.addRow("workintroduce", row, "experience", "title", title);
//            HbaseUtil.addRow("workintroduce", row, "experience", "time", time);
//            HbaseUtil.addRow("workintroduce", row, "experience", "duty", duty);
//            HbaseUtil.addRow("workintroduce", row, "experience", "desc", desc);
//        }
//        HbaseUtil.getAllRowsShow("workintroduce");
        HbaseUtil.getAllRowsShow("city_id_list");
    }

}
