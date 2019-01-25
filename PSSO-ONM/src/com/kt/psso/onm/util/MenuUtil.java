package com.kt.psso.onm.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;

import com.kt.psso.onm.admin.config.MenuConfig;

public class MenuUtil {
	/**
	 * @uml.property  name="menuConfig"
	 * @uml.associationEnd  
	 */
	private MenuConfig menuConfig;
	
	/**
	 * @uml.property  name="servletPath"
	 */
	private String servletPath;
	/**
	 * @uml.property  name="pathKey"
	 */
	private String pathKey;

	public void setRequest(HttpServletRequest req) {
		this.servletPath = (String) req.getAttribute("javax.servlet.forward.servlet_path");
		this.pathKey = servletPath.substring(1, servletPath.lastIndexOf("/")).replaceAll("/", "-");
	}
	
	public void setServletContext(ServletContext ctx) {
		WebApplicationContext appCtx = (WebApplicationContext) ctx
				.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		this.menuConfig = (MenuConfig) appCtx.getBean("menuConfig");
	}
	
	/**
	 * @return
	 * @uml.property  name="pathKey"
	 */
	public String getPathKey() {
		return pathKey;
	}
	
	public String getMenu1() {
		return menuConfig.getMenu1(pathKey);
	}
	
	public String getClass(String path) {
		if (pathKey.equals(path))
			return "btn_lefton";
		return "btn_leftoff";
	}
	
	public String getOnOff(String menu1) {
		if (menuConfig.getMenu1(pathKey).equals(menu1)) return "on";
		else return "off";
	}
	
	public String getNavi() {
		return menuConfig.getNavi(pathKey);
	}

}
