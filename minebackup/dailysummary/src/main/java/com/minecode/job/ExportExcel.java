package com.minecode.job;

import com.minecode.mail.MailSend;
import com.minecode.util.DBUtil;
import com.minecode.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/29
 * @desc
 */
public class ExportExcel {

    static String tag = "'";
    //    static String sql = "select regist_channel,count(1) as a from user_base_info where regist_channel is not null and (regist_channel like 'jp%' or regist_channel like '122%' or regist_channel like 'wjf%' or regist_channel like 'yt%') and create_time<='2017-12-29 00:00:00' and create_time>='2017-12-17 00:00:00'  group by regist_channel order by a desc";
    static String sql = " select regist_channel,count(1) as a from user_base_info where create_time >'2017-9-30 00:00:00' and create_time<'2018-1-09 00:00:00'  and user_status=1 group by  regist_channel order by a desc;";
    static String sql_count = "select count(distinct user_id) from user_login_record where  device_type=5 and create_time>='2017-12-17 00:00:00' and create_time<=";
    static String suffix = "00:00:00";

    static Logger logger = LoggerFactory.getLogger(ExportExcel.class);

    public static String initDBtoExcel() throws SQLException, IOException {
        DBUtil.initDataSource("db_ucenter","");


        LocalDate date = LocalDate.now();
        String daycondition = tag + date.toString() + " " + suffix + tag;


        Connection con = DBUtil.getConnection();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(getSql(sql, ""));
        Map<String, String> map = new HashMap<>();
        List<String> slist = new ArrayList<>();
        List<String> clist = new ArrayList<>();
        StringBuffer sb;
        while (rset.next()) {
            sb = new StringBuffer();
            if (StringUtils.isBlank(rset.getString(1)) || StringUtils.isEmpty(rset.getString(1))) {
                sb.append("自然用户" + ";" + rset.getString(2));

            } else {
                sb.append(rset.getString(1) + ";" + rset.getString(2));
            }
            slist.add(sb.toString());
        }
        String sheename = "注册渠道统计";
        String header = "注册渠道;注册数";
        ExcelUtil.createSheetAndheader(sheename, header);
        for (String s : slist) {
            System.out.println(s);
            ExcelUtil.createRowofSheet(sheename, s);
        }
        ExcelUtil.createExcel(sheename);
//
//        /**-------统计-------**/
//
//        ResultSet resultSet2 = stat.executeQuery(getSql(sql_count, "count"));
//        while (resultSet2.next()) {
//            sb = new StringBuffer();
//            sb.append(resultSet2.getString(1));
//            clist.add(sb.toString());
//        }
//        sheename = "注册总数";
//        header = "注册数";
//        ExcelUtil.createSheetAndheader(sheename, header);
//        for (String c : clist) {
//            ExcelUtil.createRowofSheet(sheename, c);
//        }
//        String attach = ExcelUtil.createExcel("注册统计表(12.17~" + LocalDate.now() + ")");
        String attach = ExcelUtil.createExcel("各注册渠道统计表" + LocalDate.now() + "");
        logger.info("excel表生成成功....");
        return attach;
    }

    public static void main(String[] args) throws SQLException, IOException {
        String attach = initDBtoExcel();
        String reciever = args[0];
//        String reciever = "wqkenqingto@163.com";
        MailSend.send(attach, reciever);
    }

    public static String getSql(String sql, String sign) {
        String nowtime = tag + LocalDate.now().toString() + " " + suffix + tag;
        if (sign.equals("count")) {
            sql = sql + nowtime;
            System.out.println(sql);
        } else {
            int index = sql.indexOf("create_time<");
            int index1 = sql.indexOf("and", index);
            String time = sql.substring(index, index1).replace("create_time<", "");
            sql = sql.replace(time, nowtime);
        }
        return sql;
    }

}
