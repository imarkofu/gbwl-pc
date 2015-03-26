package me.gbwl.pc.dao.mysql;

import me.gbwl.pc.base.BaseDaoMysqlImpl;
import me.gbwl.pc.dao.TbPostDao;
import me.gbwl.pc.model.TbPost;
import org.springframework.stereotype.Repository;

@Repository("tbPostDao")
public class TbPostDaoImpl extends BaseDaoMysqlImpl<TbPost, Integer> implements
		TbPostDao {
	public TbPostDaoImpl() {
		super(TbPost.class);
	}
}