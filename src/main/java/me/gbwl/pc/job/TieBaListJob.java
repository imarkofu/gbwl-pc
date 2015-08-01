package me.gbwl.pc.job;

import java.util.Date;

import me.gbwl.pc.base.KeywordsUtil;
import me.gbwl.pc.base.UrlsUtil;
import me.gbwl.pc.main.JLSCMain;
import me.gbwl.pc.main.TianyaMain;
import me.gbwl.pc.main.TiebaMain;
import me.gbwl.pc.main.TiebaUrlMain;
import me.gbwl.pc.util.DateUtil;

public class TieBaListJob {
	
	public void execute() {
		Date now = new Date();
		TiebaMain.getInstance().addTiebaUrl(KeywordsUtil.getInstance().getTiebaUrls());
		TiebaUrlMain.getInstance().addTiebaUrl(UrlsUtil.getInstance().getTiebaUrls());
		TianyaMain.getInstance().addTianyaUrls(UrlsUtil.getInstance().getTianyaUrls());
		
		if (Integer.parseInt(DateUtil.formatDate(now, "mm")) % 5 == 0 && Integer.parseInt(DateUtil.formatDate(now, "ss")) < 30) {
			JLSCMain.getInstance().addJLSCURL(UrlsUtil.getInstance().getJLSCUrls());
			JLSCMain.getInstance().addMainJLSCURL(UrlsUtil.getInstance().getJLSCQUrls());
		}
	}
}