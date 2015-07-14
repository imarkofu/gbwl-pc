package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.TianyaKeyword;
import me.gbwl.pc.service.TianyaKeywordService;

@Controller
@RequestMapping(value="/tianyaKeyword")
public class TianyaKeywordAction extends BaseAction<TianyaKeyword, Integer> {

	private TianyaKeywordService tianyaKeywordService;
	@Resource(name="tianyaKeywordService")
	public void setTianyaKeywordService(TianyaKeywordService tianyaKeywordService) {
		this.tianyaKeywordService = tianyaKeywordService;
		this.setBaseService(tianyaKeywordService, "tianyaKeyword");
	}

	@Override
	protected Result validate(TianyaKeyword form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TianyaKeyword getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
