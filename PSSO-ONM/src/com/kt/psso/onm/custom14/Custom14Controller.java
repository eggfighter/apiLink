package com.kt.psso.onm.custom14;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.kt.psso.onm.customer.CustomerVo;

@Controller
public class Custom14Controller {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="custom14Service"
	 * @uml.associationEnd  
	 */
	private Custom14Service custom14Service;
	/*
	@InitBinder
	
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new Custom14Validator());
    }*/
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException ex) {
		FieldError error = ex.getBindingResult().getFieldError();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		ModelAndView mav = new ModelAndView("error.bind");
		mav.addObject("error", error);
		mav.addObject("allErrors", allErrors);
		return mav;
	}
	
	//@RequestMapping(method=RequestMethod.GET, value="/custom14/list.do")
	@RequestMapping("/custom14/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) throws Exception {
		String cn = searchVo.getpCn();
		String rrn7 = searchVo.getpRrn7();
		String regdate = searchVo.getpDate();
		
		int count = 0;
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<CustomerVo> listCustom14 = null;
		if ((cn != null && !cn.equals("")) || (rrn7 != null && !rrn7.equals("")) || (regdate != null && !regdate.equals(""))) {
			listCustom14 = custom14Service.list(searchVo);
			count = custom14Service.count();
		} else {
			listCustom14 = new ArrayList<CustomerVo>();
			count = custom14Service.count();
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("countCustomer = " + count);
		}
		
	
		ModelAndView mav = new ModelAndView("custom14.list");
		mav.addObject("listCustom14", listCustom14);
		mav.addObject("count", count);
		return mav;
	}

	@RequestMapping("/custom14/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res, HttpSession session, CustomerVo customerVo) throws Exception {
		String cn = customerVo.getCn();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + cn);
		}

		if (cn != null && !cn.equals("")) {
			customerVo = custom14Service.get(session, cn);
		}
		
		ModelAndView mav = new ModelAndView("custom14.view");
		mav.addObject("custom14Vo", customerVo);
		return mav;
	}

	@RequestMapping("/custom14/form.do")
	public ModelAndView form(HttpServletRequest req, HttpServletResponse res, HttpSession session, CustomerVo customerVo) throws Exception{
		String cn = customerVo.getCn();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + cn);
		}
		
		if (cn != null && !cn.equals("")) {
			customerVo = custom14Service.get(session, cn);
		}
		ModelAndView mav = new ModelAndView("custom14.form");
		mav.addObject("custom14Vo", customerVo);
		mav.addObject("pwdhintArr", custom14Service.getPwdHintArr());
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/custom14/mod.do")
	public ModelAndView mod(HttpServletRequest req, HttpServletResponse res, HttpSession session, @Valid CustomerVo customerVo) {
		boolean result = custom14Service.mod(session, customerVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + customerVo.getCn());
			LOG.info("result = " + result);
		}
		
		ModelAndView mav = new ModelAndView("custom14.result");
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/custom14/del.do")
	public ModelAndView del(HttpServletRequest req, HttpServletResponse res, @Valid CustomerVo customerVo) {
		boolean result = custom14Service.del(customerVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + customerVo.getCn());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("custom14.result");
		mav.addObject("result", result);
		return mav;
	}


	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param custom14Service
	 * @uml.property  name="custom14Service"
	 */
	@Autowired
	public void setCustom14Service(Custom14Service custom14Service) {
		this.custom14Service = custom14Service;
	}	
}
