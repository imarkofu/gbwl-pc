package me.gbwl.pc.main;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.main.listener.TiebaUrlSpiderListener;
import me.gbwl.pc.main.pageProcessor.TiebaUrlPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class TiebaUrlMain {

	private boolean tiebaUrlRun = true;
	private int 	tiebaUrlThreadCount = 5;
	private Spider 	spiderTiebaUrlList = null;
	private long 	tiebaUrlMillisAgo = 120000;
	
	public void addTiebaUrl(String... urls) {
		if (tiebaUrlRun) {
			if (spiderTiebaUrlList == null || spiderTiebaUrlList.getStatus() != Status.Running) {
				init();
			}
			spiderTiebaUrlList.addUrl(urls);
		}
	}
	
	private synchronized void init() {
		if (spiderTiebaUrlList == null || spiderTiebaUrlList.getStatus() != Status.Running) {
			try { spiderTiebaUrlList.stop(); Thread.sleep(100); } catch (Exception e) { } finally {spiderTiebaUrlList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TiebaUrlSpiderListener());
			this.spiderTiebaUrlList = Spider.create(new TiebaUrlPageProcessor())
					.thread(tiebaUrlThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaURL="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaUrlList.start();
		}
	}
	public synchronized void stop () {
		try {
			this.spiderTiebaUrlList.stop();
			Thread.sleep(100);
		} catch (Exception e) { } finally {
			this.spiderTiebaUrlList = null;
		}
	}
	public long getTiebaUrlMillisAgo() {
		return tiebaUrlMillisAgo;
	}
	public void setTiebaUrlMillisAgo(long tiebaUrlMillisAgo) {
		this.tiebaUrlMillisAgo = tiebaUrlMillisAgo;
	}

	private TiebaUrlMain() {}
	private static class Tools {
		private static final TiebaUrlMain tiebaUrlMain = new TiebaUrlMain();
	}
	public static TiebaUrlMain getInstance() {
		return Tools.tiebaUrlMain;
	}
}
