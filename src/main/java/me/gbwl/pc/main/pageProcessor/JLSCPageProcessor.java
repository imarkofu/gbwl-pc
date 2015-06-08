package me.gbwl.pc.main.pageProcessor;

import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.util.SpringUtil;

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
			logger.info(page.getRawText());
			logger.info(c);
			site.addCookie("__jsl_clearance", c+"; Expires="+e+"; Path=/")
					.addCookie("__jsluid", "6e578ecdffd8b70bc1d5549571af4bef");
			try { Thread.sleep(1500); } catch (Exception ex) { }
			SpringUtil.getInstance().addJLSCListUrl("http://www.ccdi.gov.cn/jlsc/index.html");
		} else {
			try {
				List<String> lines_content = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/text()").all();
				List<String> lines_href = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/@href").all();
				for (int i = 0 ; i < lines_content.size() && i < lines_href.size(); i ++) {
					String href = lines_href.get(i);
					JLSC jlsc = new JLSC();
					jlsc.setpId(href);
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						SpringUtil.getInstance().addJLSCDetailListUrl(href);
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
