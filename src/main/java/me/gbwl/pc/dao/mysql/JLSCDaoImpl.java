package me.gbwl.pc.dao.mysql;

import org.springframework.stereotype.Repository;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.JLSCDao;
import me.gbwl.pc.model.JLSC;

@Repository("jlscDao")
public class JLSCDaoImpl extends BaseDaoMysqlImpl<JLSC, Integer> implements JLSCDao {

	public JLSCDaoImpl() {
		super(JLSC.class);
	}
}
