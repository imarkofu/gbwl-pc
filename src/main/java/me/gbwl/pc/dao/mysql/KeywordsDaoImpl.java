package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.KeywordsDao;
import me.gbwl.pc.model.Keywords;

@Repository("keywordsDao")
public class KeywordsDaoImpl extends BaseDaoMysqlImpl<Keywords, Integer> implements KeywordsDao {

	public KeywordsDaoImpl() {
		super(Keywords.class);
	}
}
