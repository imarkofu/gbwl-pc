package me.gbwl.pc.main.listener;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class TieBaDetailSpiderListener implements SpiderListener {
	public void onSuccess(Request request) {
	}

	public void onError(Request request) {
//		SpringUtil.getInstance().addTiebaDetailListUrl(request.getUrl());
//		TiebaMain.getInstance().addTiebaUrl(request.getUrl());
	}
}