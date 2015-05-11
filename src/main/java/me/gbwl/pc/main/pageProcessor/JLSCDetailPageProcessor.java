package me.gbwl.pc.main.pageProcessor;

import java.util.Date;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.JPushUtil;
import me.gbwl.pc.util.StringUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class JLSCDetailPageProcessor implements PageProcessor {

	private static final Logger logger = Logger.getLogger(JLSCDetailPageProcessor.class);
	private Site site = Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(30000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		String dateStr = page.getHtml().xpath("//div[@class='daty_con']/em[@class='e2']/text()").toString();
		if (!StringUtil.isEmpty(dateStr)) {
			dateStr = dateStr.replace("发布时间：", "");
			if (!StringUtil.isEmpty(dateStr) && !"".equals(dateStr.trim())) {
				dateStr = dateStr.trim();
				Date date = DateUtil.parser(dateStr, "yyyy-MM-dd HH:mm");
				Date now = new Date();
				if (date != null) {
					if (now.getTime() - date.getTime() <= ContentHolder.constant.getTianyaAgo()) {
						String title = page.getHtml().xpath("//title/text()").toString();
						title = title.substring(0, title.indexOf("—")!=-1?title.indexOf("—"):title.length());
						JLSC jlsc = new JLSC();
						jlsc.setpId(page.getRequest().getUrl());
						jlsc.setpTitle(title);
						jlsc.setpDate(dateStr);
						jlsc.setpFrom("纪律审查");
						jlsc = ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《纪律审查》的异常帖子", "帖子标题："+ jlsc.getpTitle() + "<br />帖子链接：" + jlsc.getpId() + "<br />发帖时间：" + date);
						MailSender.getInstance().send("来自《纪律审查》的异常帖子", "帖子标题："+ jlsc.getpTitle() + "<br />帖子链接：" + jlsc.getpId() + "<br />发帖时间：" + date);
					} else {
						logger.info((now.getTime() - date.getTime())+">=" + ContentHolder.constant.getTianyaAgo());
					}
				} else {
					logger.info("日期抓取错误"+date);
				}
			}
		}
		
	}

	@Override
	public Site getSite() {
		return site;
	}

}
