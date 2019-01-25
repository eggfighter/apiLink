package com.kt.psso.onm.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kt.psso.onm.admin.member.MemberService;
import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.KhubSms;
import com.kt.psso.onm.util.AuthUtil;
import com.kt.psso.onm.util.MailUtil;

@Controller
public class LoginController {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	/**
	 * @uml.property  name="mav"
	 * @uml.associationEnd  
	 */
	private ModelAndView mav;
	/**
	 * @uml.property  name="memberService"
	 * @uml.associationEnd  
	 */
	private MemberService memberService;
	/**
	 * @uml.property  name="authUtil"
	 * @uml.associationEnd  
	 */
	private AuthUtil authUtil = new AuthUtil();
	
	 @RequestMapping(method = RequestMethod.OPTIONS, value="/**")
	    public void manageOptions(HttpServletResponse response) {
	      response.setHeader("Allow", "GET, HEAD, POST, OPTIONS");
	    }

	@RequestMapping(method=RequestMethod.GET, value="/login.do")
	//@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		String message = null;
		String result =null;
		
		
		LOG.debug("1+++++++++++++++"+message+"+++++++++++++");
		AuthenticationException exception = (AuthenticationException) req.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		//LOG.debug("2+++++++++++++++"+exception.getLocalizedMessage()+"+++++++++++++");
		//System.out.println("+++++++++++++login+++++++++++++");
		if (exception != null){
			message = exception.getLocalizedMessage();
			
			if(message.equals("사용자 계정이 잠겨 있습니다.")){
				message = "1";//exception.getLocalizedMessage();
			} else{
				//message = "사용자 정보가 일치하지 않습니다.";
				message = exception.getLocalizedMessage();
			}
		}

		if (message == null){
			mav = new ModelAndView("member.login");			
		}
		/*else if(message.equals("비밀번호 변경 주기를 경과하였습니다. 비밀번호 변경은 관리자에게 문의하시기 바랍니다") ||  message.equals("사용자 계정이 잠겨 있습니다.") )
		{	
			mav = new ModelAndView("member.login");	
			mav.addObject("message", message);
			result = "1";
			mav.addObject("result", result);
		}*/else {
			mav = new ModelAndView("member.login");	
			mav.addObject("message", message);
		}
		
		if (LOG.isInfoEnabled()) {
			LOG.info("message=" + message);
			LOG.info("result=" + result);
		}
		
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/member/passwd.do")
	public ModelAndView passwd(HttpServletRequest req, HttpServletResponse res) {
		mav = new ModelAndView("member.passwd");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/member/mod.do")
	public ModelAndView passwdMod(HttpServletRequest req, HttpServletResponse res, @Valid MemberVo memberVo) {

		
		boolean result = memberService.pwMod(memberVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("userId = " + memberVo.getUserId());
			LOG.info("result = " + result);
		}

		mav = new ModelAndView("member.passwd");
		mav.addObject("result", result);
		return mav;
	}
		
	/**
	 * @param memberService
	 * @uml.property  name="memberService"
	 */
	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
}

