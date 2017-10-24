package com.minecode.util;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.minecode.mail.auth.MyAuthenticator;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * Created by wqkenqin on 2016/8/9.
 */
public class EmailProduct extends Thread {

//	 用于给用户发送邮件的邮箱
	private static String from = "wangkuiqing@mocentre.com";
	// 邮箱的用户名
	private String username = "wangkuiqing@mocentre.com";
	// 邮箱的密码
	//ijkmpbcsuoxsbhhi
	private String password ="125323Wkq";
	// 发送邮件的服务器地址
	private String host = "smtp.exmail.qq.com";
	private String receiver;
	private String description;
	private String content;
	private String attachRoute;
	public EmailProduct(String receiver, String description, String content, String attachRoute){
		        this.receiver=receiver;
		        this.description=description;
		        this.content=content;
				this.attachRoute=attachRoute;
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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
//			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long d=14693931220L;
			Date myDate1 =new Date(d);
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
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
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
		attach.setFileName(dh.getName());  //

		//创建容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");

		message.setContent(mp);
		message.saveChanges();
		System.out.println("附件生成成功-------------------------");
		//将创建的Email写入到E盘存储
//		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
	public void run() {
		try {
			Properties prop = new Properties();
	        prop.setProperty("mail.smtp.host", host);
	        prop.setProperty("mail.transport.protocol", "smtp");
	        prop.setProperty("mail.smtp.auth", "true");
	        prop.setProperty("mail.debug", "true");
			prop.put("mail.smtp.port", 465);
			MyAuthenticator myauth = new MyAuthenticator(username, password);
			Session mailSession = Session.getInstance(prop,myauth);//获取邮件session
	        Transport ts = mailSession.getTransport("smtp");
			Message message;
            ts.connect(host, username, password);

			if (!StringUtils.isBlank(attachRoute)){
				message=EmailProduct.createMixMessage(mailSession,receiver,description,content,attachRoute);
			}else {
				message= EmailProduct.createSimpleMessage(mailSession, receiver, description, content);
			}

//			Transport.send(message, message.getAllRecipients());
			ts.send(message, message.getAllRecipients());
            System.out.println("发送完成----------");
            ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
