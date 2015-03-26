package me.gbwl.pc.dao.mysql;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.TyPostDao;
import me.gbwl.pc.model.TyPost;

import org.springframework.stereotype.Repository;

@Repository("tyPostDao")
public class TyPostDaoImpl extends BaseDaoMysqlImpl<TyPost, Integer> implements
		TyPostDao {
	public TyPostDaoImpl() {
		super(TyPost.class);
	}
}