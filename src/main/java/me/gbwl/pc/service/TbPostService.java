package me.gbwl.pc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.TbPostDao;
import me.gbwl.pc.model.TbPost;

import org.springframework.stereotype.Service;

@Service("tbPostService")
public class TbPostService extends BaseService<TbPost, Integer> {
	private TbPostDao tbPostDao;
	@Resource(name = "tbPostDao")
	public void setTbPostDao(TbPostDao tbPostDao) {
		this.tbPostDao = tbPostDao;
		this.baseDao = tbPostDao;
	}
	
	public void delAll() {
		tbPostDao.del("delete from tb_post", new ArrayList<Object>());
	}
}