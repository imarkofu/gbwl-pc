package me.gbwl.pc.job;

import java.util.Date;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.base.KeywordsUtil;
import me.gbwl.pc.base.UrlsUtil;
import me.gbwl.pc.main.TianyaMain;
import me.gbwl.pc.main.TiebaMain;
import me.gbwl.pc.main.TiebaUrlMain;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.SpringUtil;

public class TieBaListJob {
	
	public void execute() {
		Date now = new Date();
		TiebaMain.getInstance().addTiebaUrl(KeywordsUtil.getInstance().getTiebaUrls());
		TiebaUrlMain.getInstance().addTiebaUrl(UrlsUtil.getInstance().getTiebaUrls());
		TianyaMain.getInstance().addTianyaUrls(UrlsUtil.getInstance().getTianyaUrls());
		
		if (ContentHolder.constant.isJLSCRun()) {
			if (Integer.parseInt(DateUtil.formatDate(now, "mm")) % 5 == 0 && Integer.parseInt(DateUtil.formatDate(now, "ss")) < 30) {
				SpringUtil.getInstance().addJLSCListUrl(ContentHolder.constant.getJLSCURL());
				SpringUtil.getInstance().addMainJLSCListUrl(ContentHolder.constant.getMainJlscURL());
			}
		}
	}
}