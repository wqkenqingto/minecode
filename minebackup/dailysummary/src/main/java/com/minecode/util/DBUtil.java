package com.minecode.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by wqkenqing on 2017/8/30.
 */
public class DBUtil {
    /**
     * 通过dbcp配置数据库连接池
     **/
    private static Properties properties = new Properties();
    private static DataSource dataSource;
    private static String suffix = "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";

    public static void initDataSource(String database) {
        //初始化配置文件
        try {
//            InputStream in = new FileInputStream("db.properties");
//            InputStream in = new FileInputStream("db.properties");
            properties.load(DBUtil.class.getResourceAsStream("/db.properties"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String url = (String) properties.get("url");
            url = url + database + suffix;
            properties.setProperty("url", url);

            System.out.println(url);
            dataSource = BasicDataSourceFactory.createDataSource(properties);//实现针对不同库的dataSource的初始化
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //针对hbse获取索引进行的需求添加工具方法
//    public static
}
