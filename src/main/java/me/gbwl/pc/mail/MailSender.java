package me.gbwl.pc.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.gbwl.pc.base.ContentHolder;

import org.apache.log4j.Logger;

public class MailSender {
	private static final Logger logger = Logger.getLogger(MailSender.class);

	public static MailSender getInstance() {
		return InstanceHolder.instance;
	}

	public void send(String subject, String content) {
		if (ContentHolder.constant.isSendEmail()) {
			try {
				String toAddress = ContentHolder.constant.getToAddress();
				String[] toAddresses = toAddress.split("\\|");
				if (toAddress.length() <= 0) {
					logger.info("邮件接收地址异常");
					return;
				}
				List<String> toList = new ArrayList<String>();
				for (String to : toAddresses) {
					toList.add(to);
				}
				Map<String, String> map = ContentHolder.constant.getEmail();
//				Mail mail = new Mail(ContentHolder.constant.getServerHost(),
//								ContentHolder.constant.getUserName(),
//								ContentHolder.constant.getDisplayName(),
//								ContentHolder.constant.getUserName(),
//								ContentHolder.constant.getUserPassword(), toList,
//								ContentHolder.constant.getSubject(), content);
				Mail mail = new Mail(map.get("stmp"), map.get("email"),
						ContentHolder.constant.getDisplayName(),
						map.get("email"), map.get("pass"), toList, subject, content);
				Map<String, String> result = mail.send();
				logger.info("发送邮件" + result);
			} catch (Exception e) {
				logger.info("发送邮件发生异常：content=" + content + ";Exception="
						+ e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.info("邮件未发送:"+content);
		}
	}

	public void send(String content) {
		if (ContentHolder.constant.isSendEmail()) {
			try {
				String toAddress = ContentHolder.constant.getToAddress();
				String[] toAddresses = toAddress.split("\\|");
				if (toAddress.length() <= 0) {
					logger.info("邮件接收地址异常");
					return;
				}
				List<String> toList = new ArrayList<String>();
				for (String to : toAddresses) {
					toList.add(to);
				}
				Map<String, String> map = ContentHolder.constant.getEmail();
//				Mail mail = new Mail(ContentHolder.constant.getServerHost(),
//						ContentHolder.constant.getUserName(),
//						ContentHolder.constant.getDisplayName(),
//						ContentHolder.constant.getUserName(),
//						ContentHolder.constant.getUserPassword(), toList,
//						ContentHolder.constant.getSubject(), content);
				Mail mail = new Mail(map.get("stmp"), map.get("email"),
						ContentHolder.constant.getDisplayName(),
						map.get("email"), map.get("pass"), toList,
						ContentHolder.constant.getSubject(), content);
				Map<String, String> result = mail.send();
				logger.info("发送邮件" + result);
			} catch (Exception e) {
				logger.info("发送邮件发生异常：content=" + content + ";Exception="
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static class InstanceHolder {
		private static MailSender instance = new MailSender();
	}
}