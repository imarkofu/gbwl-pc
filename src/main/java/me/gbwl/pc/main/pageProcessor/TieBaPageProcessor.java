package me.gbwl.pc.main.pageProcessor;

import java.util.Date;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.main.TiebaMain;
import me.gbwl.pc.model.TbPost;
import me.gbwl.pc.util.DateUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TieBaPageProcessor implements PageProcessor {
	private static final Logger logger = Logger.getLogger(TieBaPageProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(60000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");

	public void process(Page page) {
		
		List<String> titles = page.getHtml().xpath("//div[@class='s_post']/span/a/text()").all() ;
		List<String> contents = page.getHtml().xpath("//div[@class='s_post']/div/text()").all() ;
		List<String> hrefs = page.getHtml().xpath("//div[@class='s_post']/span/a/@href").all() ;
		List<String> froms = page.getHtml().xpath("//div[@class='s_post']/a[@class='p_forum']/font/text()").all();
		List<String> dates = page.getHtml().xpath("//div[@class='s_post']/font/text()").all();
		
		Date now = new Date();
		if (dates != null && dates.size() > 0) {
			for (int i = 0; i < dates.size(); i ++) {
				try {
					if (now.getTime() - DateUtil.parser(dates.get(i), "yyyy-MM-dd HH:mm").getTime() <= TiebaMain.getInstance().getTiebaMillisAgo()) {
						TbPost tbPost = new TbPost();
						tbPost.setpId(hrefs.get(i));
						tbPost = ContentHolder.tbPostService.searchOne(tbPost);
						if (tbPost == null) {
							MailSender.getInstance().send("来自《" + froms.get(i) + "》贴吧的异常邮件", "帖子标题：" + titles.get(i) + "<br />帖子内容：" + contents.get(i) + "<br />帖子链接：" + hrefs.get(i) +"<br />发帖时间：" + dates.get(i));
							tbPost = new TbPost();
							tbPost.setIsMatch(TbPost.TRUE_MATCH);
							tbPost.setpContent(contents.get(i));
							tbPost.setpId(hrefs.get(i));
							tbPost.setpLastUpdateDate(dates.get(i));
							tbPost.setpName(froms.get(i)+"吧");
							tbPost.setpTitle(titles.get(i));
							ContentHolder.tbPostService.save(tbPost);
						}
					} else {
						logger.info(now.getTime() - DateUtil.parser(dates.get(i), "yyyy-MM-dd HH:mm").getTime() - TiebaMain.getInstance().getTiebaMillisAgo());
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e.getCause());
				}
			}
		}
//		List<String> topicURLs = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@href").all();
//		List<String> topicTitles = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@title").all();
//		for (int i = 0; i < topicURLs.size(); i++) {
//			String topicURL = (String) topicURLs.get(i);
//			String pid = topicURL.substring(topicURL.lastIndexOf("/") + 1, topicURL.length());
//			String title = (String) topicTitles.get(i);
//
//				if (BlackKeyHelper.isBlack(title)) {
//					TbPost tp = new TbPost();
//					tp.setpId(pid);
//					tp = ContentHolder.tbPostService.searchOne(tp);
//					if (tp == null) {
//						//爬取详情页面
//						if (ContentHolder.constant.isTieBaDetailRun())
//							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
//					}
//				}
//		}
//		List<String> topURLs = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@href").all();
//		List<String> topTitles = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@title").all();
//		for (int i = 0; i < topURLs.size(); i++) {
//			String topURL = (String) topURLs.get(i);
//			String pid = topURL.substring(topURL.lastIndexOf("/") + 1, topURL.length());
//			String title = (String) topTitles.get(i);
//				if (BlackKeyHelper.isBlack(title)) {
//					TbPost tp = new TbPost();
//					tp.setpId(pid);
//					tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
//					if (tp == null) {
//						//爬取详情页面
//						if (ContentHolder.constant.isTieBaDetailRun())
//							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
//					}
//				}
//		}
//
//		List<String> listTitles = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@title").all();
//		List<String> listURLs = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@href").all();
//		for (int i = 0; i < listURLs.size(); i++) {
//			String listURL = (String) listURLs.get(i);
//			String pid = listURL.substring(listURL.lastIndexOf("/") + 1, listURL.length());
//			String title = (String) listTitles.get(i);
//			if (BlackKeyHelper.isBlack(title)) {
//				TbPost tp = new TbPost();
//				tp.setpId(pid);
//				tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
//				if (tp ==  null) {
//					//爬详情页面
//					if (ContentHolder.constant.isTieBaDetailRun())
//						SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
//				}
//			}
//		}
	}

	public Site getSite() {
		return this.site;
	}
}