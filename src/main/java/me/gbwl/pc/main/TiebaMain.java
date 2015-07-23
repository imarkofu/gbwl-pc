package me.gbwl.pc.main;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.main.listener.TieBaSpiderListener;
import me.gbwl.pc.main.pageProcessor.TieBaPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;

public class TiebaMain {

	private boolean tiebaRun = true;
	private int tiebaThreadCount = 5;
	private Spider spiderTiebaList = null;
	private long tiebaMillisAgo = 240000;
	
	private TiebaMain() {
		init();
	}
	
	public void addTiebaUrl(String... urls) {
		if (tiebaRun) {
			if (spiderTiebaList == null || spiderTiebaList.getStatus() != Spider.Status.Running) {
				init();
			}
			spiderTiebaList.addUrl(urls);
		}
	}
	
	private synchronized void init() {
		if (spiderTiebaList == null || spiderTiebaList.getStatus() != Spider.Status.Running) {
			try { this.spiderTiebaList.stop(); Thread.sleep(100);} catch (Exception e) { } finally { this.spiderTiebaList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TieBaSpiderListener());
			this.spiderTiebaList = Spider.create(new TieBaPageProcessor())
					.thread(tiebaThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tieba="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaList.start();
		}
	}
	
	public synchronized void stop () {
		try { this.spiderTiebaList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { this.spiderTiebaList = null; }
	}
	
	public synchronized int tiebaTaskSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderTiebaList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	public void setTiebaRun(boolean tiebaRun) {
		this.tiebaRun = tiebaRun;
	}
	public void setTiebaThreadCount(int tiebaThreadCount) {
		this.tiebaThreadCount = tiebaThreadCount;
	}
	public long getTiebaMillisAgo() {
		return tiebaMillisAgo;
	}
	public void setTiebaMillisAgo(long tiebaMillisAgo) {
		this.tiebaMillisAgo = tiebaMillisAgo;
	}

	private static class Tools {
		private static final TiebaMain tiebaMain = new TiebaMain();
	}
	public static TiebaMain getInstance() {
		return Tools.tiebaMain;
	}
}
