package me.gbwl.pc.main.pageProcessor;

import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.base.KeywordsUtil;
import me.gbwl.pc.model.TbPost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TiebaUrlPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(60000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		List<String> topicURLs = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@href").all();
		List<String> topicTitles = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@title").all();
		for (int i = 0; i < topicURLs.size(); i++) {
				if (KeywordsUtil.getInstance().isTiebaKeywords(topicTitles.get(i))) {
					TbPost tp = new TbPost();
					tp.setpId(topicURLs.get(i));
					tp = ContentHolder.tbPostService.searchOne(tp);
					if (tp == null) {
						//爬取详情页面
						if (ContentHolder.constant.isTieBaDetailRun()) {
//							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
						}
					}
				}
		}
		List<String> topURLs = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@href").all();
		List<String> topTitles = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@title").all();
		for (int i = 0; i < topURLs.size(); i++) {
				if (KeywordsUtil.getInstance().isTiebaKeywords(topTitles.get(i))) {
					TbPost tp = new TbPost();
					tp.setpId(topURLs.get(i));
					tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
					if (tp == null) {
						//爬取详情页面
						if (ContentHolder.constant.isTieBaDetailRun()) {
//							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
						}
					}
				}
		}

		List<String> listTitles = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@title").all();
		List<String> listURLs = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@href").all();
		for (int i = 0; i < listURLs.size(); i++) {
			if (KeywordsUtil.getInstance().isTiebaKeywords(listTitles.get(i))) {
				TbPost tp = new TbPost();
				tp.setpId(listURLs.get(i));
				tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
				if (tp ==  null) {
					//爬详情页面
					if (ContentHolder.constant.isTieBaDetailRun()) {
//						SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
					}
				}
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
