package me.gbwl.pc.main.pageProcessor;

import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.model.TbPost;
import me.gbwl.pc.util.BlackKeyHelper;
import me.gbwl.pc.util.SpringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TieBaPageProcessor implements PageProcessor {
//	private static final Logger logger = Logger.getLogger(TieBaPageProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(30000);

	public void process(Page page) {
//		String name = page.getHtml().xpath("//title/text()").toString();
//		name = name.substring(0, name.indexOf("_"));
//		TbPostService tbPostService = ContentHolder.tbPostService;
//		if (tbPostService == null) {
//			logger.info("系统错误");
//			return;
//		}

		List<String> topicURLs = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@href").all();
		List<String> topicTitles = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div/a[@id='topic_post_title']/@title").all();
//		List<String> topicContents = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div/div[@id='tt_abs']/text()").all();
//		List<String> topicReplys = page.getHtml().xpath("//ul[@id='thread_topic']/li/div/div[@id='topic_post_relynum']/text()").all();
		for (int i = 0; i < topicURLs.size(); i++) {
			String topicURL = (String) topicURLs.get(i);
			String pid = topicURL.substring(topicURL.lastIndexOf("/") + 1, topicURL.length());
//			String reply = (String) topicReplys.get(i);
			String title = (String) topicTitles.get(i);
//			String content = (String) topicContents.get(i);
//			TbPost tbPost = new TbPost();
//			tbPost.setpId(pid);
//			tbPost = tbPostService.searchOne(tbPost);
//			if (tbPost == null) {
//				tbPost = new TbPost();
//				tbPost.setIsMatch(TbPost.FALSE_MATCH);
//				tbPost.setpContent(content);
//				tbPost.setpId(pid);
//				tbPost.setpReplyCount(Integer.valueOf(Integer.parseInt(reply)));
//				tbPost.setpLastUpdateDate("");
//				tbPost.setpTitle((String) topicTitles.get(i));
//				tbPost.setpName(name);
//				tbPost = tbPostService.save(tbPost);
				if (BlackKeyHelper.isBlack(title)) {
					TbPost tp = new TbPost();
					tp.setpId(pid);
					tp = ContentHolder.tbPostService.searchOne(tp);
//					if (tp != null) {
					if (tp == null) {
//						tp.setIsMatch(TbPost.TRUE_MATCH);
//						tbPostService.update(tp);
//					} else {
//						logger.info("系统异常...");
						//爬取详情页面
						if (ContentHolder.constant.isTieBaDetailRun())
							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
					}
//					MailSender.getInstance().send("来自《" + name + "》贴吧的异常邮件","帖子标题：" + tbPost.getpTitle() + "<br />帖子内容:" + tbPost.getpContent() + "<br />帖子链接：http://tieba.baidu.com/p/"+ tbPost.getpId());
//					//爬取详情页面
//					if (ContentHolder.constant.isTieBaDetailRun())
//						SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + tbPost.getpId());
				}
//			}

		}

		List<String> topURLs = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@href").all();
		List<String> topTitles = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div/div/a[@class='j_th_tit']/@title").all();
//		List<String> topReplys = page.getHtml().xpath("//ul[@id='thread_list']/li/ul[@id='thread_top_list']/li/div/div/div[@class='threadlist_rep_num']/text()").all();
		for (int i = 0; i < topURLs.size(); i++) {
			String topURL = (String) topURLs.get(i);
//			String reply = (String) topReplys.get(i);
			String pid = topURL.substring(topURL.lastIndexOf("/") + 1, topURL.length());
			String title = (String) topTitles.get(i);
//			TbPost tbPost = new TbPost();
//			tbPost.setpId(pid);
//			tbPost = (TbPost) tbPostService.searchOne(tbPost);
//			if (tbPost == null) {
//				tbPost = new TbPost();
//				tbPost.setIsMatch(TbPost.FALSE_MATCH);
//				tbPost.setpContent("");
//				tbPost.setpId(pid);
//				tbPost.setpReplyCount(Integer.valueOf(Integer.parseInt(reply)));
//				tbPost.setpLastUpdateDate("");
//				tbPost.setpTitle(title);
//				tbPost.setpName(name);
//				tbPost = (TbPost) tbPostService.save(tbPost);
				if (BlackKeyHelper.isBlack(title)) {
					TbPost tp = new TbPost();
					tp.setpId(pid);
					tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
//					if (tp != null) {
					if (tp == null) {
//						tp.setIsMatch(TbPost.TRUE_MATCH);
//						tbPostService.update(tp);
//					} else {
//						logger.info("系统异常...");
						//爬取详情页面
						if (ContentHolder.constant.isTieBaDetailRun())
							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
					}
//					MailSender.getInstance().send("来自《" + name + "》贴吧的异常邮件", "帖子标题：" + tbPost.getpTitle() + "<br />帖子链接：http://tieba.baidu.com/p/" + tbPost.getpId());
//					//爬取详情页面
//					if (ContentHolder.constant.isTieBaDetailRun())
//						SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + tbPost.getpId());
				}
//			}
		}

//		List<String> listReplyNums = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div[@class='threadlist_rep_num']/text()").all();
		List<String> listTitles = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@title").all();
		List<String> listURLs = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/a[@class='j_th_tit']/@href").all();
//		List<String> listContents = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div[@class='threadlist_text']/div[@class='threadlist_abs threadlist_abs_onlyline']/text()").all();
//		List<String> listReplyDates = page.getHtml().xpath("//ul[@id='thread_list']/li[@class='j_thread_list']/div/div/div/div/span[@class='threadlist_reply_date']/text()").all();
		for (int i = 0; i < listURLs.size(); i++) {
			String listURL = (String) listURLs.get(i);
//			String reply = (String) listReplyNums.get(i);
			String pid = listURL.substring(listURL.lastIndexOf("/") + 1, listURL.length());
			String title = (String) listTitles.get(i);
//			String content = (String) listContents.get(i);
//			TbPost tbPost = new TbPost();
//			tbPost.setpId(pid);
//			tbPost = (TbPost) tbPostService.searchOne(tbPost);
//			if (tbPost == null) {
//				tbPost = new TbPost();
//				tbPost.setIsMatch(TbPost.FALSE_MATCH);
//				tbPost.setpContent(content);
//				tbPost.setpId(pid);
//				tbPost.setpReplyCount(Integer.valueOf(Integer.parseInt(reply)));
//				tbPost.setpLastUpdateDate(((String) listReplyDates.get(i)).trim());
//				tbPost.setpTitle(title);
//				tbPost.setpName(name);
//				tbPostService.save(tbPost);
				if (BlackKeyHelper.isBlack(title)) {
					TbPost tp = new TbPost();
					tp.setpId(pid);
					tp = (TbPost) ContentHolder.tbPostService.searchOne(tp);
//					if (tp !=  null) {
					if (tp ==  null) {
//						tp.setIsMatch(TbPost.TRUE_MATCH);
//						tbPostService.update(tp);
//					} else {
//						logger.info("系统异常...");
						//爬详情页面
						if (ContentHolder.constant.isTieBaDetailRun())
							SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + pid);
					}
//					MailSender.getInstance().send("来自《" + name + "》贴吧的异常邮件", "帖子标题" + tbPost.getpTitle() + "<br />帖子内容:" + tbPost.getpContent() + "<br />帖子链接：http://tieba.baidu.com/p/" + tbPost.getpId());
//					//爬详情页面
//					if (ContentHolder.constant.isTieBaDetailRun())
//						SpringUtil.getInstance().addTiebaDetailListUrl("http://tieba.baidu.com/p/" + tbPost.getpId());
				}
//			}
		}
	}

	public Site getSite() {
		return this.site;
	}
}