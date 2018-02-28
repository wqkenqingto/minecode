package com.minecode.util;

import org.b3log.solo.model.Article;
import org.b3log.solo.model.Option;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/2/6
 * @desc solo同步文章插件DBUtil
 */
public class SoloDBUtil {
    public void initDB() {
        DBUtil.initDataSource("solo","solo");
    }

    public void test() throws SQLException, FileNotFoundException, ClassNotFoundException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        String sql1 = DBUtil.getSqlSolo("");//blogArticle加1

    }



     //更新static文章总数,发布总数
    public void incBlogArticleCount() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();

    }     //更新static文章总数,发布总数
    public void addArticle() throws SQLException {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        //记得添加邮箱

    }
}
