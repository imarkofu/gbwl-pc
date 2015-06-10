package me.gbwl.pc.main.pageProcessor;

import java.util.Date;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.JPushUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MainJLSCDetailPageProcessor implements PageProcessor {

	private static final Logger logger = Logger.getLogger(MainJLSCDetailPageProcessor.class);
	private Site site =  Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(30000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		Date now = new Date();
		String today = DateUtil.formatDate(now, "yyyy-MM-dd");
		String today1 = DateUtil.formatDate(now, "yyyy-M-d");
		if (page.getRequest().getUrl().indexOf("www.zjsjw.gov.cn/news/detail.asp") != -1) {
			String title = page.getHtml().xpath("//b/text()").all().get(0);
			String d = page.getHtml().xpath("//tr[@class='ee']/td[4]/text()").all().get(0);
			String[] dates = d.split(" ");
			if (dates[0].equals(today1)) {
				JLSC jlsc = new JLSC();
				jlsc.setpId(page.getRequest().getUrl());
				jlsc = ContentHolder.jlscService.searchOne(jlsc);
				if (jlsc == null) {
					jlsc = new JLSC();
					jlsc.setpDate(today);
					jlsc.setpFrom("浙江纪律审查");
					jlsc.setpId(page.getRequest().getUrl());
					jlsc.setpTitle(title);
					ContentHolder.jlscService.save(jlsc);
					JPushUtil.getInstance().pushAndroid("来自《浙江纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + page.getRequest().getUrl() + "<br />发帖时间：" + today);
					MailSender.getInstance().send("来自《浙江纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + page.getRequest().getUrl() + "<br />发帖时间：" + today);
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.hnlzw.net/page.php") != -1) {
			String title = page.getHtml().xpath("//div[@id='arttitl']/text()").all().get(0);
			String date = page.getHtml().xpath("//div[@id='artdes']/text()").all().get(0);
			if (date.indexOf(today) != -1) {
				JLSC jlsc = new JLSC();
				jlsc.setpId(page.getRequest().getUrl());
				jlsc = ContentHolder.jlscService.searchOne(jlsc);
				if (jlsc == null) {
					jlsc = new JLSC();
					jlsc.setpDate(today);
					jlsc.setpFrom("海南纪律审查");
					jlsc.setpId(page.getRequest().getUrl());
					jlsc.setpTitle(title);
					ContentHolder.jlscService.save(jlsc);
					JPushUtil.getInstance().pushAndroid("来自《海南纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + page.getRequest().getUrl() + "<br />发帖时间：" + today);
					MailSender.getInstance().send("来自《海南纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + page.getRequest().getUrl() + "<br />发帖时间：" + today);
				}
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
