package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.Config;
import me.gbwl.pc.service.ConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/config")
public class ConfigAction extends BaseAction<Config, Integer> {

	private ConfigService configService;
	@Resource(name="configService")
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
		this.setBaseService(configService, "config");
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
