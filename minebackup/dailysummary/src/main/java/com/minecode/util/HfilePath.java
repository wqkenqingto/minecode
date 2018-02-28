package com.minecode.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wangkuiqing
 * @Date 2016/11/9 16:02
 * @ClassName:HfilePath
 * @Description:获取指定文件夹下的文件路径
 */
public class HfilePath {
    static List<String> hpaths = new ArrayList<String>();

    public static List<String> getHfilePath(Configuration conf, String hfilePath) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(hfilePath), conf);
        Path path = new Path(hfilePath);
        if (fs.isDirectory(path)) {
            FileStatus[] status = fs.listStatus(path);
            for (FileStatus f : status) {
                getHfilePath(conf, f.getPath().toString());
            }
        } else {
//            System.out.println(path.toString());
//            if(path.toString().contains("msg-r-")) {
            if (!path.toString().contains("_SUCCESS")) {
                hpaths.add(path.toString());
                System.out.println(path.toString());
            }
//            }
        }
        return hpaths;
    }

    public static List<String> getHfilePathForExcel(Configuration conf, String hfilePath, String suffix) throws IOException, ParseException {
        FileSystem fs = FileSystem.get(URI.create(hfilePath), conf);
        Path path = new Path(hfilePath);
        String fileName = "";
        Long suffixTime = DateToLong(suffix);
        if (fs.isDirectory(path)) {
            FileStatus[] status = fs.listStatus(path);
            for (FileStatus f : status) {

                String[] fes = f.getPath().toString().split("/"); //将path分割
                fileName = fes[fes.length - 1];
                Long time = DateToLong(fileName);
                if (time <= suffixTime) {
                    hpaths.add(f.getPath().toString());
                }
            }
        } else {
            hpaths.add(hfilePath);
        }
        return hpaths;
    }

    //针对AutoExecl的需求，会需要将以时间存的文件转成Long,方便比较
    public static Long DateToLong(String time) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date date = dateFormat.parse(time);
        return date.getTime();
    }

    static Configuration conf = new Configuration();
    static String hfilePah1 = "hdfs://test-hadoop-4:8020/test/";
    static String hfilePah2 = "hdfs://hadoop221:8020/temp/auto";
    static String suffix = "2017-03-21";

    public static void main(String[] args) throws IOException, ParseException {
        hpaths = getHfilePathForExcel(conf, hfilePah2, suffix);
        System.out.println(hpaths);
    }
}
