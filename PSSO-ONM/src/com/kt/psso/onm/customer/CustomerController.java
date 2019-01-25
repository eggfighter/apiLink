package com.kt.psso.onm.customer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
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
import com.kt.psso.onm.util.AuthUtil;

@Controller
public class CustomerController {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="customerService"
	 * @uml.associationEnd  
	 */
	private CustomerService customerService;
	
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
        binder.setValidator(new CustomerValidator());
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
	//@RequestMapping(method=RequestMethod.GET, value="/customer/list.do")
	@RequestMapping("/customer/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res,HttpSession session, SearchVo searchVo) {
		
		
		String cn = searchVo.getpCn();
		String rrn7 = searchVo.getpRrn7();
		String regdate = searchVo.getpDate();
		int count = 0;
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<CustomerVo> listCustomer = null;
		if ((cn != null && !cn.equals("")) || (rrn7 != null && !rrn7.equals("")) || (regdate != null && !regdate.equals(""))) {
			listCustomer = customerService.list(searchVo);
			count = customerService.count();
		} else {
			listCustomer = new ArrayList<CustomerVo>();
			count = customerService.count();
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("countCustomer = " + count);
		}
		
	
		ModelAndView mav = new ModelAndView("customer.list");
		mav.addObject("listCustomer", listCustomer);
		mav.addObject("count", count);
		return mav;
	}

	@RequestMapping("/customer/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res, HttpSession session, CustomerVo customerVo) throws Exception {
		String cn = customerVo.getCn();
		if (cn != null && !cn.equals("")) {
			customerVo = customerService.get(session, cn);
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + cn);
		}

		ModelAndView mav = new ModelAndView("customer.view");
		mav.addObject("customerVo", customerVo);
		return mav;
	}

	
	@RequestMapping("/customer/form.do")
	public ModelAndView form(HttpServletRequest req, HttpServletResponse res, HttpSession session, CustomerVo customerVo) throws Exception {
		String cn = customerVo.getCn();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + cn);
			
		}
		
		if (cn != null && !cn.equals("")) {
			customerVo = customerService.get(session, cn);
		}
		ModelAndView mav = new ModelAndView("customer.form");
		mav.addObject("customerVo", customerVo);
		mav.addObject("pwdhintArr", customerService.getPwdHintArr());
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/customer/mod.do")
	public ModelAndView mod(HttpServletRequest req, HttpServletResponse res, HttpSession session, @Valid CustomerVo customerVo) {
		boolean result = customerService.mod(session, customerVo);
        
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + customerVo.getCn());
			LOG.info("mod result = " + result);
		}

		ModelAndView mav = new ModelAndView("customer.result");
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/customer/del.do")
	public ModelAndView del(HttpServletRequest req, HttpServletResponse res, @Valid CustomerVo customerVo) {
		boolean result = customerService.del(customerVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + customerVo.getCn());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("customer.result");
		mav.addObject("result", result);
		return mav;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/customer/pwreset.do")
	public ModelAndView pwreset(HttpServletRequest req, HttpServletResponse res, CustomerVo customerVo) {
		boolean result = customerService.pwreset(customerVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + customerVo.getCn());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("customer.reset");
		//mav.addObject("result", result);
		return mav;
	}
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param customerService
	 * @uml.property  name="customerService"
	 */
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}	
}
