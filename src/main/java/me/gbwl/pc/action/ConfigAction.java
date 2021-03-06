package me.gbwl.pc.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.Config;
import me.gbwl.pc.service.ConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/config")
public class ConfigAction extends BaseAction<Config, Integer> {

	private ConfigService configService;
	@Resource(name="configService")
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
		this.setBaseService(configService, "config");
	}
	
	@RequestMapping(value="/restartConfig.do",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> restartConfig() {
		Map<String, Object> result = new HashMap<String, Object>();
		configService.restartConfig();
		result.put("result", true);
		return result;
	}
	
	@Override
	protected Result validate(Config form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Config getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
