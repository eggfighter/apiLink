package com.kt.psso.onm.otp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OtpController {

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

	/*@RequestMapping(method = RequestMethod.OPTIONS, value = "/**")
	public void manageOptions(HttpServletResponse response) {
		response.setHeader("Allow", "GET, HEAD, POST, OPTIONS");
	}*/

	@RequestMapping(method = RequestMethod.GET, value = "/member/otp.do")
	public ModelAndView otp(@Valid MemberVo memberVo, HttpServletRequest req, HttpServletResponse res,
			HttpSession session) {
		
		String userId = authUtil.getUserId(session);
		
		memberVo = memberService.get(userId);

		
		// String telNo = memberVo.getTelNo();
		 
		// String contractnum = memberVo.getContractnum();
		 
		 /*if(telNo != null && contractnum != null){ mav = new
		 ModelAndView("member.otp"); }else {*/
		
		mav = new ModelAndView("member.otpEmail");
		//}

		mav.addObject("memberVo", memberVo);
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/member/sendSms.do")
	public ModelAndView sendSms(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@Valid MemberVo memberVo) {

		String userId = authUtil.getUserId(session);
		memberVo = memberService.get(userId);
		String recvrTel = memberVo.getTelNo();

		memberVo = memberService.getOtpSeq(userId);

		String otpSeq = memberVo.getOtpSeq();

		memberVo = memberService.getOtpVerfNo(otpSeq);

		memberVo.setTelNo(recvrTel);
		memberVo.setOtpSeq(otpSeq);
		memberVo.setUserId(userId);
		memberVo.setSendDiv("020");

		memberService.insertSendHist(memberVo);

		KhubSms smsSender = new KhubSms();

		String senderTel = "010";
		String message = "OTP 인증 번호 입니다. 인증번호 입력란에 입력하여 주십시오. ["
				+ memberVo.getOtpVerfNo() + "]";
		smsSender.sendSms(recvrTel, senderTel, message);

		mav = new ModelAndView("member.otp");
		mav.addObject("memberVo", memberVo);
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/member/sendEmail.do")
	public ModelAndView sendEmail(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@Valid MemberVo memberVo) throws Exception {

		String referer = req.getHeader("referer");

		// String referer_sub = referer.substring(0, 23);

		String message = "";

		LOG.info("referer = " + referer);

		if (!referer.equals("https://pssoonm.kt.com/member/otp.do")
				&& !referer
						.equals("https://pssoonm.kt.com/member/sendEmail.do")) {
			message = "1";
			LOG.info("referer_sub = " + referer);
			mav = new ModelAndView("member.msg");
		}

		if (message != "1") {

			String userId = authUtil.getUserId(session);
			memberVo = memberService.get(userId);
			String userMail = memberVo.getEmail();
			String otherm = memberVo.getOtherm();

			memberVo = memberService.getOtpSeq(userId);

			String otpSeq = memberVo.getOtpSeq();

			memberVo = memberService.getOtpVerfNo(otpSeq);

			memberVo.setEmail(userMail);
			memberVo.setOtherm(otherm);
			memberVo.setOtpSeq(otpSeq);
			memberVo.setUserId(userId);
			memberVo.setSendDiv("010");
			
			

			memberService.insertSendHist(memberVo);

			MailUtil mail = new MailUtil();

			String otpVerfNo = memberVo.getOtpVerfNo();

			mail.SendOtp(userMail, otpVerfNo);
			
			//LOG.info(otpVerfNo);

			LOG.info("Mail 전송 성공");

			mav = new ModelAndView("member.otpEmail");
			mav.addObject("memberVo", memberVo);
		}
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/member/confirm.do")
	public ModelAndView confirm(HttpServletRequest req,
			HttpServletResponse res, HttpSession session,
			@Valid MemberVo memberVo) {

		String referer = req.getHeader("referer");
		// String referer_sub = referer.substring(0, 23);

		String message = "";

		LOG.info("referer = " + referer);

		if (!referer.equals("https://pssoonm.kt.com/member/sendEmail.do")) {
			message = "1";
			LOG.info("referer_sub = " + referer);
			mav = new ModelAndView("member.msg");
		}

		if (message != "1") {

			String inOtpVerfNo = memberVo.getInOtpVerfNo();
			String otpSeq = memberVo.getOtpSeq();

			memberVo = memberService.getOtpVerfNo(otpSeq);
			String otpVerfNo = memberVo.getOtpVerfNo();

			boolean result;

			if (inOtpVerfNo.equals(otpVerfNo)) {
				result = true;

			} else {
				result = false;
			}
			mav = new ModelAndView("member.result");

			mav.addObject("result", result);
		}

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
