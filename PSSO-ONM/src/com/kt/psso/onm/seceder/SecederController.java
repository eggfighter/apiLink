package com.kt.psso.onm.seceder;

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

import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.EventLogHelper;
import com.kt.psso.onm.util.AuthUtil;

@Controller
public class SecederController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="secederService"
	 * @uml.associationEnd  
	 */
	private SecederService secederService;
	
	/**
	 * @uml.property  name="eventLogDao"
	 * @uml.associationEnd  
	 */
	private EventLogDao eventLogDao;
	
	/**
	 * @uml.property  name="authUtil"
	 * @uml.associationEnd  
	 */
	private AuthUtil authUtil = new AuthUtil();
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new SecederValidator());
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
	//@RequestMapping(method=RequestMethod.GET, value="/seceder/list.do")
	@RequestMapping("/seceder/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, HttpSession session, SearchVo searchVo) {
		String cn = searchVo.getpCn();
		String rrn7 = searchVo.getpRrn7();
		String regdate = searchVo.getpDate();
		int count = 0;
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<SecederVo> listSeceder = null;
		if ((cn != null && !cn.equals("")) || (rrn7 != null && !rrn7.equals("")) || (regdate != null && !regdate.equals(""))) {
			listSeceder = secederService.list(searchVo);
			count = secederService.count();
		} else {
			listSeceder = new ArrayList<SecederVo>();
			count = secederService.count();
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("countSeceder = " + count);
		}

	
		ModelAndView mav = new ModelAndView("seceder.list");
		mav.addObject("listSeceder", listSeceder);
		mav.addObject("count", count);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param secederService
	 * @uml.property  name="secederService"
	 */
	@Autowired
	public void setSecederService(SecederService secederService) {
		this.secederService = secederService;
	}	
}
