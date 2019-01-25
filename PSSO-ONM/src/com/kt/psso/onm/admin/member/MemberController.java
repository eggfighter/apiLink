package com.kt.psso.onm.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.kt.psso.onm.util.PagingUtil;


@Controller
public class MemberController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="memberService"
	 * @uml.associationEnd  
	 */
	private MemberService memberService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MemberValidator());
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
	
	@RequestMapping("/in/member/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		List<MemberVo> listMember = memberService.list(searchVo);
		int count = memberService.count(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("admin.member.list");
		mav.addObject("listMember", listMember);
		PagingUtil pagingUtil = new PagingUtil(count, searchVo.getpPageNo(), searchVo.getpPageCount());
		mav.addObject("paging", pagingUtil);
		mav.addObject("roles", memberService.getRoles());
		return mav;
	}

	@RequestMapping("/in/member/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res, MemberVo memberVo) {
		String userId = memberVo.getUserId();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("/in/member/view.do userId = " + userId);
		}

		if (userId != null && !userId.equals("")) {
			memberVo = memberService.get(userId);
		}
		ModelAndView mav = new ModelAndView("admin.member.view");
		mav.addObject("memberVo", memberVo);
		mav.addObject("roles", memberService.getRoles());
		return mav;
	}

	@RequestMapping("/in/member/form.do")
	public ModelAndView form(HttpServletRequest req, HttpServletResponse res, MemberVo memberVo) {
		String userId = memberVo.getUserId();

		if (LOG.isInfoEnabled()) {
			LOG.info("userId = " + userId);
		}

		if (userId != null && !userId.equals("")) {
			memberVo = memberService.get(userId);
		}
		ModelAndView mav = new ModelAndView("admin.member.form");
		mav.addObject("memberVo", memberVo);
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/in/member/add.do")
	public ModelAndView add(HttpServletRequest req, HttpServletResponse res, @Valid MemberVo memberVo) {
		boolean result = memberService.add(memberVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("userId = " + memberVo.getUserId());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("admin.member.result");
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/in/member/mod.do")
	public ModelAndView mod(HttpServletRequest req, HttpServletResponse res, @Valid MemberVo memberVo) {
		boolean result = memberService.mod(memberVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("userId = " + memberVo.getUserId());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("admin.member.result");
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST, value="/in/member/del.do")
	public ModelAndView del(HttpServletRequest req, HttpServletResponse res, MemberVo memberVo) {
		boolean result = memberService.del(memberVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("userId = " + memberVo.getUserId());
			LOG.info("result = " + result);
		}

		ModelAndView mav = new ModelAndView("admin.member.result");
		mav.addObject("result", result);
		return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param memberService
	 * @uml.property  name="memberService"
	 */
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

}
