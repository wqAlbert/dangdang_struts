package com.imvcc.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.mail.internet.MimeUtility;
import java.util.Date;

/**
 * 利用java.mail的邮件发送程序
 */

public class SendMailTest {
	public static void main(String[] args) {
		String title = "titleTest";// 所发送邮件的标题
		String from = "ljq_2000com@163.com";// 从那里发送
		String sendTo[] = { "ljq_2000@tom.com", "ljq_2000com@163.com" };// 发送到那里
		// 邮件的文本内容，可以包含html标记则显示为html页面
		String content = "mail test!!!!!!<br><a href=#>aaa</a>";
		// 所包含的附件，及附件的重新命名
		String fileNames[] = { "D:\\beans.xml" };
		try {
			// MailSender mailsender = new MailSender();
			sendmail(title, from, sendTo, content, fileNames,
					"text/html;charset=gb2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendmail(String subject, String from, String[] to,
			String text, String[] filenames, String mimeType) throws Exception {
		// ResourceBundle mailProps = ResourceBundle.getBundle("mail");
		// 可以从配置文件读取相应的参数
		Properties props = new Properties();

		String smtp = "smtp.163.com"; // 设置发送邮件所用到的smtp
		String servername = "xiaoa";
		String serverpaswd = "111111";

		javax.mail.Session mailSession; // 邮件会话对象
		javax.mail.internet.MimeMessage mimeMsg; // MIME邮件对象

		props = java.lang.System.getProperties(); // 获得系统属性对象
		props.put("mail.smtp.host", smtp); // 设置SMTP主机
		props.put("mail.smtp.auth", "true"); // 是否到服务器用户名和密码验证
		// 到服务器验证发送的用户名和密码是否正确
		Email_Autherticatorbean myEmailAuther = new Email_Autherticatorbean(
				servername, serverpaswd);
		// 设置邮件会话
		mailSession = javax.mail.Session.getInstance(props,
				(Authenticator) myEmailAuther);
		// 设置传输协议
		javax.mail.Transport transport = mailSession.getTransport("smtp");
		// 设置from、to等信息
		mimeMsg = new javax.mail.internet.MimeMessage(mailSession);
		if (!"".equals(from)) {
			InternetAddress sentFrom = new InternetAddress(from);
			mimeMsg.setFrom(sentFrom); // 设置发送人地址
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			System.out.println("发送到:" + to[i]);
			sendTo[i] = new InternetAddress(to[i]);
		}

		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
				sendTo);
		mimeMsg.setSubject(subject, "gb2312");

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		// messageBodyPart.setText(UnicodeToChinese(text));
		messageBodyPart1.setContent(text, mimeType);

		Multipart multipart = new MimeMultipart();// 附件传输格式
		multipart.addBodyPart(messageBodyPart1);

		for (int i = 0; i < filenames.length; i++) {
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// 选择出每一个附件名
			String filename = filenames[i].split(",")[0];
			System.out.println("附件名：" + filename);
			String displayname = filenames[i].split(",")[1];
			// 得到数据源
			FileDataSource fds = new FileDataSource(filename);
			// 得到附件本身并至入BodyPart
			messageBodyPart2.setDataHandler(new DataHandler(fds));
			// 得到文件名同样至入BodyPart
			// messageBodyPart2.setFileName(displayname);
			// messageBodyPart2.setFileName(fds.getName());
			messageBodyPart2.setFileName(MimeUtility.encodeText(displayname));
			multipart.addBodyPart(messageBodyPart2);
		}
		mimeMsg.setContent(multipart);
		// 设置信件头的发送日期
		mimeMsg.setSentDate(new Date());
		mimeMsg.saveChanges();
		// 发送邮件
		transport.send(mimeMsg);
		transport.close();
	}

}
