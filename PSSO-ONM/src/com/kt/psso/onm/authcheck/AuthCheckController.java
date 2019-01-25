package com.kt.psso.onm.authcheck;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.kt.psso.onm.common.SearchVo;

@Controller
public class AuthCheckController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="authCheckService"
	 * @uml.associationEnd  
	 */
	private AuthCheckService authCheckService;

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new AuthCheckValidator());
    }
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException ex) {
		FieldError error = ex.getBindingResult().getFieldError();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		ModelAndView mav = new ModelAndView("error.bind");
		mav.addObject("error", error);
		mav.addObject("allErrors", allErrors);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/authcheck/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		String cn= searchVo.getpCn();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<AuthCheckSearchVo> listAuthCheck = null;
		if ((cn != null && !cn.equals("")) ) {
			listAuthCheck = authCheckService.list(searchVo);
		} else {
			listAuthCheck = new ArrayList<AuthCheckSearchVo>();
		}
	
		ModelAndView mav = new ModelAndView("authcheck.list");
		mav.addObject("listAuthCheck", listAuthCheck);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param authCheckService
	 * @uml.property  name="authCheckService"
	 */
	@Autowired
	public void setAuthCheckService(AuthCheckService authCheckService) {
		this.authCheckService = authCheckService;
	}	
}
