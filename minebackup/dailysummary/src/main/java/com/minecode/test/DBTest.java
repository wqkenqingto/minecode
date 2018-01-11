package com.minecode.test;

import com.minecode.util.DBUtil;
import com.minecode.util.HbaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by wqkenqing on 2017/9/21.
 */
public class DBTest {
    public static void main(String[] args) throws Exception {

        DBUtil.initDataSource("solo");

        Connection con = DBUtil.getConnection();
        String sql = "select articleTitle,articleContent from ";
        String tname = "b3_solo_article";
        sql = sql + tname;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int a = 0;
        String key = "";
        while (rs.next()) {
            if (a % 5 == 0) {
                System.out.println("上传了" + a + "条数据");
            }
            String title = rs.getString("articleTitle");
            String content = rs.getString("articleContent");
            System.out.println(title);
            key = System.currentTimeMillis()+"";
            System.out.println(key);
            HbaseUtil.addRow("blog_backup", key, "info", "title", title);
            HbaseUtil.addRow("blog_backup", key, "info", "content", content);
            a++;
        }
        st.close();
        con.close();

    }
}
