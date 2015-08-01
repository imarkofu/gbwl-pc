package me.gbwl.pc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.ConfigDao;
import me.gbwl.pc.model.Config;

@Service("configService")
public class ConfigService extends BaseService<Config, Integer> {

	private ConfigDao configDao;
	@Resource(name="configDao")
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
		this.baseDao = configDao;
	}
}
