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
						TiebaMain.getInstance().setTiebaRun(Boolean.parseBoolean(config.getVal()));
						break;
					case "tiebaThreadCount":	// 2
						TiebaMain.getInstance().setTiebaThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "tiebaMillisAgo":	// 3
						TiebaMain.getInstance().setTiebaMillisAgo(Long.parseLong(config.getVal()));
						break;
					case "tiebaUrlRun":	// 4
						TiebaUrlMain.getInstance().setTiebaUrlRun(Boolean.parseBoolean(config.getVal()));
						break;
					case "tiebaUrlThreadCount":	// 5
						TiebaUrlMain.getInstance().setTiebaUrlThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "tiebaUrlMillisAgo":	// 6
						TiebaUrlMain.getInstance().setTiebaUrlMillisAgo(Long.parseLong(config.getVal()));
						break;
					case "tiebaUrlDetailThreadCount":	// 7
						TiebaUrlMain.getInstance().setTiebaUrlDetailThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "tianyaRun":	// 8
						TianyaMain.getInstance().setTianyaRun(Boolean.parseBoolean(config.getVal()));
						break;
					case "tianyaThreadCount":	// 9
						TianyaMain.getInstance().setTianyaThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "tianyaMillisAgo":		// 10
						TianyaMain.getInstance().setTianyaMillisAgo(Integer.parseInt(config.getVal()));
						break;
					case "tianyaDetailThreadCount":
						TianyaMain.getInstance().setTianyaDetailThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "isJLSCRun":
						JLSCMain.getInstance().setJLSCRun(Boolean.parseBoolean(config.getVal()));
						break;
					case "jlscThreadCount":
						JLSCMain.getInstance().setJlscThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "jlscDetailThreadCount":
						JLSCMain.getInstance().setJlscDetailThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "mainJlscThreadCount":
						JLSCMain.getInstance().setMainJlscThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "mainJLSCDetailThreadCount":
						JLSCMain.getInstance().setMainJLSCDetailThreadCount(Integer.parseInt(config.getVal()));
						break;
					case "isSendEmail":
						MailSender.getInstance().setSendEmail(Boolean.parseBoolean(config.getVal()));
						break;
					case "isPush":
						JPushUtil.getInstance().setPush(Boolean.parseBoolean(config.getVal()));
						break;
					case "jpushMasterSecret":
						JPushUtil.getInstance().setJpushMasterSecret(config.getVal());
						break;
					case "jpushAppKey":
						JPushUtil.getInstance().setJpushAppKey(config.getVal());
						break;
					case "jpushAlias":
						JPushUtil.getInstance().setJpushAlias(config.getVal());
						break;
					case "displayName":
						MailSender.getInstance().setDisplayName(config.getVal());
						break;
					case "toAddress":
						MailSender.getInstance().setToAddress(config.getVal());
						break;
					case "subject":
						MailSender.getInstance().setSubject(config.getVal());
						break;
					case "emails":
						MailSender.getInstance().setMail(config.getVal());
						break;
					default:
						logger.warn("Not Found The Key = " + config);
						break;
					}
				} catch (Exception e) {
					logger.warn("Restart Config Exception = " + e.getMessage(), e.getCause());
				}
			}
		}
	}
}
