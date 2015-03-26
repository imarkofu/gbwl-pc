package me.gbwl.pc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.TyPostDao;
import me.gbwl.pc.model.TyPost;

import org.springframework.stereotype.Service;

@Service("tyPostService")
public class TyPostService extends BaseService<TyPost, Integer> {
	private TyPostDao tyPostDao;
	@Resource(name = "tyPostDao")
	public void setTyPostDao(TyPostDao tyPostDao) {
		this.tyPostDao = tyPostDao;
		this.baseDao = tyPostDao;
	}
	
	public void delAll() {
		tyPostDao.del("delete from ty_post", new ArrayList<Object>());
	}
}