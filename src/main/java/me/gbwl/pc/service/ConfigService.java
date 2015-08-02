package me.gbwl.pc.service;

import java.util.List;

import javax.annotation.Resource;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.ConfigDao;
import me.gbwl.pc.mail.MailSender;
import me.gbwl.pc.main.JLSCMain;
import me.gbwl.pc.main.TianyaMain;
import me.gbwl.pc.main.TiebaMain;
import me.gbwl.pc.main.TiebaUrlMain;
import me.gbwl.pc.model.Config;
import me.gbwl.pc.util.JPushUtil;

import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigService extends BaseService<Config, Integer> {

	private ConfigDao configDao;
	@Resource(name="configDao")
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
		this.baseDao = configDao;
	}
	
	public void restartConfig() {
		List<Config> configs = configDao.getAll();
		if (configs != null && configs.size() > 0) {
			for (Config config : configs) {
				try {
					switch (config.getKeyword()) {
					case "tiebaRun":	// 1
						TiebaMain.getInstance().setTiebaRun(Boolean.parseBoolean(config.getValues()));
						break;
					case "tiebaThreadCount":	// 2
						TiebaMain.getInstance().setTiebaThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "tiebaMillisAgo":	// 3
						TiebaMain.getInstance().setTiebaMillisAgo(Long.parseLong(config.getValues()));
						break;
					case "tiebaUrlRun":	// 4
						TiebaUrlMain.getInstance().setTiebaUrlRun(Boolean.parseBoolean(config.getValues()));
						break;
					case "tiebaUrlThreadCount":	// 5
						TiebaUrlMain.getInstance().setTiebaUrlThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "tiebaUrlMillisAgo":	// 6
						TiebaUrlMain.getInstance().setTiebaUrlMillisAgo(Long.parseLong(config.getValues()));
						break;
					case "tiebaUrlDetailThreadCount":	// 7
						TiebaUrlMain.getInstance().setTiebaUrlDetailThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "tianyaRun":	// 8
						TianyaMain.getInstance().setTianyaRun(Boolean.parseBoolean(config.getValues()));
						break;
					case "tianyaThreadCount":	// 9
						TianyaMain.getInstance().setTianyaThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "tianyaMillisAgo":		// 10
						TianyaMain.getInstance().setTianyaMillisAgo(Integer.parseInt(config.getValues()));
						break;
					case "tianyaDetailThreadCount":
						TianyaMain.getInstance().setTianyaDetailThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "isJLSCRun":
						JLSCMain.getInstance().setJLSCRun(Boolean.parseBoolean(config.getValues()));
						break;
					case "jlscThreadCount":
						JLSCMain.getInstance().setJlscThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "jlscDetailThreadCount":
						JLSCMain.getInstance().setJlscDetailThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "mainJlscThreadCount":
						JLSCMain.getInstance().setMainJlscThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "mainJLSCDetailThreadCount":
						JLSCMain.getInstance().setMainJLSCDetailThreadCount(Integer.parseInt(config.getValues()));
						break;
					case "isSendEmail":
						MailSender.getInstance().setSendEmail(Boolean.parseBoolean(config.getValues()));
						break;
					case "isPush":
						JPushUtil.getInstance().setPush(Boolean.parseBoolean(config.getValues()));
						break;
					case "jpushMasterSecret":
						JPushUtil.getInstance().setJpushMasterSecret(config.getValues());
						break;
					case "jpushAppKey":
						JPushUtil.getInstance().setJpushAppKey(config.getValues());
						break;
					case "jpushAlias":
						JPushUtil.getInstance().setJpushAlias(config.getValues());
						break;
					case "displayName":
						MailSender.getInstance().setDisplayName(config.getValues());
						break;
					case "toAddress":
						MailSender.getInstance().setToAddress(config.getValues());
						break;
					}
				} catch (Exception e) {
					logger.warn("Restart Config Exception = " + e.getMessage(), e.getCause());
				}
			}
		}
	}
}
