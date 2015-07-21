package me.gbwl.pc.main.listener;

import me.gbwl.pc.main.TiebaUrlMain;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class TiebaUrlSpiderListener implements SpiderListener {

	@Override
	public void onSuccess(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Request request) {
		TiebaUrlMain.getInstance().addTiebaUrl(request.getUrl());
	}

}
