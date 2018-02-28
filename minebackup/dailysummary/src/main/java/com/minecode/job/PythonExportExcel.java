package com.minecode.job;

import com.minecode.mail.MailSend;
import com.minecode.util.DBUtil;
import com.minecode.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @className: PythonExportExcel
 * @author: kuiq.wang@hnair.com
 * @date: 2018/1/11 下午3:57
 * @describe: 一些python excel job的导出
 **/
public class PythonExportExcel {

    static Logger logger = LoggerFactory.getLogger(PythonExportExcel.class);
    static String SPLIT_TAG = ";";

    public static String initDBtoExcel(String dbname, String sqlname, String tname, String[] sheetname) throws SQLException, IOException, ClassNotFoundException {

        DBUtil.initDataSource(dbname,"");
        Connection con = DBUtil.getConnection();
        int index = 0;
        if (sqlname.contains(SPLIT_TAG)) {
            String[] sqls = sqlname.split(SPLIT_TAG);

            for (String s : sqls) {
                System.out.println(s);
                if (!StringUtils.isEmpty(DBUtil.getSql(s)) || !StringUtils.isBlank(DBUtil.getSql(s))) {
                    createExcelContent(con, s, sheetname[index]);
                    if (sqls.length == sheetname.length) {
                        index++;
                    }
                }
            }

        } else {
            createExcelContent(con, sqlname, sheetname[index]);
        }


        String attach = ExcelUtil.createExcel(tname);
        logger.info("excel表生成成功....");
        return attach;
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        String attach = initDBtoExcel("db_promotion", "coupon_cost;coupon_receive", "优惠券发行与使用统计1-11", new String[]{"优惠券使用", "优惠券发行"});
//        String attach = initDBtoExcel("db_order", "flihgt_order1;hotel_order;hotel_order_now;order_coupon", "订单统计");
//        String attach = initDBtoExcel("db_order", "flihgt_order1;hotel_order;hotel_order_now;coupon_cost_day;coupon_receive_day;order_coupon", "订单统计测试", new String[]{"机票订单", "酒店订单", "酒店现付订单", "优惠券使用", "优惠券发行", "所有订单"});
//        String attach = initDBtoExcel("db_order", "flihgt_order", "机票测试表1", new String[]{""});
//        String attach = initDBtoExcel("db_order", "order_coupon", "优惠券订单", new String[]{""});
//        String reciever = "125323997@qq.com";

        if (args.length != 5) {
            logger.error("参数全,请重新配置");
        }
        String reciever = args[0];
        String dbase = args[1];
        String sqlname = args[2];
        String tname = args[3];
        String sheetname = args[4];
        String[] sheets = new String[]{};
        if (sheetname.contains(SPLIT_TAG)) {
            sheets = sheetname.split(SPLIT_TAG);
        } else {
            sheets[0] = sheetname;
        }
        LocalDate day = LocalDate.now();
        tname = day + tname;
        String attach = initDBtoExcel(dbase, sqlname, tname, sheets);
        MailSend.send(attach, reciever);


    }

    public static void createExcelContent(Connection con, String sqlname, String sheetname) throws SQLException, FileNotFoundException, ClassNotFoundException {

        Statement stat = con.createStatement();

        ResultSet rset = stat.executeQuery(DBUtil.getSql(sqlname));
        List<String> slist = new ArrayList<>();
        StringBuffer sb;
        while (rset.next()) {
            sb = new StringBuffer();
            int count = rset.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                sb.append(rset.getString(i));
                sb.append(";");
            }
            slist.add(sb.toString());
        }
        System.out.println(sqlname + "完成了" + slist.size() + "条记录");
        if (sheetname.equals("")) {
            sheetname = sqlname;
        }
        String header = ExcelUtil.getExcelHead(sqlname);
        ExcelUtil.createSheetAndheader(sheetname, header);
        for (String s : slist) {
            ExcelUtil.createRowofSheet(sheetname, s);
        }

    }
}
