package me.gbwl.pc.main.pageProcessor;

import java.util.Date;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.JPushUtil;
import me.gbwl.pc.util.SpringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MainJLSCPageProcessor implements PageProcessor {

//	private static final Logger logger = Logger.getLogger(MainJLSCPageProcessor.class);
	private Site site =  Site.me().setRetryTimes(1).setSleepTime(100).setTimeOut(30000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
	@Override
	public void process(Page page) {
		Date now = new Date();
		String today = DateUtil.formatDate(now, "yyyy-MM-dd");
		String today1 = DateUtil.formatDate(now, "yyyy/MM/dd");
		String today2 = DateUtil.formatDate(now, "yyyy年MM月dd日");
		String today3 = DateUtil.formatDate(now, "yyyyMMdd");
		if (page.getRequest().getUrl().indexOf("www.shjcw.gov.cn/2015jjw/n2233/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//ul[@class='newslist']/li/a/@title").all();
			List<String> urls = page.getHtml().xpath("//ul[@class='newslist']/li/a/@href").all();
			List<String> dates = page.getHtml().xpath("//ul[@class='newslist']/li/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("上海纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《上海纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《上海纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.hebcdi.gov.cn/hb_gzdt/hb_gzdt_ajcc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='news_list']/div/h6/a/@title").all();
			List<String> urls = page.getHtml().xpath("//div[@class='news_list']/div/h6/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='news_list']/div/h6/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("河北纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《河北纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《河北纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.hnsjct.gov.cn/viewCmsCac.do?cacId=ff8080814c27196e014c6ec705c434de") != -1) {
			List<String> titles = page.getHtml().xpath("//tr[@height='30']/td[2]/a/text()").all();
			List<String> urls = page.getHtml().xpath("//tr[@height='30']/td[2]/a/@href").all();
			List<String> dates = page.getHtml().xpath("//tr[@height='30']/td[3]/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("河南纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《河南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《河南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.jjjc.yn.gov.cn/list-5.html") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='list clist']/dl/dt/a/text()").all();
			List<String> urls = page.getHtml().xpath("//div[@class='list clist']/dl/dt/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='list clist']/dl/dt/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("云南纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《云南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《云南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.lnsjjjc.gov.cn/jlsc/index.shtml") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='pList']/p/a/text()").all();
			List<String> urls = page.getHtml().xpath("//div[@class='pList']/p/a/@href").all();
			for (int i = 0; i < urls.size(); i ++) {
				if (urls.get(i).indexOf(today1) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("辽宁纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《辽宁纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《辽宁纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.mingjian.com/news.php?cid=6") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='main01_con font_16']/ul/li/a/text()").all();
			List<String> urls = page.getHtml().xpath("//div[@class='main01_con font_16']/ul/li/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='main01_con font_16']/ul/li/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("黑龙江纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《黑龙江纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《黑龙江纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.ahjjjc.gov.cn/newslist.php?CateId=4978") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='container']/div/ul/li/a/@title").all();
			List<String> urls = page.getHtml().xpath("//div[@class='container']/div/ul/li/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='container']/div/ul/li[@class='date']/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("安徽纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《安徽纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《安徽纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.sdjj.gov.cn/channels/ch00026/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//td[@class='l_t16black']/a/text()").all();
			List<String> urls = page.getHtml().xpath("//td[@class='l_t16black']/a/@href").all();
			List<String> dates = page.getHtml().xpath("//td[@width='83']/text()").all();
			for (int i = 0; i < dates.size() && i < 6; i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("山东纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《山东纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《山东纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.xjjct.gov.cn/zhuzhan/jilvjc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//ul[@class='cclist']/li/h2/a/text()").all();
			List<String> urls = page.getHtml().xpath("//ul[@class='cclist']/li/h2/a/@href").all();
			List<String> dates = page.getHtml().xpath("//ul[@class='cclist']/li/h2/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("新疆纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《新疆纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《新疆纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.jssjw.gov.cn/col/col17/index.html") != -1) {
			String[] arrs = page.getHtml().toString().split("urls\\[i\\]=");
			String url = "";
			String title = "";
			String date = "";
			for (int i = 1; i < arrs.length && i <  10; i ++) {
				String[] strs = arrs[i].split(";");
				for (int j = 0; j < strs.length && j < 5; j ++) {
					if (j == 0) {
						url = "http://www.jssjw.gov.cn" + strs[j].substring(1, strs[j].length() - 1);
					} else if (j == 1) {
						String[] ss = strs[j].split("/>");
						title = ss[1].substring(0, ss[1].length() - 1);
					} else if (j == 2) {
						date = strs[j].substring(11, strs[j].length() - 1) + "-";
					} else if (j == 3) {
						date += strs[j].substring(12, strs[j].length() - 1) + "-";
					} else if (j == 4) {
						date += strs[j].substring(10, strs[j].length() - 1);
					}
				}
				if (date.trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(url);
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("江苏纪律审查");
						jlsc.setpId(url);
						jlsc.setpTitle(title);
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《江苏纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + url + "<br />发帖时间：" + date);
						MailSender.getInstance().send("来自《江苏纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + url + "<br />发帖时间：" + date);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.zjsjw.gov.cn/subject.asp") != -1) {
			List<String> urls = page.getHtml().xpath("//td[@class='dd']/a/@href").all();
			SpringUtil.getInstance().addmainJLSCDetailListUrl(urls.toArray(new String[0]));
		} else if (page.getRequest().getUrl().indexOf("www.jxlz.gov.cn/jjjcyw/jlsc/index.htm") != -1) {
			List<String> titles = page.getHtml().xpath("//td[@class='title']/a/text()").all();
			List<String> urls = page.getHtml().xpath("//td[@class='title']/a/@href").all();
			List<String> dates = page.getHtml().xpath("//td[@width='15%']/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("江西纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《江西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《江西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.hbjwjc.gov.cn/info/iList.jsp") != -1) {
			List<String> titles = page.getHtml().xpath("//ul[@class='list-b-t']/li/h4/a/text()").all();
			List<String> urls = page.getHtml().xpath("//ul[@class='list-b-t']/li/h4/a/@href").all();
			List<String> dates = page.getHtml().xpath("//ul[@class='list-b-t']/li/p/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("湖北纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《湖北纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《湖北纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.gxjjw.gov.cn/staticmores/700/700-1.shtml") != -1) {
			List<String> dates = page.getHtml().xpath("//div[@class='more_list lh28 fn-clear']/span/text()").all();
			List<String> urls = page.getHtml().xpath("//div[@class='more_list lh28 fn-clear']/a/@href").all();
			List<String> titles1 = page.getHtml().xpath("//div[@class='more_list lh28 fn-clear']/a/b/text()").all();
			List<String> titles = page.getHtml().xpath("//div[@class='more_list lh28 fn-clear']/a/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("广西纪律审查");
						jlsc.setpId(urls.get(i));
						if (i < titles1.size())
							jlsc.setpTitle(titles1.get(i));
						else 
							jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						
						JPushUtil.getInstance().pushAndroid("来自《广西纪律审查》的异常帖子", "帖子标题："+ jlsc.getpTitle() + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《广西纪律审查》的异常帖子", "帖子标题："+ jlsc.getpTitle() + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.sxdi.gov.cn/gzdt/jlsc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//dl[@class='list-tit fl']/dt/a/@title").all();
			List<String> urls = page.getHtml().xpath("//dl[@class='list-tit fl']/dt/a/@href").all();
			List<String> dates = page.getHtml().xpath("//dl[@class='list-tit fl']/dt/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("山西纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《山西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《山西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.nmgjjjc.gov.cn/ajcc/index.shtml") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='zMain_list']/h2/a/@title").all();
			List<String> urls = page.getHtml().xpath("//div[@class='zMain_list']/h2/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='zMain_list']/h2/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("内蒙古纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《内蒙古纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
						MailSender.getInstance().send("来自《内蒙古纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + dates.get(i));
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.qinfeng.gov.cn/ywzc/cbaj/cbaj.htm") != -1) {
			List<String> titles = page.getHtml().xpath("//a[@class='c1075']/@title").all();
			List<String> urls = page.getHtml().xpath("//a[@class='c1075']/@href").all();
			List<String> dates = page.getHtml().xpath("//span[@class='timestyle1075']/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().indexOf(today2) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("陕西纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《山西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《山西纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.ccdijl.gov.cn/ajcc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//td[@class='gray_title news_td']/a/text()").all();
			List<String> urls = page.getHtml().xpath("//td[@class='gray_title news_td']/a/@href").all();
			List<String> dates = page.getHtml().xpath("//td[@class='font14 gray']/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("吉林纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《吉林纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《吉林纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.fjjc.gov.cn/html/xxgkajcc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='news_list']/dl/dd/a/@title").all();
			List<String> urls = page.getHtml().xpath("//div[@class='news_list']/dl/dd/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='news_list']/dl/dd/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				String date = dates.get(i).trim();
				if (date.substring(date.length()-10, date.length()).equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("福建纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《福建纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《福建纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.gzdis.gov.cn/col/col1806/index.html") != -1) {
			String[] arrs = page.getHtml().toString().split("urls\\[i\\]=");
			String url = "";
			String title = "";
			String date = "";
			for (int i = 1; i < arrs.length-1 && i <  10; i ++) {
				String[] strs = arrs[i].split(";");
				for (int j = 0; j < strs.length && j < 5; j ++) {
					if (j == 0) {
						url = "http://www.gzdis.gov.cn" + strs[j].substring(1, strs[j].length() - 1);
					} else if (j == 1) {
						title = strs[j].substring(12, strs[j].length() - 1);
					} else if (j == 2) {
						date = strs[j].substring(9, strs[j].length() - 1) + "-";
					} else if (j == 3) {
						date += strs[j].substring(10, strs[j].length() - 1) + "-";
					} else if (j == 4) {
						date += strs[j].substring(8, strs[j].length() - 1);
					}
				}
				if (date.trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(url);
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("贵州纪律审查");
						jlsc.setpId(url);
						jlsc.setpTitle(title);
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《贵州纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + url + "<br />发帖时间：" + date);
						MailSender.getInstance().send("来自《贵州纪律审查》的异常帖子", "帖子标题："+ title + "<br />帖子链接：" + url + "<br />发帖时间：" + date);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("gdjct.gd.gov.cn/ffkb/index.jhtml") != -1) {
			List<String> titles = page.getHtml().xpath("//ul[@class='GsTL5']/li/a/@title").all();
			List<String> urls = page.getHtml().xpath("//ul[@class='GsTL5']/li/a/@href").all();
			List<String> dates = page.getHtml().xpath("//ul[@class='GsTL5']/li/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("山东纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《山东纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《山东纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.qhjc.gov.cn/html/ajcc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//li/a/text()").all();
			List<String> urls = page.getHtml().xpath("//li/a/@href").all();
			List<String> dates = page.getHtml().xpath("//li/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().equals(today)) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("青海纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《青海纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《青海纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.xzjjw.gov.cn/ff.php") != -1) {
			List<String> titles = page.getHtml().xpath("//div[@class='title']/a/text()").all();
			List<String> urls = page.getHtml().xpath("//div[@class='title']/a/@href").all();
			List<String> dates = page.getHtml().xpath("//div[@class='title']/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("西藏纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《西藏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《西藏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.scjc.gov.cn/zhyw/qwfb/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//p[@class='name']/a/text()").all();
			List<String> urls = page.getHtml().xpath("//p[@class='name']/a/@href").all();
			List<String> dates = page.getHtml().xpath("//p[@class='time_p']/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("四川纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《四川纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《四川纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.nxjjjc.gov.cn/jlsc/index.html") != -1) {
			List<String> titles = page.getHtml().xpath("//dl[@class='new-top-dl']/dt/a[2]/text()").all();
			List<String> urls = page.getHtml().xpath("//dl[@class='new-top-dl']/dt/a[2]/@href").all();
			if (urls != null && urls.size() > 0) {
				for (int i = 0; i < urls.size(); i ++) {
					if (urls.get(i).indexOf(today3) != -1) {
						JLSC jlsc = new JLSC();
						jlsc.setpId(urls.get(i));
						jlsc = ContentHolder.jlscService.searchOne(jlsc);
						if (jlsc == null) {
							jlsc = new JLSC();
							jlsc.setpDate(today);
							jlsc.setpFrom("宁夏纪律审查");
							jlsc.setpId(urls.get(i));
							jlsc.setpTitle(titles.get(i));
							ContentHolder.jlscService.save(jlsc);
							JPushUtil.getInstance().pushAndroid("来自《宁夏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
							MailSender.getInstance().send("来自《宁夏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						}
					}
				}
			}
			titles = page.getHtml().xpath("//ul[@class='news-list']/li/a/text()").all();
			urls = page.getHtml().xpath("//ul[@class='news-list']/li/a/@href").all();
			if (urls != null && urls.size() > 0) {
				for (int i = 0; i < urls.size(); i ++) {
					if (urls.get(i).indexOf(today3) != -1) {
						JLSC jlsc = new JLSC();
						jlsc.setpId(urls.get(i));
						jlsc = ContentHolder.jlscService.searchOne(jlsc);
						if (jlsc == null) {
							jlsc = new JLSC();
							jlsc.setpDate(today);
							jlsc.setpFrom("宁夏纪律审查");
							jlsc.setpId(urls.get(i));
							jlsc.setpTitle(titles.get(i));
							ContentHolder.jlscService.save(jlsc);
							JPushUtil.getInstance().pushAndroid("来自《宁夏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
							MailSender.getInstance().send("来自《宁夏纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						}
					}
				}
			}
		} else if (page.getRequest().getUrl().indexOf("www.hnlzw.net/jingzcm_index.php") != -1) {
			List<String> urls = page.getHtml().xpath("//div[@id='jxxxcon']/ul/li/a/@href").all();
			if (urls != null && urls.size() > 0) {
				SpringUtil.getInstance().addmainJLSCDetailListUrl(urls.toArray(new String[0]));
			}
			urls = page.getHtml().xpath("//div[@id='dymaincon2']/ul/li/h1/a/@href").all();
			List<String> titles = page.getHtml().xpath("//div[@id='dymaincon2']/ul/li/h1/a/text()").all();
			List<String> dates = page.getHtml().xpath("//div[@id='dymaincon2']/ul/li/h1/span/text()").all();
			for (int i = 0; i < dates.size(); i ++) {
				if (dates.get(i).trim().indexOf(today) != -1) {
					JLSC jlsc = new JLSC();
					jlsc.setpId(urls.get(i));
					jlsc = ContentHolder.jlscService.searchOne(jlsc);
					if (jlsc == null) {
						jlsc = new JLSC();
						jlsc.setpDate(today);
						jlsc.setpFrom("海南纪律审查");
						jlsc.setpId(urls.get(i));
						jlsc.setpTitle(titles.get(i));
						ContentHolder.jlscService.save(jlsc);
						JPushUtil.getInstance().pushAndroid("来自《海南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
						MailSender.getInstance().send("来自《海南纪律审查》的异常帖子", "帖子标题："+ titles.get(i) + "<br />帖子链接：" + urls.get(i) + "<br />发帖时间：" + today);
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
