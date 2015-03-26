package me.gbwl.pc.main.pageProcessor;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TopTaobaoPageProcessor implements PageProcessor {
	private static final Logger logger = Logger
			.getLogger(TopTaobaoPageProcessor.class);

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	public void process(Page page) {
		List<String> keywords = page
				.getHtml()
				.xpath("//div[@id='page']/div/div/div/div/div/div/div/table/tbody/tr/td/span[@class='title']/a/text()")
				.all();
		if ((keywords != null) && (keywords.size() > 0))
			for (int i = 0; i < keywords.size(); i++)
				writeDataToFile("D:/job/log/keywords-20141017.txt",
						(String) keywords.get(i) + "\n");
	}

	public Site getSite() {
		return this.site;
	}

	public static synchronized boolean writeDataToFile(String filePath,
			String content) {
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			fw.write(content);
		} catch (Exception e) {
			logger.info("写文件异常:filePath=" + filePath + "; content=" + content);
			return false;
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (Exception localException2) {
				}
		}
		return true;
	}
}