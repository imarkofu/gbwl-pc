package me.gbwl.pc.main.pageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.main.TiebaUrlMain;
import me.gbwl.pc.model.TbPost;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.JPushUtil;

import com.alibaba.fastjson.JSON;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TiebaUrlDetailPageProcessor implements PageProcessor {

	private static final Logger logger = Logger.getLogger(TiebaUrlDetailPageProcessor.class);
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(60000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		List<String> pName = page.getHtml().xpath("//a[@class='card_title_fname']/text()").all();
		List<String> posts = page.getHtml().xpath("//div[@class='l_post l_post_bright noborder']/@data-field").all();
		List<String> title = page.getHtml().xpath("//h1[@class='core_title_txt']/@title").all();
		List<String> content = page.getHtml().xpath("//div[@class='j_d_post_content']/text()").all();
		if (pName == null || pName.size() <= 0) {pName = new ArrayList<String>();pName.add("未识别");}
		if (title == null || title.size() <= 0) {title = new ArrayList<String>();title.add("未识别到帖子标题");}
		if (content == null || content.size() <= 0) {content = new ArrayList<String>();content.add("未识别到帖子内容");}
		if (posts != null && posts.size() > 0) {
			for (String s : posts) {
				Map<?, ?> map = JSON.parseObject(s, Map.class);
				Map<?, ?> c = (Map<?, ?>) map.get("content");
				Date now = new Date();
				String cc;
				try {
					cc = c.get("date").toString();
				} catch (Exception e) {
					cc = page.getHtml().xpath("//span[@class='j_reply_data']/text()").all().get(0);
				}
				Date date = DateUtil.parser(cc, "yyyy-MM-dd HH:mm");
				if (date != null) {
					if (now.getTime() - date.getTime() <= TiebaUrlMain.getInstance().getTiebaUrlMillisAgo()) {
						MailSender.getInstance().send("来自《" + pName.get(0) + "》贴吧的异常邮件", "帖子标题：" + title.get(0) + "<br />帖子内容：" + content.get(0) + "<br />帖子链接：http://tieba.baidu.com/p/" + page.getRequest().getUrl()+"<br />发帖时间：" + cc);
						TbPost tbPost = new TbPost();
						tbPost.setIsMatch(TbPost.TRUE_MATCH);
						tbPost.setpContent((content.get(0).length()>200?content.get(0).substring(0, 200):content.get(0)));
						tbPost.setpId(page.getRequest().getUrl());
						tbPost.setpLastUpdateDate(cc);
						tbPost.setpName(pName.get(0));
						tbPost.setpTitle(title.get(0));
						ContentHolder.tbPostService.save(tbPost);
						JPushUtil.getInstance().pushAndroid("来自《" + pName.get(0) + "》贴吧的异常帖子", "帖子标题：" + title.get(0) + "<br />帖子内容：" + content.get(0) + "<br />帖子链接：" + page.getRequest().getUrl() +"<br />发帖时间：" + cc);
					} else {
//						logger.info((now.getTime() - date.getTime())+">=" + TiebaUrlMain.getInstance().getTiebaUrlMillisAgo());
					}
				} else {
					logger.info("日期抓取错误"+cc);
				}
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

}
