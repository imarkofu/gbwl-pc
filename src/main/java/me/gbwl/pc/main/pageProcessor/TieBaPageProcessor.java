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
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e.getCause());
				}
			}
		}
	}

	public Site getSite() {
		return this.site;
	}
}