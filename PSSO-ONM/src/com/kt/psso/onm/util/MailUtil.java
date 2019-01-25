package com.kt.psso.onm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

//import javax.inject.Inject;
//import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;



public class MailUtil {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	//private MailService mailService;
	
	/*
	public void sendMailType1(String subject, String cntnts, String fromUser, String toUser, String[] toCC) {
		mailService.sendMail(subject, cntnts, fromUser, toUser, toCC);
	}*/
	//@Autowired
	//private MailService mailService;
	

	
	
public void SendOtp (String userMail, String otpVerfNo ) throws Exception {
		
		try{
		
		
			 HtmlEmail email = new HtmlEmail();
			 
			 String siteUrl = "http://pssoonm.kt.com:80/";
				
				
			 email.setHostName("smtp.olleh.com"); // smtp server
	
			 email.setSmtpPort(25);
	
			 email.setDebug(false);
	
			 email.setCharset("UTF-8");
	
			 email.setTLS(false);
	
			 email.setFrom("psso@olleh.com");
	
			 email.addTo(userMail);
			 
	
			 email.setSubject("협력사 사이트 회원의 OTP 인증을 위한 메일입니다.");
			 
			 Calendar cal = Calendar.getInstance();
	   		 	SimpleDateFormat formatter = new SimpleDateFormat( "yyyy.MM.dd" );
	   		 	String today = formatter.format( cal.getTime() );
		
			// 메일 내용    	  
			StringBuilder mailBody = new StringBuilder();
			
			  
			mailBody.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
			mailBody.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
			mailBody.append("<head>");
			mailBody.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
			mailBody.append("<title>Untitled Document</title>");
			mailBody.append("</head>");
			mailBody.append("");
			mailBody.append("<body >");
			mailBody.append("<table  cellpadding='0'  cellspacing='0' align='center' style=' width:700px; height:auto; font-size:12px;");
			mailBody.append("border-left:#dfdfdf 1px solid; border-bottom:#dfdfdf 1px solid; line-height:180%'>");
			mailBody.append("<tr>");
			mailBody.append("<td style=' height:95px;  font-size:14px; font-weight:bold; color:#656565;  background-image:url("+siteUrl+"images/mail/bg_top.jpg); background-repeat:no-repeat;");
			mailBody.append(" vertical-align:middle; padding-right:40px; text-align:right;'>"+today+"</td>");
			mailBody.append("</tr>");
			mailBody.append("<tr>");
			mailBody.append("<td  style=' border-right:#dfdfdf 1px solid; height:70px; padding-left:30px; '><img src='"+siteUrl+"images/mail/img_title.png' align='absmiddle' alt='olleh 파트너 인증 시스템 ' /></td>");
			mailBody.append("</tr>");
			mailBody.append("<tr>");
			mailBody.append(" <td style='  padding-left:30px; border-right:#dfdfdf 1px solid;'> ");
			mailBody.append("<table style='width:610px;  height:47px; background-image:url("+siteUrl+"images/mail/bg_title.gif); background-repeat:no-repeat;'  >");
			mailBody.append(" <tr>");
			mailBody.append(" <td  style='text-align:left;  padding:5px 0px 0px 50px; font-size:14px; font-weight:bold; color:#FFFFFF; ' >회원 정보수정에 따른 E-mail 인증을 위한 메일        </td></tr>");
			mailBody.append(" </table>");
			mailBody.append(" <tr>");
			mailBody.append("  <td height='42' style='border-right:#dfdfdf 1px solid;'>");
			mailBody.append("  <span style='font-size: 14px; text-align:right;	font-weight:bold; line-height:20px; color:#FF0000;padding-left:40px;'>OTP 인증</span>에 인증번호 발송을 위한 메일입니다.       </td>");
			mailBody.append(" </tr>");
			mailBody.append(" ");
			mailBody.append(" <tr>");
			mailBody.append(" <td height='103' style='border-right:#dfdfdf 1px solid;padding-left:40px;'> 안녕하세요.<br />");
			mailBody.append("    olleh 파트너 인증 시스템 입니다.<br />");
			mailBody.append("    로그인 요청하신 협력사 사이트에서는 회원님의 개인정보를 보호하기 위해<br />");
			mailBody.append("  로그인시 OTP 인증을 받도록 하고 있습니다.<br />");
			mailBody.append("  </tr>");
			mailBody.append("  <tr>");
			mailBody.append("  <td height='204' style='border-right:#dfdfdf 1px solid;padding-left:40px;'> ");
			mailBody.append("  <!--박스 시작 -->");
			mailBody.append("  <table width='600'  style=' background-color:#efefef;  border:#9cb9d6 1px solid;  z-index:100; font-size:11px ; '>");
			mailBody.append(" <tr>");
			mailBody.append("        <td height='40' style='padding:10px; '>&#8226; 본 메일은 귀하가  KT 파트너 SSO 사이트 (psso.show.co.kr)에서 회원가입시 등록하신 ");
			mailBody.append("             <br>");
			mailBody.append("              &nbsp;                <u>E-mail</u>로 발송된 메일입니다.</td>");
			mailBody.append("      </tr>");
			mailBody.append("       <tr>");
			mailBody.append("         <td height='40' style='padding:10px; '><b>※ 아래에 표시된 인증번호를 OTP 인증 페이지 <span style='font-size: 13px; 	font-weight:bold; line-height:20px; color:#FF3333; text-decoration:underline'> 인증번호 입력란</span>에 ");
			mailBody.append("              입력해 주십시오.</b></td>");
			mailBody.append("</tr>");
			mailBody.append("<tr>");
			mailBody.append("<td>");
			mailBody.append("			            <table align='center' style='background-image:url("+siteUrl+"images/mail/btn_bg.png); width:500px; font-size:16px; font-weight:bold; height:30px; margin:5px  0px;'>");
			mailBody.append(" <tr >");
			mailBody.append("  <td height='37' style='text-align:center; vertical-align:middle;' > "+otpVerfNo+"</td>");
			mailBody.append("</tr>");
			mailBody.append("  </table>              </td>");
			mailBody.append("   </tr>");
			mailBody.append("  </table>");
			mailBody.append("  <!--박스 끝-->");
			mailBody.append("  <tr><td style='border-right:#dfdfdf 1px solid;width:100%; height:65px; color:#a3a2a2; font-size:11px;l etter-spacing:-1px; background-color:#fafafa; ");
			mailBody.append("    border-top:#dfdfdf 1px solid; border-bottom:#dfdfdf 1px solid;  padding:15px 0px 10px 20px; line-height:15pt;'>");
			mailBody.append(" - 본 메일은 귀하가 가입하신 파트너 SSO 사이트(PSSO : psso.show.co.kr)에서 공지 및 안내를 위해 발송된 메일입니다. <br />");
			mailBody.append("     - 본 메일은 발신전용으로 회신되지 않습니다.<br />");
			mailBody.append("       - 메일 주소 변경을 원하시면 파트너 SSO사이트에서 로그인 후 '회원정보수정'에서 변경하시면 됩니다.");
			mailBody.append(" </td>");
			mailBody.append(" </tr>");
			mailBody.append(" <tr>");
			mailBody.append("<td style='border-right:#dfdfdf 1px solid; width:100%; height:80px;  color:#9e9e9e; line-height:15pt;  text-align:left; font-size:11px; padding:10px 0px 0px 20px; border-top:#dfdfdf 1px solid; '>");
			mailBody.append("        (주)케이티 대표이사 이석채 . 사업자등록번호 : 102-81-42945 . 통신판매업신고 : 2002-경기성남-0048 <br />");
			mailBody.append("           463-711 경기도 성남시 분당구 불정로 90 (정자동 206번지)  Tel : 국번없이 100  Copyright (c) 2012 kt corp. all rights reserved. </td>");
			mailBody.append("      </td>");
			mailBody.append("  </tr>");
			mailBody.append("</table>");
			mailBody.append("</body>");
	    	mailBody.append("</html>");
		 
		 email.setHtmlMsg(mailBody.toString());


		 email.send();
		} catch (Exception  e) {
	        e.printStackTrace();
	    }
		
	}
}