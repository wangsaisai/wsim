package cn.edu.ustc.wsim.util.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.edu.ustc.wsim.bean.User;
import cn.edu.ustc.wsim.datastructure.GlobalFinal;

public class SendEmailUtil {
	

	public static boolean sendEmail(String email, String subject, String text)
			throws Exception {

		String host = "smtp.126.com"; // 发件人使用发邮件的电子信箱服务器
		String from = "magic_mail_test@126.com"; // 发邮件的出发地（发件人的信箱）
		String to = email; // 发邮件的目的地（收件人信箱）

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", host);

		// Get session
		props.put("mail.smtp.auth", "true"); // 这样才能通过验证

		MyAuthenticator myauth = new MyAuthenticator("magic_mail_test@126.com",
				"mailtest");
		
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session    
		Session session = Session.getDefaultInstance(props, myauth);

		try {
			// 根据session创建一个邮件消息   
			MimeMessage message = new MimeMessage(session);

			// 设置邮件消息的发送者    
			message.setFrom(new InternetAddress(from));

			// 创建邮件的接收者地址，并设置到邮件消息中   
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// 设置邮件消息的主题   
			message.setSubject(subject);
			
			 // 设置邮件消息发送的时间   
			message.setSentDate(new Date()); 

			// 设置邮件消息的主要内容   
			message.setText(text, "utf-8", "html");

			message.saveChanges();

			// 发送邮件   
			Transport.send(message);
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}

	public static boolean sendResetPasswordEmail(User user) throws Exception {
		String subject = "重置密码";
		String link = "http://" + GlobalFinal.getServerIP() + ":8080/wsim/module/user/resetPassword.jsp";
		String text = "点击以下链接修改密码<br><a href='hrefStr?email=emailStr&pwd=pwdStr'>hrefStr</a>";
		text = text.replace("emailStr", user.getEmail());
		text = text.replace("pwdStr", user.getPassword());
		text = text.replace("hrefStr", link);
		return sendEmail(user.getEmail(), subject, text);
	}

	//not use
	public static boolean sendInviteUserEmail(String email, User user) throws Exception {
		String subject = "好友邀请您加入wsim";
		String link = "http://" + GlobalFinal.getServerIP() + ":8080/wsim/module/user/register.jsp";
		String text = "点击以下链接加入wsim<br><a href='hrefStr?inviteUserId=idStr'>hrefStr</a>";
		text = text.replace("idStr", user.getId().toString());
		text = text.replace("hrefStr", link);
		return sendEmail(email, subject, text);
	}
	
	public static void sendWelcomeEmail(User user) throws Exception {
		String subject = "欢迎您加入wsim";
		String link = "http://" + GlobalFinal.getServerIP() + ":8080/wsim/module/user/login.jsp";
		String text = "欢迎您的加入！<br><a href='" + link + "'>登录</a>";
		sendEmail(user.getEmail(), subject, text);
	}

}