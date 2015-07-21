package me.gbwl.pc.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.Keywords;
import me.gbwl.pc.service.KeywordsService;
import me.gbwl.pc.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/keywords")
public class KeywordsAction extends BaseAction<Keywords, Integer> {

	private KeywordsService keywordsService;
	@Resource(name="keywordsService")
	public void setKeywordsService(KeywordsService keywordsService) {
		this.keywordsService = keywordsService;
		this.setBaseService(keywordsService, "keywords");
	}
	
	@Override
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String keywords = request.getParameter("keywords");
		String type = request.getParameter("type");
		if (StringUtil.isEmpty(keywords)) {
			result.put("result", false);
			result.put("msg", "关键词不能为空");
			return result;
		}
		if (StringUtil.isNumeric(type)) {
			result.put("result", false);
			result.put("msg", "分类不能为空");
			return result;
		}
		keywords = keywords.trim();
		Keywords tk = new Keywords();
		tk.setKeywords(keywords);
		tk.setType(Integer.parseInt(type));
		if (null ==keywordsService.searchOne(tk)) {
			tk = new Keywords();
			tk.setKeywords(keywords);
			tk.setType(Integer.parseInt(type));
			keywordsService.save(tk);
			result.put("result", true);
			result.put("msg", "存储成功");
			return result;
		} else {
			result.put("result", false);
			result.put("msg", "关键词已经存在");
			return result;
		}
	}
	
	@RequestMapping(value="/clear.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clear(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String type = request.getParameter("type");
		if (StringUtil.isEmpty(type)) {
			result.put("result", false);
			result.put("msg", "类型不能为空");
			return result;
		}
		keywordsService.delByType(Integer.parseInt(type));
		result.put("result", true);
		result.put("msg", "清除成功");
		return result;
		
	}
	
	@Override
	protected Result validate(Keywords form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Keywords getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
