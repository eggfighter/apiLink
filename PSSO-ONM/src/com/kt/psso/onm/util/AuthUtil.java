package com.kt.psso.onm.util;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import com.kt.psso.onm.member.OnmUserDetails;

public class AuthUtil {
	public String getUserId(HttpSession session) {
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (context == null) return null;
		
		Authentication authentication = context.getAuthentication();
		if (authentication == null) return null;
		
		return authentication.getName();
	}
	
	public String getUserName(HttpSession session) {
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (context == null) return null;
		
		Authentication authentication = context.getAuthentication();
		if (authentication == null) return null;
		
		OnmUserDetails details = (OnmUserDetails) authentication.getPrincipal();
		return details.getDisplayName();
	}
	
	public String getLastLogin(HttpSession session) {
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (context == null) return null;
		
		Authentication authentication = context.getAuthentication();
		if (authentication == null) return null;
		
		OnmUserDetails details = (OnmUserDetails) authentication.getPrincipal();
		return details.getLastLogin();
	}
	
	public boolean hasAuthority(HttpSession session, String authority) {
		SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		if (context == null) return false;
		
		Authentication authentication = context.getAuthentication();
		if (authentication == null) return false;
		
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority auth : authorities) {
			if (auth.getAuthority().equals(authority))
			return true;
		}
		return false;
	}
}
