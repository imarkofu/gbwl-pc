package me.gbwl.pc.util;

import me.gbwl.pc.base.ContentHolder;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

public class SpringUtil {
	
	private static final Logger logger = Logger.getLogger(SpringUtil.class);
//	private Spider spiderTianYaList = null;
	
//	private Spider spiderTianYaDetailList = null;
//	private Spider spiderJLSCList = null;
//	private Spider spiderJLSCDetailList = null;
//	private Spider spiderMainJLSCList = null;
//	private Spider spiderMainJLSCDetailList = null;

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

	private static class Tools {
		private static SpringUtil springUtil = new SpringUtil();
	}
}