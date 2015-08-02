package me.gbwl.pc.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.gbwl.pc.util.StringUtil;

import org.apache.log4j.Logger;

public class MailSender {
	private static final Logger logger = Logger.getLogger(MailSender.class);
	
	private boolean isSendEmail = false;
	private String	displayName;
	private String	toAddress;
	private String	subject;
	private List<Map<String, String>> emails = null;

	public static MailSender getInstance() {
		return InstanceHolder.instance;
	}

	public void send(String subject, String content) {
		if (isSendEmail) {
			try {
				String[] toAddresses = toAddress.split("\\|");
				if (toAddress.length() <= 0) {
					logger.info("邮件接收地址异常");
					return;
				}
				List<String> toList = new ArrayList<String>();
				for (String to : toAddresses) {
					toList.add(to);
				}
				Map<String, String> map = getMail();
				Mail mail = new Mail(map.get("stmp"), map.get("email"),
						displayName,
						map.get("email"), map.get("pass"), toList, subject, content);
				Map<String, String> result = mail.send();
				logger.info("发送邮件" + result);
			} catch (Exception e) {
				logger.error("发送邮件发生异常：content=" + content + ";Exception=" + e.getMessage(), e.getCause());
			}
		} else {
			logger.info("邮件未发送:"+content);
		}
	}

	public void send(String content) {
		if (isSendEmail) {
			try {
				String[] toAddresses = toAddress.split("\\|");
				if (toAddress.length() <= 0) {
					logger.info("邮件接收地址异常");
					return;
				}
				List<String> toList = new ArrayList<String>();
				for (String to : toAddresses) {
					toList.add(to);
				}
				Map<String, String> map = getMail();
				Mail mail = new Mail(map.get("stmp"), map.get("email"),
						displayName,
						map.get("email"), map.get("pass"), toList,
						subject, content);
				Map<String, String> result = mail.send();
				logger.info("发送邮件" + result);
			} catch (Exception e) {
				logger.info("发送邮件发生异常：content=" + content + ";Exception="
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void setSendEmail(boolean isSendEmail) {
		this.isSendEmail = isSendEmail;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setMail(String mail) {
		if (!StringUtil.isEmpty(mail)) {
			String[] emailArr = mail.split("\\|\\|");
			if (emailArr != null && emailArr.length > 0) {
				emails = new ArrayList<Map<String,String>>();
				for (String emailStr : emailArr) {
					String[] arr = emailStr.split("\\|");
					if (arr != null && arr.length == 3) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("stmp", arr[0]); map.put("email", arr[1]); map.put("pass", arr[2]);
						emails.add(map);
					}
				}
			}
		}
	}
	public Map<String, String> getMail() {
		if (emails != null && emails.size() > 0) {
			return emails.get((int) (Math.random()*emails.size()));
		}
		return null;
	}

	private static class InstanceHolder {
		private static MailSender instance = new MailSender();
	}
}