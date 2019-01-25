package com.kt.psso.onm.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.kt.psso.onm.admin.member.MemberVo;

public class OnmUserDetails implements UserDetails {

	private static final long serialVersionUID = -4282915311645276141L;
	
	/**
	 * @uml.property  name="memberVo"
	 * @uml.associationEnd  
	 */
	private MemberVo memberVo;
	
	public OnmUserDetails(MemberVo memberVo) {
		this.memberVo = memberVo;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		String userType = memberVo.getUserType();
		if (userType != null && !userType.equals("")) {
			ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String role : userType.split(",")) {
				authorities.add(new GrantedAuthorityImpl(role));
			}
			return authorities;
		}
		return null;
	}

	@Override
	public String getPassword() {
		return memberVo.getUserPswd();
	}

	@Override
	public String getUsername() {
		return memberVo.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return "1".equals(memberVo.getUseFlag());
	}

	@Override
	public boolean isAccountNonLocked() {
		return "1".equals(memberVo.getUseFlag());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return "1".equals(memberVo.getUseFlag());
	}

	@Override
	public boolean isEnabled() {
		return "1".equals(memberVo.getUseFlag());
	}

	public String getDisplayName() {
		return memberVo.getUserName();
	}
	
	public String getUserHost() {
		return memberVo.getUserHost();
	}
	
	public String getLastLogin() {
		return memberVo.getLastLogin();
	}

	@Override
	public int hashCode() {
		return getUsername().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return getUsername().equals(((UserDetails)obj).getUsername());
	}

}
