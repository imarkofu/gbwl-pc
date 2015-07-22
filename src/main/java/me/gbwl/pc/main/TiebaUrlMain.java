package me.gbwl.pc.main;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.main.listener.TiebaUrlDetailSpiderListener;
import me.gbwl.pc.main.listener.TiebaUrlSpiderListener;
import me.gbwl.pc.main.pageProcessor.TiebaUrlDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.TiebaUrlPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class TiebaUrlMain {

	private boolean tiebaUrlRun = true;
	private long 	tiebaUrlMillisAgo = 240000;
	private int 	tiebaUrlThreadCount = 10;
	private Spider 	spiderTiebaUrlList = null;
	
	private int		tiebaUrlDetailThreadCount = 24;
	private Spider	spiderTiebaUrlDetailList = null;
	
	
	public void addTiebaUrl(String... urls) {
		if (tiebaUrlRun) {
			if (spiderTiebaUrlList == null || spiderTiebaUrlList.getStatus() != Status.Running) {
				init();
			}
			spiderTiebaUrlList.addUrl(urls);
		}
	}
	public void addTiebaUrlDetail(String... urls) {
		if (tiebaUrlRun) {
			if (spiderTiebaUrlDetailList == null || spiderTiebaUrlDetailList.getStatus() != Status.Running) {
				init();
			}
		}
		spiderTiebaUrlDetailList.addUrl(urls);
	}
	
	private synchronized void init() {
		if (spiderTiebaUrlList == null || spiderTiebaUrlList.getStatus() != Status.Running) {
			try { spiderTiebaUrlList.stop(); Thread.sleep(100); } catch (Exception e) { } finally {spiderTiebaUrlList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TiebaUrlSpiderListener());
			this.spiderTiebaUrlList = Spider.create(new TiebaUrlPageProcessor())
					.thread(tiebaUrlThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaUrl="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaUrlList.start();
		}
		if (spiderTiebaUrlDetailList == null || spiderTiebaUrlDetailList.getStatus() != Status.Running) {
			try { spiderTiebaUrlDetailList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderTiebaUrlDetailList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TiebaUrlDetailSpiderListener());
			this.spiderTiebaUrlDetailList = Spider.create(new TiebaUrlDetailPageProcessor())
					.thread(tiebaUrlDetailThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaUrlDetail="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaUrlDetailList.start();
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
