package com.kt.psso.onm.admin.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="configService"
	 * @uml.associationEnd  
	 */
	private ConfigService configService;
	
	@RequestMapping(method=RequestMethod.GET, value="/in/config/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) {
		String[] onmKeys = configService.onmKeys();
		Map<String, String> onmMap = configService.onmMap();
		
		String[] menuKeys = configService.menuKeys();
		Map<String, String> menuMap = configService.menuMap();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("onmConfig.size() = " + onmKeys.length);
			LOG.info("menuConfig.size() = " + menuKeys.length);
		}

		ModelAndView mav = new ModelAndView("admin.config.view");
		mav.addObject("onmKeys", onmKeys);
		mav.addObject("onmMap", onmMap);
		mav.addObject("menuKeys", menuKeys);
		mav.addObject("menuMap", menuMap);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param configService
	 * @uml.property  name="configService"
	 */
	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
}
