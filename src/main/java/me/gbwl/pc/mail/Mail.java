package me.gbwl.pc.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;

public class Mail {
	private String displayName;
	private List<String> toList;
	private String from;
	private String smtpServer;
	private String username;
	private String password;
	private String subject;
	private String content;
	private boolean ifAuth;
	private List<String> file = new ArrayList<String>();
	public static Mail mail = null;

	public void init() {
		mail = new Mail(this.displayName, this.from, this.smtpServer,
				this.username, this.password, this.subject);
	}

	public static boolean sendMailContantAndMailList(String content,
			String mailAcct) {
		System.out.println("content=" + content + "_mailAcct=" + mailAcct
				+ "_mail=" + mail);
		if ((mail != null) && (StringUtils.isNotBlank(mailAcct))
				&& (StringUtils.isNotBlank(content))) {
			mail.setContent(content);
			List<String> mailList = new ArrayList<String>();
			mailList.add(mailAcct);
			mail.toList = mailList;
			System.out.println(mail);
			Map<String, String> map = mail.send();
			if (!"failed".equals(map.get("state"))) {
				System.out.println("发送邮件成功");
				return true;
			}
			System.out.println("发送邮件失败");
			return false;
		}
		return false;
	}

	public Mail(String displayName, String from, String smtpServer,
			String username, String password, String subject) {
		this.displayName = displayName;
		this.from = from;
		this.smtpServer = smtpServer;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.ifAuth = true;
		this.file = null;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setIfAuth(boolean ifAuth) {
		this.ifAuth = ifAuth;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTo(List<String> toList) {
		this.toList = toList;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addAttachfile(String fname) {
		this.file.add(fname);
	}

	public Mail() {
	}

	public Mail(String smtpServer, String from, String displayName,
			String username, String password, List<String> toList,
			String subject, String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = true;
		this.username = username;
		this.password = password;
		this.toList = toList;
		this.subject = subject;
		this.content = content;
	}

	public Mail(String smtpServer, String from, String displayName,
			List<String> toList, String subject, String content) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.ifAuth = false;
		this.toList = toList;
		this.subject = subject;
		this.content = content;
	}

	public Map<String, String> send() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("state", "success");
		String message = "邮件发送成功";
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", this.smtpServer);
		if (this.ifAuth) {
			props.put("mail.smtp.auth", "true");
			SmtpAuth smtpAuth = new SmtpAuth(this.username, this.password);
			session = Session.getDefaultInstance(props, smtpAuth);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		session.setDebug(false);
		Transport trans = null;
		try {
			Message msg = new MimeMessage(session);
			try {
				Address from_address = new InternetAddress(this.from,
						this.displayName);
				msg.setFrom(from_address);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			InternetAddress[] address = new InternetAddress[this.toList.size()];
			for (int i = 0; i < this.toList.size(); i++) {
				address[i] = new InternetAddress((String) this.toList.get(i));
			}
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(this.subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(this.content.toString(), "text/html;charset=utf-8");
			mp.addBodyPart(mbp);
			if ((this.file != null) && (!this.file.isEmpty())) {
				for (String filename : this.file) {
					mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(filename);
					mbp.setDisposition("attachment ");
					mbp.setFileName(MimeUtility.encodeText(new File(filename)
							.getName()));
					mbp.setDataHandler(new DataHandler(fds));
					mp.addBodyPart(mbp);
				}
			}
			msg.setContent(mp);
			msg.setSentDate(new Date());

			msg.saveChanges();
			trans = session.getTransport("smtp");
			trans.connect(this.smtpServer, this.username, this.password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();
		} catch (AuthenticationFailedException e) {
			map.put("state", "failed");
			message = "邮件发送失败！错误原因\n身份验证错误!";
			e.printStackTrace();
		} catch (MessagingException e) {
			message = "邮件发送失败！错误原因\n" + e.getMessage();
			map.put("state", "failed");
			e.printStackTrace();
			Exception ex = null;
			if ((ex = e.getNextException()) != null) {
				System.out.println(ex.toString());
				ex.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		map.put("message", message);
		return map;
	}
}