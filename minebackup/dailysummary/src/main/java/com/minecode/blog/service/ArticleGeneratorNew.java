/*
 * Copyright (c) 2010-2017, b3log.org & hacpai.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.minecode.blog.service;


import com.alibaba.fastjson.JSONObject;
import com.minecode.blog.model.Article;
import com.minecode.util.CommonUtil;
import com.minecode.util.DBUtil;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;


/**
 * Generates some dummy articles for development testing.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.4, Feb 1, 2013
 * @since 0.4.0
 */

public class ArticleGeneratorNew {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleGeneratorNew.class);

    public void genArticles()
            throws IOException {

        final String authorEmail = "";

        for (int i = 0; i < 10; i++) {
            final JSONObject article = new JSONObject();

            article.put(Article.ARTICLE_TITLE, "article title" + i);
            article.put(Article.ARTICLE_ABSTRACT, "article" + i + " abstract");
            final int deviationTag = 3;

            article.put(Article.ARTICLE_TAGS_REF, "taga,tagb,tag" + i % deviationTag);
            article.put(Article.ARTICLE_AUTHOR_EMAIL, authorEmail);
            article.put(Article.ARTICLE_COMMENT_COUNT, 0);
            article.put(Article.ARTICLE_VIEW_COUNT, 0);
            article.put(Article.ARTICLE_CONTENT, "article content");
            article.put(Article.ARTICLE_PERMALINK, "article" + i + " permalink");
            article.put(Article.ARTICLE_HAD_BEEN_PUBLISHED, true);
            article.put(Article.ARTICLE_IS_PUBLISHED, true);
            article.put(Article.ARTICLE_PUT_TOP, false);

            final int deviationBase = 5;
            final int deviationDay = -(Integer.valueOf(String.valueOf(i).substring(0, 1)) % deviationBase);

            final Date date = DateUtils.addMonths(new Date(), deviationDay);

            article.put(Article.ARTICLE_CREATE_DATE, date);
            article.put(Article.ARTICLE_UPDATE_DATE, date);

            article.put(Article.ARTICLE_RANDOM_DOUBLE, Math.random());
            article.put(Article.ARTICLE_COMMENTABLE, true);
            article.put(Article.ARTICLE_VIEW_PWD, "");
            article.put(Article.ARTICLE_SIGN_ID, "1");

        }
    }

    /**
     * oId
     * articleTitle
     * articleAbstract
     * articleTags
     * articleAuthorEmail
     * articleCommentCount
     * articleViewCount
     * articleContent
     * articlePermalink
     * articleHadBeenPublished
     * articleIsPublished
     * articlePutTop
     * articleCreateDate
     * articleUpdateDate
     * articleRandomDouble
     * articleSignId
     * articleCommentable
     * articleViewPwd
     * articleEditorType
     */
    @Test
    public void addArticle() throws SQLException, FileNotFoundException {
        DBUtil.initDataSource("solo", "mdb");
        Connection con = DBUtil.getConnection();
        String sql = "";
        sql = createSql();

        PreparedStatement st = con.prepareStatement(sql);

//        String sql = "select count(*) from b3_solo_article_copy3";


        JSONObject jobj = new JSONObject();
        long c = System.currentTimeMillis();
        jobj.put("1", c);
        jobj.put("2", "新学习记录2");
        jobj.put("3", "实时计算22");
        jobj.put("4", "storm22");
        jobj.put("5", "wqkenqingto@163.com");
        jobj.put("6", "0");
        jobj.put("7", "11");
        jobj.put("8", "content");
        jobj.put("9", "/articles/2018/02/27/" + c + ".html");
        jobj.put("10", "1");
        jobj.put("11", "1");
        jobj.put("12", "0");
        jobj.put("13", new Date());
        jobj.put("14", new Date());
        jobj.put("15", Math.random());
        jobj.put("16", "1");
        jobj.put("17", "1");
        jobj.put("18", "pwd");
        jobj.put("19", "CodeMirror-Markdown");
        for (int i = 1; i <= 19; i++) {
            st.setObject(i, jobj.get(i + ""));
        }
        st.executeUpdate();
        con.commit();

    }

    public static String createSql() throws FileNotFoundException {

        String sqlpre = "insert into b3_solo_article ( ";
        InputStream in = DBUtil.class.getResourceAsStream("/static/meta");
        String metas = CommonUtil.stream2String(in, "utf8");
        String[] mes = metas.split("\n");
        StringBuffer sb = new StringBuffer();
        int a = 0;
        for (String m : mes) {
            a++;
            sb.append(m);
            if (a != mes.length) {
                sb.append(",");
            } else {
                sb.append(") values (");
            }
        }
        a = 0;
        for (String m : mes) {
            a++;
            sb.append("?");
            if (a != mes.length) {
                sb.append(",");
            } else {
                sb.append(")");
            }
        }
        String sql = sqlpre + sb.toString();
        System.out.println(sql);
        return sql;
    }

}
