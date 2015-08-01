package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.ConfigDao;
import me.gbwl.pc.model.Config;

@Repository("configDao")
public class ConfigDaoImpl extends BaseDaoMysqlImpl<Config, Integer> implements ConfigDao {

	public ConfigDaoImpl() {
		super(Config.class);
	}
}
