package com.kt.psso.onm.terms;

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

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.util.PagingUtil;


@Controller
public class TermsVersionController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="termsVersionService"
	 * @uml.associationEnd  
	 */
	@Autowired
	private TermsVersionService termsVersionService;

	@RequestMapping(method=RequestMethod.GET, value="/in/terms/version/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		List<TermsVersionVo> listTermsVersion = termsVersionService.list(searchVo);
		int count = termsVersionService.count(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("admin.terms.version.list");
		mav.addObject("listTermsVersion", listTermsVersion);
		PagingUtil pagingUtil = new PagingUtil(count, searchVo.getpPageNo(), searchVo.getpPageCount());
		mav.addObject("paging", pagingUtil);
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET, value="/in/terms/version/form.do")
	public ModelAndView form(HttpServletRequest req, HttpServletResponse res, TermsVersionVo termsVersionVo) {
		if (LOG.isInfoEnabled()) {
			LOG.info(termsVersionVo);
		}

		ModelAndView mav = new ModelAndView("admin.terms.version.form");
		mav.addObject("termsVersionVo", termsVersionVo);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/in/terms/version/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res, TermsVersionVo termsVersionVo) {
		if (LOG.isInfoEnabled()) {
			LOG.info(termsVersionVo);
		}

		boolean result = termsVersionService.add(termsVersionVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("admin.terms.version.result");
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/in/terms/version/del.do")
	public ModelAndView del(HttpServletRequest req, HttpServletResponse res, TermsVersionVo termsVersionVo) {
		if (LOG.isInfoEnabled()) {
			LOG.info(termsVersionVo);
		}

		boolean result = termsVersionService.del(termsVersionVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("admin.terms.version.result");
		mav.addObject("result", result);
		return mav;
	}

}
