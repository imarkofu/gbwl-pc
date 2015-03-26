package me.gbwl.pc.main.pageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.model.TyPost;
import me.gbwl.pc.util.DateUtil;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TianYaDetailPageProcessor implements PageProcessor {
	private static final Logger logger = Logger.getLogger(TianYaDetailPageProcessor.class);
	private Site site = Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(10000);

	public Site getSite() {
		return this.site;
	}

	public void process(Page page) {
//		TyPostService tyPostService = ContentHolder.tyPostService;
//		if (tyPostService == null) {
//			logger.info("系统异常");
//			return;
//		}
		String pageUrl = page.getRequest().getUrl();
		String pid = pageUrl.substring(pageUrl.lastIndexOf("/")+1, pageUrl.lastIndexOf("."));
//		TyPost tyPost = new TyPost();
//		tyPost.setpId(pid);
//		tyPost = tyPostService.searchOne(tyPost);
//		if (tyPost == null) {
//			logger.info("系统异常...");
//			return;
//		}
		List<String> list = page.getHtml().xpath("//div[@class='atl-info']/span[2]/text()").all();
		List<String> content = page.getHtml().xpath("//div[@class='bbs-content clearfix']/text()").all();
		List<String> title = page.getHtml().xpath("//span[@class='s_title']/span/text()").all();
		List<String> pName = page.getHtml().xpath("//p[@class='crumbs']/em/a/text()").all();
		List<String> author = page.getHtml().xpath("//div[@class='atl-info']/span/a/text()").all();
		if (pName == null || pName.size() <= 0) {pName = new ArrayList<String>();pName.add("未知板块");}
		if (title == null || title.size() <= 0) {title = new ArrayList<String>();title.add("未拿到");}
		if (content == null || content.size() <= 0) {content = new ArrayList<String>();content.add("未拿到"); }
		if (author == null || author.size() <= 0) {author = new ArrayList<String>();author.add("未知");}
		if (list != null && list.size() > 0) {
//			for (String s : list) {
				String date = list.get(0).replace("时间：", "").trim();
				Date now = new Date();
				Date d = DateUtil.parser(date, "yyyy-MM-dd HH:mm:ss");
				if (d != null) {
					if (now.getTime() - d.getTime() <= ContentHolder.constant.getTianyaAgo()) {
						MailSender.getInstance().send("来自《" + pName.get(0) + "》的异常邮件", "帖子标题："+ title.get(0) + "<br />帖子内容："+(content.get(0).length()>300?content.get(0).substring(0, 300):content.get(0))+"<br />帖子链接：http://bbs.tianya.cn/" + pid + ".shtml<br />发帖时间：" + date);
						TyPost tyPost = new TyPost();
						tyPost.setIsMatch(TyPost.TRUE_MATCH);
						tyPost.setpAuthor(author.get(0));
						tyPost.setpClickCount(0);
						tyPost.setpName(pName.get(0));
						tyPost.setpId(pid);
						tyPost.setpReplyCount(0);
						tyPost.setpReplyTime(d);
						tyPost.setpTitle(title.get(0));
						ContentHolder.tyPostService.save(tyPost);
					} else {
						logger.info((now.getTime() - d.getTime())+">=" + ContentHolder.constant.getTianyaAgo());
					}
				} else {
					logger.info("日期抓取错误"+date);
				}
//			}
		}
	}
}