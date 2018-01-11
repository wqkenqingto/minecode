package com.minecode.mail;


import com.minecode.utils.EmailProduct;

/**
 * Created by wqkenqing on 2017/4/25.
 */
public class MailSend {
//    static String receiver = "wqkenqingto@163.com";
//    static String description = "test";
//    static String content = "本次任务完成了";

    public static void main(String[] args) {
        String receiver = args[0];
        String description = args[1];
        String content = args[2];
        String attach = args[3];
        System.out.println("进入了main");
        EmailProduct email = new EmailProduct(receiver, description, content, attach);
        email.start();
    }
}
