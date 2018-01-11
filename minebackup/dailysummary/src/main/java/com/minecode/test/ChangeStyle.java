package com.minecode.test;

import com.minecode.util.CommonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/12
 * @desc
 */
public class ChangeStyle {
    public static void main(String[] args) {
        String basepath = "/Users/wqkenqing/Desktop/sqlcsv";
        String outpath = "/Users/wqkenqing/Desktop/sqlcsv/out/";
        String files = "order_customer_info.csv\n" +
                "order_detail.csv\n" +
                "order_flight.csv\n" +
                "order_flight_detail.csv\n" +
                "order_main.csv\n" +
                "order_main_expand.csv\n" +
                "order_mts_detail.csv\n" +
                "order_pay_info.csv";
        String[] fes = files.split("\n");
        List<String> flist = Arrays.asList(fes);


        flist.forEach(s -> {
            //
            try {
                InputStream in = new FileInputStream(basepath + "/" + s);
                String fileds = CommonUtil.stream2String(in, "utf8");
                fileds = fileds.replace("\"", "");
                fileds = fileds.replace(",", "\n");
                CommonUtil.outputHtml(fileds,s.replace(".csv",""),outpath);
//                Arrays.asList(ffs).forEach(f -> {
//                    CommonUtil.ou
//
//                        }
//                );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
