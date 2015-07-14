package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.TianyaKeywordDao;
import me.gbwl.pc.model.TianyaKeyword;

@Repository("tianyaKeywordDao")
public class TianyaKeywordDaoImpl extends BaseDaoMysqlImpl<TianyaKeyword, Integer> implements TianyaKeywordDao {
	public TianyaKeywordDaoImpl() {
		super(TianyaKeyword.class);
	}
}
