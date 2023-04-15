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
 * ����java.mail���ʼ����ͳ���
 */

public class SendMailTest {
	public static void main(String[] args) {
		String title = "titleTest";// �������ʼ��ı���
		String from = "ljq_2000com@163.com";// �����﷢��
		String sendTo[] = { "ljq_2000@tom.com", "ljq_2000com@163.com" };// ���͵�����
		// �ʼ����ı����ݣ����԰���html�������ʾΪhtmlҳ��
		String content = "mail test!!!!!!<br><a href=#>aaa</a>";
		// �������ĸ���������������������
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
		// ���Դ������ļ���ȡ��Ӧ�Ĳ���
		Properties props = new Properties();

		String smtp = "smtp.163.com"; // ���÷����ʼ����õ���smtp
		String servername = "xiaoa";
		String serverpaswd = "111111";

		javax.mail.Session mailSession; // �ʼ��Ự����
		javax.mail.internet.MimeMessage mimeMsg; // MIME�ʼ�����

		props = java.lang.System.getProperties(); // ���ϵͳ���Զ���
		props.put("mail.smtp.host", smtp); // ����SMTP����
		props.put("mail.smtp.auth", "true"); // �Ƿ񵽷������û�����������֤
		// ����������֤���͵��û����������Ƿ���ȷ
		Email_Autherticatorbean myEmailAuther = new Email_Autherticatorbean(
				servername, serverpaswd);
		// �����ʼ��Ự
		mailSession = javax.mail.Session.getInstance(props,
				(Authenticator) myEmailAuther);
		// ���ô���Э��
		javax.mail.Transport transport = mailSession.getTransport("smtp");
		// ����from��to����Ϣ
		mimeMsg = new javax.mail.internet.MimeMessage(mailSession);
		if (!"".equals(from)) {
			InternetAddress sentFrom = new InternetAddress(from);
			mimeMsg.setFrom(sentFrom); // ���÷����˵�ַ
		}

		InternetAddress[] sendTo = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			System.out.println("���͵�:" + to[i]);
			sendTo[i] = new InternetAddress(to[i]);
		}

		mimeMsg.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
				sendTo);
		mimeMsg.setSubject(subject, "gb2312");

		MimeBodyPart messageBodyPart1 = new MimeBodyPart();
		// messageBodyPart.setText(UnicodeToChinese(text));
		messageBodyPart1.setContent(text, mimeType);

		Multipart multipart = new MimeMultipart();// ���������ʽ
		multipart.addBodyPart(messageBodyPart1);

		for (int i = 0; i < filenames.length; i++) {
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			// ѡ���ÿһ��������
			String filename = filenames[i].split(",")[0];
			System.out.println("��������" + filename);
			String displayname = filenames[i].split(",")[1];
			// �õ�����Դ
			FileDataSource fds = new FileDataSource(filename);
			// �õ�������������BodyPart
			messageBodyPart2.setDataHandler(new DataHandler(fds));
			// �õ��ļ���ͬ������BodyPart
			// messageBodyPart2.setFileName(displayname);
			// messageBodyPart2.setFileName(fds.getName());
			messageBodyPart2.setFileName(MimeUtility.encodeText(displayname));
			multipart.addBodyPart(messageBodyPart2);
		}
		mimeMsg.setContent(multipart);
		// �����ż�ͷ�ķ�������
		mimeMsg.setSentDate(new Date());
		mimeMsg.saveChanges();
		// �����ʼ�
		transport.send(mimeMsg);
		transport.close();
	}

}
