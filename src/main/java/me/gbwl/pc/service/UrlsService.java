package me.gbwl.pc.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.UrlsDao;
import me.gbwl.pc.model.Urls;

import org.springframework.stereotype.Service;

@Service("urlsService")
public class UrlsService extends BaseService<Urls, Integer> {

	private UrlsDao urlsDao;
	@Resource(name="urlsDao")
	public void setUrlsDao(UrlsDao urlsDao) {
		this.urlsDao = urlsDao;
		this.baseDao = urlsDao;
	}
	
	public void delByType(Integer type) {
		if (type == null)
			return ;
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		urlsDao.del("delete from urls where type=?", params);
	}
	
	public int getCount(Integer type) {
		if (type == null)
			return 0;
		String sql = "select count(id) from urls where type=?";
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		return urlsDao.getInt(sql, params);
	}
	
	public List<Urls> getAll(Integer type) {
		Urls k = new Urls();
		k.setType(type);
		return urlsDao.search(k);
	}
}
