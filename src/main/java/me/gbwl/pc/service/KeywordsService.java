package me.gbwl.pc.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.KeywordsDao;
import me.gbwl.pc.model.Keywords;

@Service("keywordsService")
public class KeywordsService extends BaseService<Keywords, Integer> {

	private KeywordsDao keywordsDao;
	@Resource(name="keywordsDao")
	public void setKeywordsDao(KeywordsDao keywordsDao) {
		this.keywordsDao = keywordsDao;
		this.baseDao = keywordsDao;
	}
	
	public void delByType(Integer type) {
		if (type == null)
			return ;
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		keywordsDao.del("delete from keywords where type=?", params);
	}
	
	public int getCount(Integer type) {
		if (type == null)
			return 0;
		String sql = "select count(id) from keywords where type=?";
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		return keywordsDao.getInt(sql, params);
	}
	
	public List<Keywords> getAll(Integer type) {
		Keywords k = new Keywords();
		k.setType(type);
		return keywordsDao.search(k);
	}
}
