package me.gbwl.pc.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.TiebaKeyword;
import me.gbwl.pc.service.TiebaKeywordService;
import me.gbwl.pc.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String keywords = request.getParameter("keywords");
		if (StringUtil.isEmpty(keywords)) {
			result.put("result", false);
			result.put("msg", "关键词不能为空");
			return result;
		}
		TiebaKeyword tk = new TiebaKeyword();
		tk.setKeywords(keywords);
		if (null ==tiebaKeywordService.searchOne(tk)) {
			return super.save(request);
		} else {
			result.put("result", false);
			result.put("msg", "关键词已经存在");
			return result;
		}
	}
	
	@RequestMapping(value="/clearTiebaKeyword.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clearTiebaKeyword() {
		Map<String, Object> result = new HashMap<String, Object>();
		tiebaKeywordService.delAll();
		result.put("result", true);
		result.put("msg", "清除成功");
		return result;
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
