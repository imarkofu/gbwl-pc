package me.gbwl.pc.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.TiebaKeywordDao;
import me.gbwl.pc.model.TiebaKeyword;

import org.springframework.stereotype.Service;

@Service("tiebaKeywordService")
public class TiebaKeywordService extends BaseService<TiebaKeyword, Integer> {

	private TiebaKeywordDao tiebaKeywordDao;
	@Resource(name="tiebaKeywordDao")
	public void setTiebaKeywordDao(TiebaKeywordDao tiebaKeywordDao) {
		this.tiebaKeywordDao = tiebaKeywordDao;
		this.baseDao = tiebaKeywordDao;
	}
	
	public void delAll() {
		tiebaKeywordDao.del("delete from tieba_keyword", new ArrayList<Object>());
	}
	
	public int getCount() {
		String sql = "select count(id) from tieba_keyword";
		List<Object> params = new ArrayList<Object>();
		return tiebaKeywordDao.getInt(sql, params);
	}
}
