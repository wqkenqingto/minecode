package com.minecode.test;

import com.minecode.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wqkenqing on 2017/9/10.
 */
public class UsualTest {
    public static void main(String[] args) throws SQLException {
        DBUtil.initDataSource("test");
        Connection con = DBUtil.getConnection();
        String sql = "insert into  usual_test (tkey) values (" + Math.random() * 1000 + ")";
        Statement st = con.createStatement();
        for(int a=0;a<9;a++) {
            st.execute(sql);
            con.commit();
        }
        con.close();

    }
}
