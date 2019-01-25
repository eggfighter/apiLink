package com.kt.psso.onm.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET, value="/error.do")
	public ModelAndView error(HttpServletRequest req, HttpServletResponse res) {
		int statusCode 		= ((Integer)req.getAttribute("javax.servlet.error.status_code")).intValue();
		String message		= (String) req.getAttribute("javax.servlet.error.message");
		Exception exception	= (Exception) req.getAttribute("javax.servlet.error.exception");
		String viewName = "error";
		switch (statusCode) {
		case 403 :
		case 404 :
		case 500 :
			viewName += statusCode;
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("statusCode = " + statusCode);
			LOG.info("message = " + message);
		}

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("exception", exception);
		return mav;
	}

}
