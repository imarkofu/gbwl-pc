package me.gbwl.pc.main.listener;

import me.gbwl.pc.main.TiebaUrlMain;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class TiebaUrlDetailSpiderListener implements SpiderListener {

	@Override
	public void onSuccess(Request request) {
		
	}

	@Override
	public void onError(Request request) {
		TiebaUrlMain.getInstance().addTiebaUrlDetail(request.getUrl());
	}

}
