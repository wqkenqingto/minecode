package com.minecode.test;

import com.minecode.util.DBUtil;
import com.minecode.util.HbaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by wqkenqing on 2017/9/10.
 */
public class UsualTest {
    public static void main(String[] args) throws Exception {
        HbaseUtil.getRowShow("city_id_list","仪陇");

    }
}
