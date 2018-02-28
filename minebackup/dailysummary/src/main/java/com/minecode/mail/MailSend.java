package com.minecode.mail;

import com.minecode.util.EmailProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wqkenqing on 2017/4/25.
 */
public class MailSend {
    //    static String receiver1 = "wqkenqingto@163.com;wqkenqingto@126.com";
    static String receiver1 = "wqkenqingto@163.com;895133104@qq.com;li-ch3@hnair.com";
    static String description = "定时任务结果";
    static String content = "本次任务完成了,若有异议,可邮件至wqkenqingto@163.com.";
    //    static String content = "测试邮件";
    static String attach1 = "";
    static Logger logger = LoggerFactory.getLogger(MailSend.class);

    public static void send(String attach, String receiver) {
        try {
            EmailProduct email = new EmailProduct(receiver, description, content, attach);
            email.start();
        } catch (Exception e) {
            EmailProduct email = new EmailProduct(receiver, "注册任务出错了", content, "");
            email.start();
        }
    }

}
