package me.gbwl.pc.job;

import java.util.Date;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.util.DateUtil;
import me.gbwl.pc.util.SpringUtil;

public class TieBaListJob {
	
	public void execute() {
		if (ContentHolder.constant.isTieBaRun()) {
			SpringUtil.getInstance().addTiebaListUrl(ContentHolder.constant.getTiebaURL());
		}
		if ((ContentHolder.constant.isTianYaRun()) && (!ContentHolder.constant.isTianyaFirst()))
			SpringUtil.getInstance().addTianYaListUrl(ContentHolder.constant.getTianyaURL());
		if (ContentHolder.constant.isJLSCRun()) {
//			if (Integer.parseInt(DateUtil.formatDate(new Date(), "mm")) % 5 == 0) {
//				SpringUtil.getInstance().addJLSCListUrl(ContentHolder.constant.getJLSCURL());
				SpringUtil.getInstance().addMainJLSCListUrl(ContentHolder.constant.getMainJlscURL());
//			}
		}
	}
}