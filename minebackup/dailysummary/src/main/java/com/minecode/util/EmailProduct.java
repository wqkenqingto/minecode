package com.minecode.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.minecode.mail.auth.MyAuthenticator;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

/**
 * Created by wqkenqin on 2016/8/9.
 */
public class EmailProduct extends Thread {

    //	 用于给用户发送邮件的邮箱
//	private static String from = "125323997@qq.com";
    private static String from = "spider_bot@sina.com";
    // 邮箱的用户名
    private String username = "spider_bot@sina.com";
    // 邮箱的密码
    //ijkmpbcsuoxsbhhi
    private String password = "hna_spider";
    // 发送邮件的服务器地址
    private String host = "smtp.sina.com";
    private String receiver;
    private String description;
    private String content;
    private String attachRoute;

    public EmailProduct(String receiver, String description, String content, String attachRoute) {
        this.receiver = receiver;
        this.description = description;
        this.content = content;
        this.attachRoute = attachRoute;
    }

    /**
     * @Author wangkuiqing
     * @Date 2016/8/9 16:28
     * @MethodName:createSimpleMessage
     * @Description:生成简单的文本信息
     * @Return:message
     */
    public static MimeMessage createSimpleMessage(Session session, String receiver, String description,
                                                  String content) {
        MimeMessage message = new MimeMessage(session);
        // 指明发件人
        try {
            message.setFrom(new InternetAddress(from));
            if (receiver.contains(";")) {
                String[] res = receiver.split(";");
                for (String s : res) {
//                    logger.info("开始给" + receiver + "发送邮件....");
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
//                    logger.info("邮件发送完成...");
                }

            } else {
//                logger.info("开始给" + receiver + "发送邮件....");

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

//                logger.info("邮件发送完成...");
            }

//			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long d = 14693931220L;
            Date myDate1 = new Date(d);
            message.setSentDate(myDate1);
            message.setSubject(description);
            message.setContent(content, "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * @Author wangkuiqing
     * @Date 2016/8/9 23:14
     * @MethodName:createMixMessage
     * @Description:用于封装带附件的邮件
     * @Return:message
     */
    private static Message createMixMessage(Session mailSession, String receiver, String description, String content, String attachRoute) {
        MimeMessage message = new MimeMessage(mailSession);
        //设置邮件的基本信息
        //发件人
        try {
            message.setFrom(new InternetAddress(from));
            //收件人
            if (receiver.contains(";")) {
                String[] res = receiver.split(";");
                for (String s : res) {
//                    logger.info("开始给" + receiver + "发送邮件....");
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
//                    logger.info("邮件发送完成...");
                }

            } else {
//                logger.info("开始给" + receiver + "发送邮件....");

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

//                logger.info("邮件发送完成...");
            }

            //邮件标题
            message.setSubject(description);


            //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
            MimeBodyPart text = new MimeBodyPart();
            text.setContent(content, "text/html;charset=UTF-8");

            //创建邮件附件
            MimeBodyPart attach = new MimeBodyPart();
//                InputStream in=new FileInputStream();
//                FileDataSource d=new FileDataSource(new FileInputStream());
            DataHandler dh = new DataHandler(new FileDataSource(attachRoute));
            attach.setDataHandler(dh);
            System.out.println("这里是附件名称");
            System.out.println(dh.getName());

            attach.setFileName(MimeUtility.encodeText(dh.getName()));
            //创建容器描述数据关系
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mp.addBodyPart(attach);
            mp.setSubType("mixed");

            message.setContent(mp);
            message.saveChanges();
            //将创建的Email写入到E盘存储
//		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void run() {
        Properties prop = new Properties();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        prop.setProperty("mail.smtp.host", host);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtps.auth", "true");
        prop.setProperty("mail.debug", "true");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
//        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        prop.setProperty("mail.smtp.port", "25");
//        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.smtp.auth", "true");

        MyAuthenticator myauth = new MyAuthenticator(username, password);
        Session mailSession = Session.getInstance(prop, myauth);//获取邮件session
        try {

            Transport ts = mailSession.getTransport("smtp");
            Message message;
//            ts.connect(host, 465, username, password);
            ts.connect(host, username, password);

            if (!StringUtils.isBlank(attachRoute)) {
                message = EmailProduct.createMixMessage(mailSession, receiver, description, content, attachRoute);
            } else {
                message = EmailProduct.createSimpleMessage(mailSession, receiver, description, content);
            }

//			Transport.send(message, message.getAllRecipients());
            Transport.send(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
