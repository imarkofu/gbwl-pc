package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.TiebaKeyword;
import me.gbwl.pc.service.TiebaKeywordService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/tiebaKeyword")
public class TiebaKeywordAction extends BaseAction<TiebaKeyword, Integer> {

	private TiebaKeywordService tiebaKeywordService;
	@Resource(name="tiebaKeywordService")
	public void setTiebaKeywordService(TiebaKeywordService tiebaKeywordService) {
		this.tiebaKeywordService = tiebaKeywordService;
		this.setBaseService(tiebaKeywordService, "tiebaKeyword");
	}

	@Override
	protected Result validate(TiebaKeyword form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TiebaKeyword getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
