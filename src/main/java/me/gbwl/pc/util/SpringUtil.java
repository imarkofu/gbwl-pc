package me.gbwl.pc.util;

import java.util.ArrayList;
import java.util.List;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.main.listener.JLSCDetailSpiderListener;
import me.gbwl.pc.main.listener.JLSCSpiderListener;
import me.gbwl.pc.main.listener.TianYaDetailSpiderListener;
import me.gbwl.pc.main.listener.TianYaSpiderListener;
import me.gbwl.pc.main.listener.TieBaDetailSpiderListener;
import me.gbwl.pc.main.listener.TieBaSpiderListener;
import me.gbwl.pc.main.pageProcessor.JLSCDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.JLSCPageProcessor;
import me.gbwl.pc.main.pageProcessor.TianYaDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.TianYaPageProcessor;
import me.gbwl.pc.main.pageProcessor.TieBaDetailPageProcessor;
import me.gbwl.pc.main.pageProcessor.TieBaPageProcessor;
import me.gbwl.pc.main.pipeline.BasePipeline;
import me.gbwl.pc.scheduler.MyScheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.SpiderListener;

public class SpringUtil {
	
	private static final Logger logger = Logger.getLogger(SpringUtil.class);
	private Spider spiderTiebaList = null;
	private Spider spiderTianYaList = null;
	private Spider spiderTiebaDetailList = null;
	private Spider spiderTianYaDetailList = null;
	private Spider spiderJLSCList = null;
	private Spider spiderJLSCDetailList = null;

