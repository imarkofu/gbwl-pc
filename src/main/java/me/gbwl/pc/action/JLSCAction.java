package me.gbwl.pc.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.gbwl.pc.base.Result;
import me.gbwl.pc.model.JLSC;
import me.gbwl.pc.service.JLSCService;

@Controller
@RequestMapping(value="/jlsc")
public class JLSCAction extends BaseAction<JLSC, Integer> {
	private JLSCService jlscService;
	@Resource(name="jlscService")
	public void setJlscService(JLSCService jlscService) {
		this.jlscService = jlscService;
		this.setBaseService(jlscService, "jlsc");
	}

	@Override
	protected Result validate(JLSC form, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JLSC getNewObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
