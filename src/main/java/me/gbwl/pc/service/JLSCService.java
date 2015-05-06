package me.gbwl.pc.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.gbwl.pc.base.BaseService;
import me.gbwl.pc.dao.JLSCDao;
import me.gbwl.pc.model.JLSC;

@Service("jlscService")
public class JLSCService extends BaseService<JLSC, Integer> {

	private JLSCDao jlscDao;
	@Resource(name="jlscDao")
	public void setJlscDao(JLSCDao jlscDao) {
		this.jlscDao = jlscDao;
		this.baseDao = jlscDao;
	}
	
	public void delAll() {
		jlscDao.del("delete from jlsc", new ArrayList<Object>());
	}
}