	private SpringUtil() {
		logger.info("----------------------start init application------------------------------");
		try {
//			BlackKeyHelper.loadBlackKey();
//			ContentHolder.context = new ClassPathXmlApplicationContext(new String[]{"spring-base.xml", "spring-quartz.xml"});
//			ContentHolder.constant = ContentHolder.context.getBean("constant", Constant.class);
//			ContentHolder.tbPostService = ContentHolder.context.getBean("tbPostService", TbPostService.class);
//			ContentHolder.tyPostService = ContentHolder.context.getBean("tyPostService", TyPostService.class);
			
			if (ContentHolder.constant.isTieBaRun()) {
				List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
				spiderListeners.add(new TieBaSpiderListener());
				this.spiderTiebaList = Spider.create(new TieBaPageProcessor())
						.thread(ContentHolder.constant.getTieBaThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("tieba="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderTiebaList.start();
				spiderListeners.clear();
				if (ContentHolder.constant.isTieBaDetailRun()) {
					spiderListeners.add(new TieBaDetailSpiderListener());
					this.spiderTiebaDetailList = Spider.create(new TieBaDetailPageProcessor())
							.thread(ContentHolder.constant.getTieBaDetailThreadCount())
							.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaDetail="))
							.setSpiderListeners(spiderListeners)
							.addPipeline(new BasePipeline());
					this.spiderTiebaDetailList.start();
//					this.spiderTiebaList.addUrl(ContentHolder.constant.getTiebaURL());
				}
			}
			if (ContentHolder.constant.isTianYaRun()) {
				List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
				spiderListeners.add(new TianYaSpiderListener());
				this.spiderTianYaList = Spider.create(new TianYaPageProcessor())
						.thread(ContentHolder.constant.getTianYaThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("tianya="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderTianYaList.start();
				if (ContentHolder.constant.isTianYaDetailRun()) {
					spiderListeners.clear();
					spiderListeners.add(new TianYaDetailSpiderListener());
					this.spiderTianYaDetailList = Spider.create(new TianYaDetailPageProcessor())
							.thread(ContentHolder.constant.getTianYaDetailThreadCount())
							.setExitWhenComplete(false).setScheduler(new MyScheduler("tianyaDetail="))
							.setSpiderListeners(spiderListeners)
							.addPipeline(new BasePipeline());
					this.spiderTianYaDetailList.start();
				}
				if (ContentHolder.constant.isTianyaFirst())
					this.spiderTianYaList.addUrl(ContentHolder.constant.getTianyaURL());
			}
			if (ContentHolder.constant.isJLSCRun()) {
				List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
				spiderListeners.add(new JLSCSpiderListener());
				this.spiderJLSCList = Spider.create(new JLSCPageProcessor())
						.thread(ContentHolder.constant.getJlscThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderJLSCList.start();
				spiderListeners.clear();
				spiderListeners.add(new JLSCDetailSpiderListener());
				this.spiderJLSCDetailList = Spider.create(new JLSCDetailPageProcessor())
						.thread(ContentHolder.constant.getJlscThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSCDetail="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
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
	
	public void addTianyaDetailListUrl(String... urls) {
		if (ContentHolder.constant.isTianYaRun() || restartTianya) {
			if (this.spiderTianYaDetailList == null || this.spiderTianYaDetailList.getStatus() != Status.Running) {
				List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
				spiderListeners.add(new TianYaDetailSpiderListener());
				this.spiderTianYaDetailList = Spider.create(new TianYaDetailPageProcessor())
						.thread(ContentHolder.constant.getTianYaDetailThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("tianyaDetail="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderTianYaDetailList.start();
			}
			if  (urls != null && urls.length > 0) {
				this.spiderTianYaDetailList.addUrl(urls);
			}
		}
	}
	
	public void addTiebaDetailListUrl(String... urls) {
		if (!ContentHolder.constant.isTieBaRun() || restartTieba)
			return ;
		if (this.spiderTiebaDetailList == null || this.spiderTiebaDetailList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new TieBaDetailSpiderListener());
			this.spiderTiebaDetailList = Spider.create(new TieBaDetailPageProcessor())
					.thread(ContentHolder.constant.getTieBaDetailThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaDetail="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaDetailList.start();
		}
		if (urls != null && urls.length > 0) {
			this.spiderTiebaDetailList.addUrl(urls);
		}
	}

	public void addTiebaListUrl(String... urls) {
		if (!ContentHolder.constant.isTieBaRun() || restartTieba)
			return;
		if (this.spiderTiebaList == null || this.spiderTiebaList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new TieBaSpiderListener());
			this.spiderTiebaList = new Spider(new TieBaDetailPageProcessor())
					.thread(ContentHolder.constant.getTieBaThreadCount()).setExitWhenComplete(false)
					.setScheduler(new MyScheduler("tieba="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaList.start();
		}
		if ((urls != null) && (urls.length > 0))
			this.spiderTiebaList.addUrl(urls);
	}

	public void addTianYaListUrl(String... urls) {
		if (!ContentHolder.constant.isTianYaRun() || restartTianya)
			return;
		if (this.spiderTianYaList == null || this.spiderTianYaList.getStatus() != Status.Running) {
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new TianYaSpiderListener());
			this.spiderTianYaList = Spider.create(new TianYaPageProcessor())
					.thread(ContentHolder.constant.getTianYaThreadCount()).setExitWhenComplete(false)
					.setScheduler(new MyScheduler("tianya"))
					.addPipeline(new BasePipeline())
					.setSpiderListeners(spiderListeners);
			this.spiderTianYaList.start();
		}
		if ((urls != null) && (urls.length > 0))
			this.spiderTianYaList.addUrl(urls);
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
		}
		if (urls != null && urls.length > 0) {
			this.spiderJLSCDetailList.addUrl(urls);
		}
	}
	private boolean restartTieba = false;
	public void restartTieBa() {
		restartTieba = true;
		if (ContentHolder.constant.isTieBaRun()) {
			this.spiderTiebaList.stop();
			try { this.spiderTiebaList = null;Thread.sleep(1000); } catch (Exception e) { }
			
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new TieBaSpiderListener());
			this.spiderTiebaList = Spider.create(new TieBaPageProcessor())
					.thread(ContentHolder.constant.getTieBaThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tieba="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderTiebaList.start();
			spiderListeners.clear();
			if (ContentHolder.constant.isTieBaDetailRun()) {
				try { this.spiderTiebaDetailList.stop();Thread.sleep(1000); } catch (Exception e) { }
				this.spiderTiebaDetailList = null;
				spiderListeners.add(new TieBaDetailSpiderListener());
				this.spiderTiebaDetailList = Spider.create(new TieBaDetailPageProcessor())
						.thread(ContentHolder.constant.getTieBaDetailThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("tiebaDetail="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderTiebaDetailList.start();
//				this.spiderTiebaList.addUrl(ContentHolder.constant.getTiebaURL());
			}
		}
		restartTieba = false;
	}
	private boolean restartTianya = false;
	public void restartTianYa() {
		restartTianya = true;
		if (ContentHolder.constant.isTianYaRun()) {
			try {
				this.spiderTianYaList.stop();
				Thread.sleep(30000);
			} catch (Exception e) { }
			this.spiderTianYaList = null;
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new TianYaSpiderListener());
			this.spiderTianYaList = Spider.create(new TianYaPageProcessor())
					.thread(ContentHolder.constant.getTianYaThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("tianya="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderTianYaList.start();
			if (ContentHolder.constant.isTianYaDetailRun()) {
				try { this.spiderTianYaDetailList.stop();Thread.sleep(30000); } catch (Exception e) { }
				this.spiderTianYaDetailList = null;
				spiderListeners.clear();
				spiderListeners.add(new TianYaDetailSpiderListener());
				this.spiderTianYaDetailList = Spider.create(new TianYaDetailPageProcessor())
						.thread(ContentHolder.constant.getTianYaDetailThreadCount())
						.setExitWhenComplete(false).setScheduler(new MyScheduler("tianyaDetail="))
						.setSpiderListeners(spiderListeners)
						.addPipeline(new BasePipeline());
				this.spiderTianYaDetailList.start();
			}
			if (ContentHolder.constant.isTianyaFirst())
				this.spiderTianYaList.addUrl(ContentHolder.constant.getTianyaURL());
		}
		restartTianya = false;
	}
	public void restartJLSC() {
		restartJLSC = true;
		if (ContentHolder.constant.isJLSCRun()) {
			try {
				this.spiderJLSCList.stop();
				Thread.sleep(30000);
			} catch (Exception e) { }
			this.spiderJLSCList = null;
			List<SpiderListener> spiderListeners = new ArrayList<SpiderListener>();
			spiderListeners.add(new JLSCSpiderListener());
			this.spiderJLSCList = Spider.create(new JLSCPageProcessor())
					.thread(ContentHolder.constant.getJlscThreadCount())
					.setExitWhenComplete(false).setScheduler(new MyScheduler("JLSC="))
					.setSpiderListeners(spiderListeners)
					.addPipeline(new BasePipeline());
			this.spiderJLSCList.start();
		}
	}
	public int getTieBaSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderTiebaList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	public int getTieBaDetailSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderTiebaDetailList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	public int getTianYaSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderTianYaList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	public int getTianYaDetailSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderTianYaDetailList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	public int getJLSCSize() {
		try {
			MyScheduler myScheduler = (MyScheduler) this.spiderJLSCList.getScheduler();
			return myScheduler.size();
		} catch (Exception e) {
			return 0;
		}
	}
	private static class Tools {
		private static SpringUtil springUtil = new SpringUtil();
	}
}