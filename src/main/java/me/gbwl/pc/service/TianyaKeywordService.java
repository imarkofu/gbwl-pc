package me.gbwl.pc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.TianyaKeywordDao;
import me.gbwl.pc.model.TianyaKeyword;

@Service("tianyaKeywordService")
public class TianyaKeywordService extends BaseService<TianyaKeyword, Integer> {

	private TianyaKeywordDao tianyaKeywordDao;
	@Resource(name="tianyaKeywordDao")
	public void setTianyaKeywordDao(TianyaKeywordDao tianyaKeywordDao) {
		this.tianyaKeywordDao = tianyaKeywordDao;
		this.baseDao = tianyaKeywordDao;
	}
	
	public void delAll() {
		tianyaKeywordDao.del("delete from tianya_keyword", new ArrayList<Object>());
	}
}
