package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.TbPost;
import me.gbwl.pc.service.TbPostService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/tb")
public class TieBaAction extends BaseAction<TbPost, Integer> {

	private TbPostService tbPostService;
	@Resource(name="tbPostService")
	public void setTbPostService(TbPostService tbPostService) {
		this.tbPostService = tbPostService;
		this.setBaseService(tbPostService, "tb");
	}

	@Override
	protected Result validate(TbPost form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TbPost getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
}