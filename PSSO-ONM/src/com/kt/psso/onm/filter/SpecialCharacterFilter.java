package com.kt.psso.onm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SpecialCharacterFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		request = new SpecialCharacterRequestWrapper(request);
		filterChain.doFilter(request, response);
	}
	
	private static class SpecialCharacterRequestWrapper extends HttpServletRequestWrapper {
		
		private static String[][] patterns;
		
		static {
			patterns = new String[][] {
					{"&", "&+38"}
				  , {"#", "&#35"}
				  , {"<", "&lt;"}
				  , {">", "&gt;"}
				  , {"'", "&lsquo;"}
				  , {"\"", "&quot;"}
				  , {"(", "&#40"}
				  , {")", "&#41"}
				  , {"javascript", "javasc#ipt"}
				  , {"&+38", "&#38"}
			};
		}

		public SpecialCharacterRequestWrapper(HttpServletRequest request) {
			super(request);
		}
		
		@Override
		public String getParameter(String name) {
			String parameter = super.getParameter(name);
			parameter = checkPattern(parameter);
			return parameter;
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] parameterValues = super.getParameterValues(name);
			if (parameterValues != null) {
				for (int i = 0; i < parameterValues.length; i++) {
					parameterValues[i] = checkPattern(parameterValues[i]);
				}
			}
			return parameterValues;
		}

		private String checkPattern(String parameter) {
			if (parameter == null) return null;
			
			for (int i = 0; i < patterns.length; i++) {
				String[] p = patterns[i];
				if (parameter.contains(p[0])) {
					if (p[0].equals("(") || p[0].equals(")")) {
						parameter = parameter.replaceAll("\\" + p[0], p[1]);
					} else {
						parameter = parameter.replaceAll(p[0], p[1]);
					}
				}
			}
			return parameter;
		}

	}

}
