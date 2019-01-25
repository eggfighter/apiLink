package com.kt.psso.onm.member;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.kt.psso.onm.scheduler.hc.LineWriter;

public class OnmLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
		implements LogoutSuccessHandler {

	/**
	 * @uml.property  name="onmConnectionWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter onmConnectionWriter;

	public OnmLogoutSuccessHandler() {
        super.setTargetUrlParameter(null);
        super.setDefaultTargetUrl("/login.do");
	}

	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
		
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		String sessionId = details.getSessionId();
		String address = details.getRemoteAddress();
		String username = authentication.getName();
		StringBuilder sb = new StringBuilder();
		String time = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'").format(System.currentTimeMillis());
		sb.append(time).append(" LOGOUT ")
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

        super.handle(request, response, authentication);
    }

	/**
	 * @param onmConnectionWriter
	 * @uml.property  name="onmConnectionWriter"
	 */
	public void setOnmConnectionWriter(LineWriter onmConnectionWriter) {
		this.onmConnectionWriter = onmConnectionWriter;
	}

}
