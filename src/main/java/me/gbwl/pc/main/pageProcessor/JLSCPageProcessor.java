package me.gbwl.pc.main.pageProcessor;

import java.util.Date;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.main.JLSCMain;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.JPushUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class JLSCPageProcessor implements PageProcessor {

	private static final Logger logger = Logger.getLogger(JLSCPageProcessor.class);
	private static Site site = Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(30000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		Date now = new Date();
		String today = DateUtil.formatDate(now, "yyyy-MM-dd");
		String today1 = DateUtil.formatDate(now, "yyyyMMdd");
		if (page.getStatusCode() == 521) {
			String[] arrs = page.getRawText().split(";");
			String c = "";
			String e = "";
			for (String arr : arrs) {
				if (arr.indexOf(".push") != -1) {
					c += arr.substring(8, arr.lastIndexOf("\""));
				} else if (arr.indexOf("Expires") != -1) {
					e = arr.substring(9, arr.length());
				}
			}
			site.addCookie("__jsl_clearance", c+";" + e);
			try { Thread.sleep(1500); } catch (Exception ex) { }
//			SpringUtil.getInstance().addJLSCListUrl(page.getRequest().getUrl());
			JLSCMain.getInstance().addJLSCURL(page.getRequest().getUrl());
		} else if (page.getRequest().getUrl().indexOf("www.ccdi.gov.cn/jlsc/index.html") != -1) {
			try {
				List<String> lines_content = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/text()").all();
				List<String> lines_href = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/@href").all();
				for (int i = 0; i < lines_href.size(); i ++) {
					if (lines_href.get(i).indexOf(today1) != -1) {
						JLSC jlsc = new JLSC();
						jlsc.setpId(lines_href.get(i));
						jlsc = ContentHolder.jlscService.searchOne(jlsc);
						if (jlsc == null) {
							jlsc = new JLSC();
							jlsc.setpDate(today);
							jlsc.setpFrom("中央纪律审查");
							jlsc.setpId(lines_href.get(i));
							jlsc.setpTitle(lines_content.get(i));
							ContentHolder.jlscService.save(jlsc);
							JPushUtil.getInstance().pushAndroid("来自《中央纪律审查》的异常帖子", "帖子标题："+ lines_content.get(i) + "<br />帖子链接：" + lines_href.get(i) + "<br />发帖时间：" + today);
							MailSender.getInstance().send("来自《中央纪律审查》的异常帖子", "帖子标题："+ lines_content.get(i) + "<br />帖子链接：" + lines_href.get(i) + "<br />发帖时间：" + today);
						}
					}
				}
			} catch (Exception e) {
				logger.error("exception = " + e.getMessage(), e.getCause());
			}
		} else if (page.getRequest().getUrl().indexOf("www.ccdi.gov.cn/special/bgtzt/qb_bgt/index.html") != -1) {
			try {
				List<String> titles = page.getHtml().xpath("//div[@class='other_center2']/ul/li/a/text()").all();
				List<String> urls = page.getHtml().xpath("//div[@class='other_center2']/ul/li/a/@href").all();
				for (int i = 0; i < urls.size(); i ++) {
					if (urls.get(i).indexOf(today1) != -1) {
						JLSC jlsc = new JLSC();
						jlsc.setpId(urls.get(i));
						jlsc = ContentHolder.jlscService.searchOne(jlsc);
						if (jlsc == null) {
							jlsc = new JLSC();
							jlsc.setpDate(today);
							jlsc.setpFrom("中央监督曝光");
							jlsc.setpId(urls.get(i));
							jlsc.setpTitle(titles.get(i));
							ContentHolder.jlscService.save(jlsc);
							JPushUtil.getInstance().pushAndroid("来自《中央监督曝光》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
							MailSender.getInstance().send("来自《中央纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						}
					}
				}
			} catch (Exception e) {
				logger.error("exception = " + e.getMessage(), e.getCause());
			}
			try {
				List<String> titles = page.getHtml().xpath("//li[@class='fixed']/dl/dt/a/@title").all();
				List<String> urls = page.getHtml().xpath("//li[@class='fixed']/dl/dt/a/@href").all();
				for (int i = 0; i < urls.size(); i ++) {
					if (urls.get(i).indexOf(today1) != -1) {
						JLSC jlsc = new JLSC();
						jlsc.setpId(urls.get(i));
						jlsc = ContentHolder.jlscService.searchOne(jlsc);
						if (jlsc == null) {
							jlsc = new JLSC();
							jlsc.setpDate(today);
							jlsc.setpFrom("全国监督曝光");
							jlsc.setpId(urls.get(i));
							jlsc.setpTitle(titles.get(i));
							ContentHolder.jlscService.save(jlsc);
							JPushUtil.getInstance().pushAndroid("来自《全国监督曝光》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
							MailSender.getInstance().send("来自《全国纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						}
					}
				}
			} catch (Exception e) {
				logger.error("exception = " + e.getMessage(), e.getCause());
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
