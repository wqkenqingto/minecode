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
package com.minecode.blog;


import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.minecode.blog.model.Article;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Generates some dummy articles for development testing.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.4, Feb 1, 2013
 * @since 0.4.0
 */

public class ArticleGenerator {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleGenerator.class);

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
}
