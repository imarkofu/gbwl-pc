package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.TyPost;
import me.gbwl.pc.service.TyPostService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ty")
public class TianYaAction extends BaseAction<TyPost, Integer> {

	private TyPostService tyPostService;
	@Resource(name="tyPostService")
	public void setTyPostService(TyPostService tyPostService) {
		this.tyPostService = tyPostService;
		this.setBaseService(tyPostService, "ty");
	}

	@Override
	protected Result validate(TyPost form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TyPost getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
