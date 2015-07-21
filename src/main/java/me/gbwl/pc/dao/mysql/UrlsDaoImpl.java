package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.UrlsDao;
import me.gbwl.pc.model.Urls;

@Repository("urlsDao")
public class UrlsDaoImpl extends BaseDaoMysqlImpl<Urls, Integer> implements UrlsDao {

	public UrlsDaoImpl() {
		super(Urls.class);
	}
}
