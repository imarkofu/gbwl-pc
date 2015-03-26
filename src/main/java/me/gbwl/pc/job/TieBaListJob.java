package me.gbwl.pc.job;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.util.SpringUtil;

public class TieBaListJob {
	
	public void execute() {
		if (ContentHolder.constant.isTieBaRun()) {
			SpringUtil.getInstance().addTiebaListUrl(ContentHolder.constant.getTiebaURL());
		}
		if ((ContentHolder.constant.isTianYaRun()) && (!ContentHolder.constant.isTianyaFirst()))
			SpringUtil.getInstance().addTianYaListUrl(ContentHolder.constant.getTianyaURL());
	}
}