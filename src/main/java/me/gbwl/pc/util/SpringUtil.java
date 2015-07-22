package me.gbwl.pc.util;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
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

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class SpringUtil {
	
	private static final Logger logger = Logger.getLogger(SpringUtil.class);
//	private Spider spiderTianYaList = null;
	
//	private Spider spiderTianYaDetailList = null;
	private Spider spiderJLSCList = null;
	private Spider spiderJLSCDetailList = null;
	private Spider spiderMainJLSCList = null;
	private Spider spiderMainJLSCDetailList = null;

	private SpringUtil() {
		logger.info("----------------------start init application------------------------------");
		try {
//			BlackKeyHelper.loadBlackKey();
//			ContentHolder.context = new ClassPathXmlApplicationContext(new String[]{"spring-base.xml", "spring-quartz.xml"});
//			ContentHolder.constant = ContentHolder.context.getBean("constant", Constant.class);
//			ContentHolder.tbPostService = ContentHolder.context.getBean("tbPostService", TbPostService.class);
//			ContentHolder.tyPostService = ContentHolder.context.getBean("tyPostService", TyPostService.class);
			
			if (ContentHolder.constant.isTieBaRun()) {
				
			
			}
			if (ContentHolder.constant.isTianYaRun()) {
				
			}
			if (ContentHolder.constant.isJLSCRun()) {
				List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
				spiderListeners.add(new JLSCSpiderListener());
				this.spiderJLSCList = Spider.create(new JLSCPageProcessor())
						.thread(ContentHolder.constant.getJlscThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
						.setSpiderListeners(spiderListeners).setDownloader(new MyHttpClientDownloader())
						.addPipeline(new BasePipeline());
				this.spiderJLSCList.start();
				spiderListeners.clear();
				spiderListeners.add(new JLSCDetailSpiderListener());
				this.spiderJLSCDetailList = Spider.create(new JLSCDetailPageProcessor())
						.thread(ContentHolder.constant.getJlscThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSCDetail="))
						.setSpiderListeners(spiderListeners).setDownloader(new MyHttpClientDownloader())
						.addPipeline(new BasePipeline());
				this.spiderJLSCDetailList.start();
				spiderListeners.clear();
				spiderListeners.add(new MainJLSCSpiderListener());
				this.spiderMainJLSCList = Spider.create(new MainJLSCPageProcessor())
						.thread(ContentHolder.constant.getMainJlscThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSC="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderMainJLSCList.start();
				spiderListeners.clear();
				spiderListeners.add(new MainJLSCDetailSpiderListener());
				this.spiderMainJLSCDetailList = Spider.create(new MainJLSCDetailPageProcessor())
						.thread(ContentHolder.constant.getMainJlscDetailThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSCDetail="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderMainJLSCDetailList.start();
				
			}
		} catch (BeansException e) {
			logger.info("----------------------init application failure------------------------------");
			e.printStackTrace();
		}
		logger.info("----------------------success init application------------------------------");
	}

	public static SpringUtil getInstance() {
		return Tools.springUtil;
	}
	


	private boolean restartJLSC = false;
	public void addJLSCListUrl(String... urls) {
		if (!ContentHolder.constant.isJLSCRun() || restartJLSC)
			return ;
		if (this.spiderJLSCList == null || this.spiderJLSCList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCSpiderListener());
			this.spiderJLSCList = Spider.create(new JLSCPageProcessor())
					.thread(ContentHolder.constant.getJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderJLSCList.start();
		}
		if (urls != null && urls.length > 0)
			this.spiderJLSCList.addUrl(urls);
	}
	public void addJLSCDetailListUrl(String... urls) {
		if (!ContentHolder.constant.isJLSCRun() || restartJLSC)
			return ;
		if (this.spiderJLSCDetailList == null || this.spiderJLSCDetailList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCDetailSpiderListener());
			this.spiderJLSCDetailList = Spider.create(new JLSCDetailPageProcessor())
					.thread(ContentHolder.constant.getJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSCDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderJLSCDetailList.start();
		}
		if (urls != null && urls.length > 0) {
			this.spiderJLSCDetailList.addUrl(urls);
		}
	}
	public void addMainJLSCListUrl(String... urls) {
		if (!ContentHolder.constant.isJLSCRun() || restartJLSC)
			return ;
		if (spiderMainJLSCList == null || spiderMainJLSCList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new MainJLSCSpiderListener());
			this.spiderMainJLSCList = Spider.create(new MainJLSCPageProcessor())
					.thread(ContentHolder.constant.getMainJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderMainJLSCList.start();
		}
		if (urls != null && urls.length > 0) 
			spiderMainJLSCList.addUrl(urls);
	}
	public void addmainJLSCDetailListUrl(String... urls) {
		if (!ContentHolder.constant.isJLSCRun() || restartJLSC)
			return ;
		if (spiderMainJLSCDetailList == null || spiderMainJLSCDetailList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new MainJLSCSpiderListener());
			this.spiderMainJLSCDetailList = Spider.create(new MainJLSCDetailPageProcessor())
					.thread(ContentHolder.constant.getMainJlscDetailThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSCDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderMainJLSCDetailList.start();
		}
		if (urls != null && urls.length > 0) 
			spiderMainJLSCDetailList.addUrl(urls);
	}

	public void restartJLSC() {
		restartJLSC = true;
		if (ContentHolder.constant.isJLSCRun()) {
			try { this.spiderJLSCList.stop(); Thread.sleep(1000); } catch (Exception e) { }
			this.spiderJLSCList = null;
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCSpiderListener());
			this.spiderJLSCList = Spider.create(new JLSCPageProcessor())
					.thread(ContentHolder.constant.getJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderJLSCList.start();
			try { this.spiderJLSCDetailList.stop(); Thread.sleep(1000); } catch (Exception e) { }
			this.spiderJLSCDetailList = null;
			spiderListeners.clear();
			spiderListeners.add(new JLSCDetailSpiderListener());
			spiderJLSCDetailList = Spider.create(new JLSCDetailPageProcessor())
					.thread(ContentHolder.constant.getJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSCDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			spiderJLSCDetailList.start();
			try { spiderMainJLSCList.stop(); Thread.sleep(1000); } catch (Exception e) { }
			spiderMainJLSCList = null;
			spiderListeners.clear();
			spiderListeners.add(new MainJLSCSpiderListener());
			spiderMainJLSCList = Spider.create(new MainJLSCPageProcessor())
					.thread(ContentHolder.constant.getMainJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			spiderMainJLSCList.start();
			try { this.spiderMainJLSCDetailList.stop(); Thread.sleep(1000); } catch (Exception e) { }
			spiderMainJLSCDetailList = null;
			spiderListeners.clear();
			spiderListeners.add(new MainJLSCDetailSpiderListener());
			spiderMainJLSCDetailList = Spider.create(new MainJLSCDetailPageProcessor())
					.thread(ContentHolder.constant.getMainJlscDetailThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("MainJLSCDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			spiderMainJLSCDetailList.start();
		}
	}
	
	public int getJLSCSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderJLSCList.getScheduler();
			MyScheduler myScheduler1 = (MyScheduler) this.spiderJLSCDetailList.getScheduler();
			MyScheduler myScheduler2 = (MyScheduler) this.spiderMainJLSCList.getScheduler();
			MyScheduler myScheduler3 = (MyScheduler) this.spiderMainJLSCDetailList.getScheduler();
			return myScheduler.size()+myScheduler1.size()+myScheduler2.size()+myScheduler3.size();
		} catch (Exception e) {
			return 0;
		}
	}
	private static class Tools {
		private static SpringUtil springUtil = new SpringUtil();
	}
}