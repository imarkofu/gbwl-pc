package me.gbwl.pc.main.listener;

import me.gbwl.pc.main.JLSCMain;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class MainJLSCDetailSpiderListener implements SpiderListener {

	@Override
	public void onSuccess(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Request request) {
		JLSCMain.getInstance().addMainJLSCDetailURL(request.getUrl());
	}

}
