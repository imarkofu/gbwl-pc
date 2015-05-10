package me.gbwl.pc.main.pageProcessor;

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
		
	}

	@Override
	public Site getSite() {
		return site;
	}

}
