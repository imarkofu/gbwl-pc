package me.gbwl.pc.main;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.main.listener.TianYaDetailSpiderListener;
import me.gbwl.pc.main.listener.TianYaSpiderListener;
import me.gbwl.pc.main.pageProcessor.TianYaDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.TianYaPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class TianyaMain {

	private boolean	tianyaRun				= false;
	private long 	tianyaMillisAgo 			= 240000;
	private int		tianyaThreadCount		= 2;
	private Spider	spiderTianYaList		= null;
	
	private int		tianyaDetailThreadCount	= 15;
	private Spider	spiderTianYaDetailList	= null;
	
	public void addTianyaUrls(String... urls) {
		if (tianyaRun) {
			if (spiderTianYaList == null || spiderTianYaList.getStatus() != Status.Running) {
				init();
			}
			spiderTianYaList.addUrl(urls);
		}
	}
	
	public void addTianyaDetailUrls(String... urls) {
		if (tianyaRun) {
			if (spiderTianYaDetailList == null || spiderTianYaDetailList.getStatus() != Status.Running) {
				init();
			}
			spiderTianYaDetailList.addUrl(urls);
		}
	}
	
	private synchronized void init() {
		if (spiderTianYaList == null || spiderTianYaList.getStatus() != Status.Running) {
			try { spiderTianYaList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderTianYaList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TianYaSpiderListener());
			this.spiderTianYaList = Spider.create(new TianYaPageProcessor())
					.thread(tianyaThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tianya="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTianYaList.start();
		}
		if (spiderTianYaDetailList == null || spiderTianYaDetailList.getStatus() != Status.Running) {
			try { spiderTianYaDetailList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderTianYaDetailList = null; }
			List<SpiderListener> listeners = new ArrayList<SpiderListener>();
			listeners.add(new TianYaDetailSpiderListener());
			this.spiderTianYaDetailList = Spider.create(new TianYaDetailPageProcessor())
					.thread(tianyaDetailThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tianyaDetail="))
					.setSpiderListeners(listeners)
					.addPipeline(new BasePipeline());
			this.spiderTianYaDetailList.start();
		}
	}
	public void stop() {
		try {spiderTianYaList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderTianYaList = null; }
		try {spiderTianYaDetailList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderTianYaDetailList = null; }
	}
	public int getTianyaThreadCount() {
		return tianyaThreadCount;
	}
	public void setTianyaThreadCount(int tianyaThreadCount) {
		this.tianyaThreadCount = tianyaThreadCount;
	}
	public int getTianyaDetailThreadCount() {
		return tianyaDetailThreadCount;
	}
	public void setTianyaDetailThreadCount(int tianyaDetailThreadCount) {
		this.tianyaDetailThreadCount = tianyaDetailThreadCount;
	}
	public long getTianyaMillisAgo() {
		return tianyaMillisAgo;
	}
	public void setTianyaMillisAgo(long tianyaMillisAgo) {
		this.tianyaMillisAgo = tianyaMillisAgo;
	}
	private TianyaMain() {}
	private static class Tools {
		private static final TianyaMain tianyaMain = new TianyaMain();
	}
	public static TianyaMain getInstance() {
		return Tools.tianyaMain;
	}
}
