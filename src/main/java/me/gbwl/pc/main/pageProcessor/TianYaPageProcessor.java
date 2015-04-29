package me.gbwl.pc.main.pageProcessor;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.model.TyPost;
import me.gbwl.pc.util.BlackKeyHelper;
import me.gbwl.pc.util.SpringUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TianYaPageProcessor implements PageProcessor {
	private static final Logger logger = Logger.getLogger(TianYaPageProcessor.class);
	private Site site = Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(60000);

	public Site getSite() {
		return this.site;
	}

	public void process(Page page) {
//		boolean hasNext = true;
//		Date now = new Date();
//		Date lastDate = new Date();
		String name = page.getHtml().xpath("//title/text()").toString();
		name = name.substring(0, name.indexOf("_"));
		List<String> postTitle 	= page.getHtml().xpath("//div[@id='bbsdoc']/div/div/div/table/tbody/tr/td[@class='td-title']/a/allText()").all();
		List<String> postURL 	= page.getHtml().xpath("//div[@id='bbsdoc']/div/div/div/table/tbody/tr/td[@class='td-title']/a/@href").all();
//		List<String> postAuthor = page.getHtml().xpath("//div[@id='bbsdoc']/div/div/div/table/tbody/tr/td/a[@class='author']/text()").all();
//		List<String> postTd 	= page.getHtml().xpath("//div[@id='bbsdoc']/div/div/div/table/tbody/tr/td/text()").all();
//		List<String> postTime	= page.getHtml().xpath("//div[@id='bbsdoc']/div/div/div/table/tbody/tr/td/@title").all();
//		List<String> nextPage	= page.getHtml().xpath("//div/div/div/div/div/div[@class='links']/a[@rel='nofollow']/@href").all();
		for (int i = 0; i < postTitle.size(); i++) {
			try {
				String url = (String) postURL.get(i);
				url = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
//				String sql = "select * from ty_post where pId=?";
//				List<Object> params = new ArrayList<Object>();
//				params.add(url);
//				List<TyPost> tyPosts = ContentHolder.tyPostService.search(sql, params);
//				if ((tyPosts == null) || (tyPosts.size() <= 0)) {
//					TyPost tyPost = new TyPost();
//					tyPost.setpAuthor((String) postAuthor.get(i));
//					tyPost.setpClickCount(Integer.valueOf(Integer.parseInt((String) postTd.get(i * 5 + 2))));
//					tyPost.setpId(url);
//					tyPost.setpName(name);
//					tyPost.setpReplyCount(Integer.valueOf(Integer.parseInt((String) postTd.get(i * 5 + 3))));
//					tyPost.setpTitle((String) postTitle.get(i));
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//					Date replyTime = sdf.parse((String) postTime.get(i * 5 + 4));
//					if (replyTime != null) {
//						lastDate = replyTime;
//					}
//					tyPost.setpReplyTime(replyTime);
//					tyPost.setIsMatch(TyPost.FALSE_MATCH);
//					if (BlackKeyHelper.isBlack(tyPost.getpTitle())) {
					if (BlackKeyHelper.isBlack(postTitle.get(i))) {
//						tyPost.setIsMatch(TyPost.TRUE_MATCH);
						if (!ContentHolder.constant.isTianyaFirst()) {
//							MailSender.getInstance().send("来自《" + name + "》的异常邮件", "帖子标题："+ postTitle.get(i) + "<br />帖子链接：http://bbs.tianya.cn/" + url + ".shtml");
							String sql = "select * from ty_post where pId=?";
							List<Object> params = new ArrayList<Object>();
							params.add(url);
							List<TyPost> tyPosts = ContentHolder.tyPostService.search(sql, params);
							if ((tyPosts == null) || (tyPosts.size() <= 0)) {
								//拉取详情页面
								if (ContentHolder.constant.isTianYaDetailRun())
									SpringUtil.getInstance().addTianyaDetailListUrl("http://bbs.tianya.cn/" + url + ".shtml");
							}
						}
					}
//					ContentHolder.tyPostService.save(tyPost);
//				}
			} catch (Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		
//		if (nextPage != null) {
//			if (ContentHolder.constant.isTianyaFirst()) {
//				if (now.getTime() - lastDate.getTime() >= ContentHolder.constant.getDataDayAgo()) {
//					for (String url : nextPage) {
//						SpringUtil.getInstance().addTianYaListUrl(
//								new String[] { url });
//					}
//				}
//			} else if (now.getTime() - lastDate.getTime() <= ContentHolder.constant.getTianyaLongTime()) {
//				if (ContentHolder.constant.getOriginalTianyaURL().indexOf(page.getRequest().getUrl()) != -1) {
//					SpringUtil.getInstance().addTianYaListUrl(nextPage.toArray(new String[0]));
////					for (String url : nextPage) {
////						SpringUtil.getInstance().addTianYaListUrl(new String[] { url });
////					}
//				}
//			}
//
//		}

		/*if (ContentHolder.constant.isTianyaFirst()) {
			if (nextPage != null) {
				for (String url : nextPage)
					SpringUtil.getInstance().addTianYaListUrl(
							new String[] { url });
			} else {
				logger.info("fuck, Actually did not climb to the next url!!!");
			}
		} else if ((hasNext) && (nextPage != null))
			for (String url : nextPage)
				SpringUtil.getInstance().addTianYaListUrl(new String[] { url });*/
	}
}