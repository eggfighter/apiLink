package com.kt.psso.onm.chknum;

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
public class ChkNumController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="chkNumService"
	 * @uml.associationEnd  
	 */
	private ChkNumService chkNumService;

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new ChkNumValidator());
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
	
	@RequestMapping(method=RequestMethod.GET, value="/chkNum/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		String email = searchVo.getpEmail();
		String mobile = searchVo.getpMobile();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<ChkNumVo> listChkNum = null;
		if ((email != null && !email.equals("")) || (mobile != null && !mobile.equals(""))) {
			listChkNum = chkNumService.list(searchVo);
		} else {
			listChkNum = new ArrayList<ChkNumVo>();
		}
	
		ModelAndView mav = new ModelAndView("chkNum.list");
		mav.addObject("listChkNum", listChkNum);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param chkNumService
	 * @uml.property  name="chkNumService"
	 */
	@Autowired
	public void setChkNumService(ChkNumService chkNumService) {
		this.chkNumService = chkNumService;
	}	
}
