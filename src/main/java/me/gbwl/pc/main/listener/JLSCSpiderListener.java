package me.gbwl.pc.main.listener;

import me.gbwl.pc.util.SpringUtil;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

public class JLSCSpiderListener implements SpiderListener {

	@Override
	public void onSuccess(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Request request) {
		SpringUtil.getInstance().addJLSCListUrl(request.getUrl());
	}

}