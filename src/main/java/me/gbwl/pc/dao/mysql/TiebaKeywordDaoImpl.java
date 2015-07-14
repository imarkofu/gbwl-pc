package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.TiebaKeywordDao;
import me.gbwl.pc.model.TiebaKeyword;

@Repository("tiebaKeywordDao")
public class TiebaKeywordDaoImpl extends BaseDaoMysqlImpl<TiebaKeyword, Integer> implements TiebaKeywordDao {
	public TiebaKeywordDaoImpl(){
		super(TiebaKeyword.class);
	}
}
