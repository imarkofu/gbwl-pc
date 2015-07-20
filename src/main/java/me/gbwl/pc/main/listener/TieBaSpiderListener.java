package me.gbwl.pc.main.listener;

import me.gbwl.pc.main.TiebaMain;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class TieBaSpiderListener implements SpiderListener {
	public void onSuccess(Request request) {
	}

	public void onError(Request request) {
//		SpringUtil.getInstance().addTiebaListUrl(request.getUrl());
		TiebaMain.getInstance().addTiebaUrl(request.getUrl());
	}
}