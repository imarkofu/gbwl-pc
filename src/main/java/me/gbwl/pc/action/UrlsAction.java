package me.gbwl.pc.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.Urls;
import me.gbwl.pc.service.UrlsService;
import me.gbwl.pc.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/urls")
public class UrlsAction extends BaseAction<Urls, Integer> {

	private UrlsService urlsService;
	@Resource(name="urlsService")
	public void setUrlsService(UrlsService urlsService) {
		this.urlsService = urlsService;
		this.setBaseService(urlsService, "urls");
	}
	
	@Override
	public Map<String, Object> save(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String url = request.getParameter("url");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		if (StringUtil.isEmpty(url)) {
			result.put("result", false);
			result.put("msg", "URL不能为空");
			return result;
		}
		if (!StringUtil.isNumeric(type)) {
			result.put("result", false);
			result.put("msg", "分类不能为空");
			return result;
		}
		if (StringUtil.isEmpty(name)) {
			result.put("result", false);
			result.put("msg", "名称不能为空");
			return result;
		}
		url = url.trim();
		Urls tk = new Urls();
		tk.setUrl(url);
		tk.setType(Integer.parseInt(type));
		if (null ==urlsService.searchOne(tk)) {
			tk = new Urls();
			tk.setUrl(url);
			tk.setType(Integer.parseInt(type));
			tk.setName(name);
			urlsService.save(tk);
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
		urlsService.delByType(Integer.parseInt(type));
		result.put("result", true);
		result.put("msg", "清除成功");
		return result;
	}
	
	@Override
	protected Result validate(Urls form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Urls getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
