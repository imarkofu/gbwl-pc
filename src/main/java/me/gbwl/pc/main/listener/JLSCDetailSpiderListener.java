package me.gbwl.pc.main.listener;

import me.gbwl.pc.main.JLSCMain;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class JLSCDetailSpiderListener implements SpiderListener {

	@Override
	public void onSuccess(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Request request) {
//		SpringUtil.getInstance().addJLSCDetailListUrl(request.getUrl());
		JLSCMain.getInstance().addJLSCDetailURL(request.getUrl());
	}

}
