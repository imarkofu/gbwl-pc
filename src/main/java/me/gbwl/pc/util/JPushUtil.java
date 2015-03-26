package me.gbwl.pc.util;

import me.gbwl.pc.base.ContentHolder;

import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;

/**
 * @Title: JPushUtil.java<br>
 * @package: me.gbwl.pc.util<br>
 * @Description:TODO<br>
 * @author gbwl<br>
 * @date 2015年3月2日 上午10:19:53<br>
 */
public class JPushUtil {

	private static final Logger logger = Logger.getLogger(JPushUtil.class);
	private JPushClient jpushClient;
	private JPushUtil() {
		jpushClient = new JPushClient(ContentHolder.constant.getJpushMasterSecret(), ContentHolder.constant.getJpushAppKey(), 3);
	}
	
	private static class Tools {
		private static JPushUtil jPushUtil = new JPushUtil();
	}
	
	public static JPushUtil getInstance() {
		return Tools.jPushUtil;
	}
	
	public void pushAndroid(String title, String content) {
		if (ContentHolder.constant.isPush()) {
			PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.android())
					.setAudience(Audience.tag(ContentHolder.constant.getJpushTag()))
					.setNotification(Notification.android(PlatformNotification.ALERT, title, null))
					.setMessage(Message.content(content))
					.build();
			try {
				PushResult result = jpushClient.sendPush(pushPayload);
				logger.info("Got result - " + result);
			} catch (APIConnectionException e) {
				logger.error("Connection error, should retry later", e);
			} catch (APIRequestException e) {
				logger.error("Should review the error, and fix the request", e);
				logger.info("HTTP Status: " + e.getStatus());
	            logger.info("Error Code: " + e.getErrorCode());
	            logger.info("Error Message: " + e.getErrorMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		JPushUtil.getInstance().pushAndroid("Test Title", "Test Content");
	}
}
