package com.kt.psso.onm.member;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import com.kt.psso.onm.admin.member.MemberService;
import com.kt.psso.onm.scheduler.hc.LineWriter;
import com.kt.psso.onm.util.CryptoUtil;

public class OnmAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    /**
	 * @uml.property  name="userDetailsService"
	 * @uml.associationEnd  
	 */
    private UserDetailsService userDetailsService;
    
    /**
	 * @uml.property  name="cryptoUtil"
	 * @uml.associationEnd  
	 */
    private CryptoUtil cryptoUtil;

	/**
	 * @uml.property  name="memberService"
	 * @uml.associationEnd  
	 */
	private MemberService memberService;


	/**
	 * @uml.property  name="onmConnectionWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter onmConnectionWriter;
	
	//private MemberDao memberDao;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		/*
		if (memberService.idChk(authentication)) {	
            throw new AuthenticationServiceException("사용자계정이 잠겨있습니다.");
		}
		*/
		if (details == null) {
			logger.debug("Authentication failed: no details provided");

            throw new AuthenticationServiceException("아이디 또는 비밀번호가 잘못 입력되었습니다.");
		}
		
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new AuthenticationServiceException("아이디 또는 비밀번호가 잘못 입력되었습니다.");
        }

        String presentedPassword = authentication.getCredentials().toString();
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++presentedPassword: "+presentedPassword);
        String encoded = cryptoUtil.hash(presentedPassword);
        logger.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++encoded: "+encoded);
        if (!userDetails.getPassword().equals(encoded)) {
            logger.debug("Authentication failed: password does not match stored value");

            // 패스워드 오류 5회시 잠금 기능
            userDetails = memberService.handleIncorrectPassword(userDetails);
            if (userDetails.isAccountNonLocked()) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            } else {
            	throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",
                        "User account is locked"));
            }
            

        } else {
        	memberService.handleCorrectPassword(userDetails);
        }

       /* 
		// 패스워드 변경한지 3개월이 지나면
		// 시스템관리자(ROLE_SUPERVISOR)가 아닌 유저의 경우 계정을 잠금
		if (memberService.isOverThreeMonth(authentication)) {
            throw new AuthenticationServiceException("비밀번호 변경 주기를 경과하였습니다. 비밀번호 변경은 관리자에게 문의하시기 바랍니다");
			
			
		}*/

        // IP 확인
		String address = details.getRemoteAddress();
		
		OnmUserDetails onmUserDetails = (OnmUserDetails) userDetails;
		String userHost = onmUserDetails.getUserHost();
		
		if (userHost == null || userHost.equals("")) {
			logger.debug("Authentication failed: host is not allowed");

			throw new AuthenticationServiceException("접근할 수 없는 IP입니다.");
		}
		
		boolean allowed = false;
		
		if ("*".equals(userHost)) {
			allowed = true;
		} else {
			for (String host : userHost.trim().split(",")) {
				if (address.equals(host)) {
					allowed = true;
				}
			}
		}
		
		if (!allowed) {
			logger.debug("Authentication failed: host is not allowed");

			throw new AuthenticationServiceException("접근할 수 없는 IP입니다.");
		}

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
        UserDetails loadedUser;

        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        }
        catch (DataAccessException repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
	}

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {

		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		String sessionId = details.getSessionId();
		String address = details.getRemoteAddress();
		String username = authentication.getName();
		StringBuilder sb = new StringBuilder();
		String time = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'").format(System.currentTimeMillis());
		sb.append(time).append(" LOGIN  ")
		  .append(address).append(", ")
		  .append(sessionId).append(", ")
		  .append(username).append("\n");
		try {
			onmConnectionWriter.write(sb.toString());
		} catch (IOException e) {
			if (logger.isWarnEnabled()) {
				logger.warn(e.getMessage(), e);
			}
		}
		
		return super.createSuccessAuthentication(principal, authentication, user);
	}

	/**
	 * @return
	 * @uml.property  name="userDetailsService"
	 */
	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	/**
	 * @param userDetailsService
	 * @uml.property  name="userDetailsService"
	 */
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * @param cryptoUtil
	 * @uml.property  name="cryptoUtil"
	 */
	public void setCryptoUtil(CryptoUtil cryptoUtil) {
		this.cryptoUtil = cryptoUtil;
	}

	/**
	 * @param memberService
	 * @uml.property  name="memberService"
	 */
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * @param onmConnectionWriter
	 * @uml.property  name="onmConnectionWriter"
	 */
	public void setOnmConnectionWriter(LineWriter onmConnectionWriter) {
		this.onmConnectionWriter = onmConnectionWriter;
	}
}
