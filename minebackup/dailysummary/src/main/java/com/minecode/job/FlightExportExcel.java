package com.minecode.job;

import com.minecode.mail.MailSend;
import com.minecode.util.DBUtil;
import com.minecode.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @className: FlightExportExcel
 * @author: kuiq.wang@hnair.com
 * @date: 2018/1/5 下午1:59
 * @describe:机票的定时导出任务
 **/
public class FlightExportExcel {

    static String tag = "'";
    static String sql_haigo = "select info.product_name, main.order_time,main.total_price ,main.order_no ,main.depart_city_name,main.arr_city_name ,main.depart_date from db_order.order_main_expand expand left join db_order.order_main main on main.order_no=expand.order_no \n" +
            "left join db_ucenter.user_base_info inf on inf.user_id=main.user_id  \n" +
            "left join order_pay_info info on (main.order_no=info.order_no )\n" +
            "where supplier_id=11  and user_type=0 and  main.order_status in (194,195,404,405,412,413,414,420,302,300,305,306,307,308,309,313,310,311,312,314,315,333,334)and info.pay_type in (1,2) and main.create_time >='2017-12-01 00:00:00' and main.create_time <='2017-12-31 23:59:59' \n";

    static String sql_flight = "select  main.order_no,main.create_time,flight.pay_time,main.depart_city_name,main.arr_city_name,main.code,detail.carrier_name, detail.adult_num+detail.child_num 出行人数,main.total_price, main.depart_date\n" +
            "from order_customer_info cus left join  order_flight_detail detail on cus.order_sub_no =detail.order_sub_no  \n" +
            "left join order_flight flight on detail.order_no=flight.order_no \n" +
            "left join order_main main  on  detail.order_no=main.order_no \n" +
            "left join order_main_expand expand on detail.order_no=expand.order_no \n" +
            "left join order_pay_info info on (detail.order_no=info.order_no )\n" +
            "where \n" +
            "flight.pay_time is not null\n" +
            " and  main.create_time>='2017-12-01 00:00:00' and main.create_time <='2017-12-31 59:59:59'  and info.pay_type in (1,2) group by order_no";


    static String suffix = "00:00:00";

    static Logger logger = LoggerFactory.getLogger(FlightExportExcel.class);

    public static String initDBtoExcel() throws SQLException, IOException {
        logger.info(LocalDate.now() + "开始加载");
        DBUtil.initDataSource("db_order","");


        LocalDate date = LocalDate.now();
        String daycondition = tag + date.toString() + " " + suffix + tag;

        logger.info(LocalDate.now() + "开始连接");
        System.out.println(LocalDate.now() + "开始连接");

        Connection con = DBUtil.getConnection();
        Statement stat = con.createStatement();

        logger.info(LocalDate.now() + "开始查询");
        System.out.println(LocalDate.now() + "开始查询");

        ResultSet rset = stat.executeQuery(getSql(sql_flight));

        logger.info(LocalDate.now() + "查询结束");
        System.out.println(LocalDate.now() + "查询结束");

        Map<String, String> map = new HashMap<>();
        List<String> slist = new ArrayList<>();
        List<String> clist = new ArrayList<>();
        StringBuffer sb;
        while (rset.next()) {
            sb = new StringBuffer();
            int count = rset.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (i == count) {
                    DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String deptime = rset.getString(i);
                    Date date1 = new Date(Long.valueOf(deptime));
                    String dstring = f.format(date1);
                    sb.append(dstring);
                } else {
                    sb.append(rset.getString(i));
                    sb.append(";");
                }
            }
            slist.add(sb.toString());

        }
        String sheename = "机票明细统计";
        String header = "订单号;下单日期;支付日期;出发地;目的地;航班号;航司;订单人数;订单销售总额;出发日期";
        ExcelUtil.createSheetAndheader(sheename, header);
        for (String s : slist) {
            ExcelUtil.createRowofSheet(sheename, s);
        }

        ExcelUtil.createExcel(sheename);
        logger.info(LocalDate.now() + "表生成成功");

        /**-------嗨go-------**/

        ResultSet resultSet2 = stat.executeQuery(getSql(sql_haigo));
        while (resultSet2.next()) {
            sb = new StringBuffer();
            int count = resultSet2.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (i == count) {
                    sb.append(resultSet2.getString(i));
                } else {
                    sb.append(resultSet2.getString(i));
                    sb.append(";");
                }

            }
            clist.add(sb.toString());
        }
        sheename = "嗨GO";
        header = "商品名称;下单日期;订单金额;订单号;出发地;目的地;出行时间";
        ExcelUtil.createSheetAndheader(sheename, header);
        HSSFWorkbook hwb = null;
        for (String c : clist) {
            ExcelUtil.createRowofSheet(sheename, c);
        }

        String attach = ExcelUtil.createExcel("机票明细(" + LocalDate.now() + ")");
        return attach;
    }

    public static void main(String[] args) throws SQLException, IOException {
        String attach = initDBtoExcel();
//        String reciever = args[0];
//        String reciever = "3093803672@qq.com";
        String reciever = "125323997@qq.com";
        MailSend.send(attach, reciever);
//        getSql(sql_flight);
    }

    public static String getSql(String sql) {
        LocalDate localDate = LocalDate.now();
        String nowtime = tag + LocalDate.now().toString() + " " + suffix + tag;

        String previous = localDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)).toString();
        String previoust = tag + previous + " " + suffix + tag;
//        String thistime = localDate.with(DayOfWeek.MONDAY).toString();
        System.out.println(previoust);
        System.out.println(nowtime);
        nowtime = "'2018-01-10 23:59:59' ";

        previoust = " '2018-01-05 00:00:00' ";
        int ind1 = sql.indexOf(">=");
        int end1 = sql.indexOf("and", ind1);

        String time = sql.substring(ind1, end1);
        if (end1 != -1) {
            time = sql.substring(ind1, end1);
        } else {
            time.substring(ind1);
        }
        sql = sql.replace(time, ">=" + previoust);
        int ind2 = sql.indexOf("<");
        int end2 = sql.indexOf("and", ind2);
        if (end2 != -1) {
            time = sql.substring(ind2, end2);

        } else {
            time = sql.substring(ind2);
        }
        sql = sql.replace(time, "<" + nowtime);
        System.out.println(sql);

        return sql;
    }


}
