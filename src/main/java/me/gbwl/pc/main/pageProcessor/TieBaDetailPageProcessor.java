package me.gbwl.pc.main.pageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.model.TbPost;
import me.gbwl.pc.util.DateUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.alibaba.fastjson.JSON;

public class TieBaDetailPageProcessor implements PageProcessor {
	private static final Logger logger = Logger
			.getLogger(TieBaDetailPageProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	public void process(Page page) {
//		TbPostService tbPostService = ContentHolder.tbPostService;
//		if (tbPostService == null) {
//			logger.info("系统异常");
//			return;
//		}
		String pageUrl = page.getRequest().getUrl();

		String pid = "";
		if (pageUrl.indexOf("?") == -1)
			pid = pageUrl.substring(pageUrl.lastIndexOf("/") + 1);
		else {
			pid = pageUrl.substring(pageUrl.lastIndexOf("/") + 1, pageUrl.indexOf("?"));
		}
//		TbPost tbPost = new TbPost();
//		tbPost.setpId(pid);
//		tbPost = (TbPost) tbPostService.searchOne(tbPost);
//		if (tbPost == null) {
//			logger.info("系统异常...");
//			return;
//		}
		List<String> pName = page.getHtml().xpath("//a[@class='card_title_fname']/text()").all();
		List<String> posts = page.getHtml().xpath("//div[@class='l_post l_post_bright noborder']/@data-field").all();
		List<String> title = page.getHtml().xpath("//h1[@class='core_title_txt']/@title").all();
		List<String> content = page.getHtml().xpath("//div[@class='d_post_content j_d_post_content']/text()").all();
		if (pName == null || pName.size() <= 0) {pName = new ArrayList<String>();pName.add("未识别");}
		if (title == null || title.size() <= 0) {title = new ArrayList<String>();title.add("未识别到帖子标题");}
		if (content == null || content.size() <= 0) {content = new ArrayList<String>();content.add("未识别到帖子内容");}
		if (posts != null && posts.size() > 0) {
			for (String s : posts) {
				Map<?, ?> map = JSON.parseObject(s, Map.class);
				Map<?, ?> c = (Map<?, ?>) map.get("content");
				Date now = new Date();
				String cc = c.get("date").toString();
				Date date = DateUtil.parser(cc, "yyyy-MM-dd HH:mm");
				if (date != null) {
					if (now.getTime() - date.getTime() <= ContentHolder.constant.getTiebaAgo()) {
						MailSender.getInstance().send("来自《" + pName.get(0) + "》贴吧的异常邮件", "帖子标题：" + title.get(0) + "<br />帖子内容：" + content.get(0) + "<br />帖子链接：http://tieba.baidu.com/p/" + pid+"<br />发帖时间：" + cc);
						TbPost tbPost = new TbPost();
						tbPost.setIsMatch(TbPost.TRUE_MATCH);
						tbPost.setpContent((content.get(0).length()>200?content.get(0).substring(0, 200):content.get(0)));
						tbPost.setpId(pid);
						tbPost.setpLastUpdateDate(cc);
						tbPost.setpName(pName.get(0));
						tbPost.setpTitle(title.get(0));
						ContentHolder.tbPostService.save(tbPost);
					} else {
						logger.info((now.getTime() - date.getTime())+">=" + ContentHolder.constant.getTiebaAgo());
					}
				} else {
					logger.info("日期抓取错误"+cc);
				}
			}
		}
//		if ((posts != null) && (posts.size() > 0)) {
//			if (!((String) posts.get(0)).equals(tbPost.getpContent())) {
//				tbPost.setpContent((String) posts.get(0));
//				tbPostService.update(tbPost);
//				if (BlackKeyHelper.isBlack((String) posts.get(0))) {
//					if (tbPost.getIsMatch() == TbPost.FALSE_MATCH) {
//						tbPost.setIsMatch(TbPost.TRUE_MATCH);
//						tbPostService.update(tbPost);
//						MailSender.getInstance().send("帖子标题："+ tbPost.getpTitle()+ "<br />帖子内容:"+ tbPost.getpContent()+ "<br />帖子链接：http://tieba.baidu.com/p/"+ tbPost.getpId());
//					}
//					return;
//				}
//			}
//		}
	}

	public Site getSite() {
		return this.site;
	}
}