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
	private Site site = Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(30000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		try {
			List<String> lines_content = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/text()").all();
			List<String> lines_href = page.getHtml().xpath("//ul[@class='list_news_dl']/li/a/@href").all();
			for (int i = 0 ; i < lines_content.size() && i < lines_href.size(); i ++) {
				String href = lines_href.get(i);
//				href = href.substring(2, href.length());
				JLSC jlsc = new JLSC();
				jlsc.setpId(href);
				jlsc = ContentHolder.jlscService.searchOne(jlsc);
				if (jlsc == null) {
//					jlsc = new JLSC();
//					jlsc.setpId(href);
//					jlsc.setpTitle(lines_content.get(i));
//					jlsc.setpFrom("纪律审查");
//					ContentHolder.jlscService.save(jlsc);
					SpringUtil.getInstance().addJLSCDetailListUrl(href);
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("拉取" + page.getRequest().getUrl() + "异常\t" + e.getMessage(), e.getCause());
		}
	}

	@Override
	public Site getSite() {
		return this.site;
	}

}
