package me.gbwl.pc.main;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.downloader.MyHttpClientDownloader;
import me.gbwl.pc.main.listener.JLSCDetailSpiderListener;
import me.gbwl.pc.main.listener.JLSCSpiderListener;
import me.gbwl.pc.main.listener.MainJLSCDetailSpiderListener;
import me.gbwl.pc.main.listener.MainJLSCSpiderListener;
import me.gbwl.pc.main.pageProcessor.JLSCDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.JLSCPageProcessor;
import me.gbwl.pc.main.pageProcessor.MainJLSCDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.MainJLSCPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class JLSCMain {

	private boolean isJLSCRun					= true;
	private int		jlscThreadCount				= 1;
	private Spider	spiderJLSCList				= null;
	
	private int		jlscDetailThreadCount		= 1;
	private Spider	spiderJLSCDetailList		= null;
	
	private int		mainJlscThreadCount			= 2;
	private Spider	spiderMainJLSCList			= null;
	
	private int		mainJLSCDetailThreadCount	= 2;
	private Spider	spiderMainJLSCDetailList	= null;
	
	public void addJLSCURL(String... urls) {
		if (isJLSCRun) {
			if (spiderJLSCList == null || spiderJLSCList.getStatus() != Status.Running) {
				init();
			}
			spiderJLSCList.addUrl(urls);
		}
	}
	public void addJLSCDetailURL(String... urls) {
		if (isJLSCRun) {
			if (spiderJLSCDetailList == null || spiderJLSCDetailList.getStatus() != Status.Running) {
				init();
			}
			spiderJLSCDetailList.addUrl(urls);
		}
	}
	public void addMainJLSCURL(String... urls) {
		if (isJLSCRun) {
			if (spiderMainJLSCList == null || spiderMainJLSCList.getStatus() != Status.Running) {
				init();
			}
			spiderMainJLSCList.addUrl(urls);
		}
	}
	public void addMainJLSCDetailURL(String... urls) {
		if (isJLSCRun) {
			if (spiderMainJLSCDetailList == null || spiderMainJLSCDetailList.getStatus() != Status.Running) {
				init();
			}
			spiderMainJLSCDetailList.addUrl(urls);
		}
	}
	
	private synchronized void init() {
		if (spiderJLSCList == null || spiderJLSCList.getStatus() != Status.Running) {
			try { spiderJLSCList.stop(); Thread.sleep(100); } catch (Exception e) { } finally { spiderJLSCList = null; }
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCSpiderListener());
			spiderJLSCList = Spider.create(new JLSCPageProcessor())
					.thread(jlscThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
					.setSpiderListeners(spiderListeners).setDownloader(new MyHttpClientDownloader())
					.addPipeline(new BasePipeline());
			spiderJLSCList.start();
		}
		if (spiderJLSCDetailList == null || spiderJLSCDetailList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCDetailSpiderListener());
			spiderJLSCDetailList = Spider.create(new JLSCDetailPageProcessor())
					.thread(jlscDetailThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSCDetail="))
					.setSpiderListeners(spiderListeners).setDownloader(new MyHttpClientDownloader())
					.addPipeline(new BasePipeline());
			spiderJLSCDetailList.start();
		}
		if (spiderMainJLSCList == null || spiderMainJLSCList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new MainJLSCSpiderListener());
			spiderMainJLSCList = Spider.create(new MainJLSCPageProcessor())
					.thread(mainJlscThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			spiderMainJLSCList.start();
		}
		if (spiderMainJLSCDetailList == null || spiderMainJLSCDetailList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new MainJLSCDetailSpiderListener());
			spiderMainJLSCDetailList = Spider.create(new MainJLSCDetailPageProcessor())
					.thread(mainJLSCDetailThreadCount)
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSCDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			spiderMainJLSCDetailList.start();
		}
	}
	public void setJLSCRun(boolean isJLSCRun) {
		this.isJLSCRun = isJLSCRun;
	}
	public void setJlscThreadCount(int jlscThreadCount) {
		this.jlscThreadCount = jlscThreadCount;
	}
	public void setJlscDetailThreadCount(int jlscDetailThreadCount) {
		this.jlscDetailThreadCount = jlscDetailThreadCount;
	}
	public void setMainJlscThreadCount(int mainJlscThreadCount) {
		this.mainJlscThreadCount = mainJlscThreadCount;
	}
	public void setMainJLSCDetailThreadCount(int mainJLSCDetailThreadCount) {
		this.mainJLSCDetailThreadCount = mainJLSCDetailThreadCount;
	}
	private JLSCMain() {
		init();
	}
	private static class Tools {
		private static final JLSCMain jlscMain = new JLSCMain();
	}
	public static JLSCMain getInstance() {
		return Tools.jlscMain;
	}
}
