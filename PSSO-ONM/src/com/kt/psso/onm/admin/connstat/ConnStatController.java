package com.kt.psso.onm.admin.connstat;

import java.io.IOException;
import java.util.List;

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
public class ConnStatController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="connStatService"
	 * @uml.associationEnd  
	 */
	private ConnStatService connStatService;
	
	@RequestMapping(method=RequestMethod.GET, value="/in/connstat/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) throws IOException {

		List<String[]> listRealname = connStatService.listRealname();
		List<String[]> listIpin = connStatService.listIpin();
		List<String[]> listHolder = connStatService.listHolder();

		if (LOG.isInfoEnabled()) {
			LOG.info("listRealname.size() = " + listRealname.size());
			LOG.info("listIpin.size() = " + listIpin.size());
			LOG.info("listHolder.size() = " + listHolder.size());
		}

		ModelAndView mav = new ModelAndView("admin.connstat.view");
		mav.addObject("listRealname", listRealname);
		mav.addObject("listIpin", listIpin);
		mav.addObject("listHolder", listHolder);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param connStatService
	 * @uml.property  name="connStatService"
	 */
	@Autowired
	public void setConnStatService(ConnStatService connStatService) {
		this.connStatService = connStatService;
	}
	
}
