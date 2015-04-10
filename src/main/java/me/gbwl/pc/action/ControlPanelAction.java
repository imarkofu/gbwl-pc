package me.gbwl.pc.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import me.gbwl.pc.base.ContentHolder;
import me.gbwl.pc.util.SpringUtil;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: ControlPanelAction.java<br>
 * @package: me.gbwl.pc.action<br>
 * @Description:TODO<br>
 * @author gbwl<br>
 * @date 2015年2月6日 下午1:55:12<br>
 */
@Controller
@RequestMapping(value="/controlPanel")
public class ControlPanelAction {

	private static final Logger logger = Logger.getLogger(ControlPanelAction.class);
	
	@RequestMapping(value="/preSearch.do", method=RequestMethod.GET)
	public ModelAndView preSearch(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("controlPanel/controlPanelSearch");
		mav.addObject("tiebaSize", SpringUtil.getInstance().getTieBaSize());
		mav.addObject("tiebaDetailSize", SpringUtil.getInstance().getTieBaDetailSize());
		mav.addObject("tianyaSize", SpringUtil.getInstance().getTianYaSize());
		mav.addObject("tianyaDetailSize", SpringUtil.getInstance().getTianYaDetailSize());
		mav.addObject("sendEmail", ContentHolder.constant.isSendEmail()?1:0);
		mav.addObject("push", ContentHolder.constant.isPush()?1:0);
		return mav;
	}
	
	@RequestMapping(value="/clearTieba.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clearTieba(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			ContentHolder.tbPostService.delAll();;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.put("result", true);result.put("msg", "清除成功");
		return result;
	}
	@RequestMapping(value="/clearTianya.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> clearTianya(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		ContentHolder.tyPostService.delAll();
		result.put("result", true);result.put("msg", "清除成功");
		return result;
	}
	
	@RequestMapping(value="/changeSendEmail.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeSendEmail(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		ContentHolder.constant.setSendEmail(ContentHolder.constant.isSendEmail()?false:true);
		result.put("result", true);result.put("msg", "重置成功");
		return result;
	}
	
	@RequestMapping(value="/changePush.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePush(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		ContentHolder.constant.setPush(ContentHolder.constant.isPush()?false:true);
		result.put("result", true);result.put("msg", "重置成功");
		return result;
	}
	
	@RequestMapping(value="/restartTieBa.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> restartTieBa(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SpringUtil.getInstance().restartTieBa();
			result.put("result", true);result.put("msg", "重启成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);result.put("msg", "重启失败");
		}		
		logger.info(result);
		return result;
	}
	
	@RequestMapping(value="/restartTianYa.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  restartTianYa() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SpringUtil.getInstance().restartTianYa();
			
			result.put("result", true);result.put("msg", "重启成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);result.put("msg", "重启失败");
		}
		logger.info(result);
		return result;
	}
	
	
}