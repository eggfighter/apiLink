package com.kt.psso.onm.member;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kt.psso.onm.admin.member.MemberService;
import com.kt.psso.onm.admin.member.MemberVo;

public class OnmUserDetailsService implements UserDetailsService {
	private Log LOG = LogFactory.getLog(getClass());
	/**
	 * @uml.property  name="memberService"
	 * @uml.associationEnd  
	 */
	private MemberService memberService;
	
	/**
	 * @param memberService
	 * @uml.property  name="memberService"
	 */
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * @return
	 * @uml.property  name="memberService"
	 */
	public MemberService getMemberService() {
		return memberService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		LOG.debug("1@@@@@@@@@@@@@@@@@@"+username);
		MemberVo memberVo = this.memberService.get(username);
		
		//if(memberVo != null) LOG.debug("2@@@@@@@@@@@@@@@@@@"+memberVo.getUserName());
		if (memberVo == null) throw new UsernameNotFoundException(username);
		
		return new OnmUserDetails(memberVo);
	}

}
