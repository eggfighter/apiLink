package com.kt.psso.onm.common;

import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



//@Controller
//@RequestMapping("/interceptor")
public class SecurityInterceptor extends HandlerInterceptorAdapter {

   
    private static final Log logger = LogFactory.getLog(SecurityInterceptor.class);
    /*
    String[] pattern= {
    		"<script>(.*?)</script>",
    		"src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
    		"src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
    		"</script>",
    		"<script(.*?)>",
    		"eval\\((.*?)\\)",
    		"expression\\((.*?)\\)",
    		"javascript:",
    		"vbscript:",
    		"onload(.*?)="
    		};
	*/
	
	//@RequestMapping(value={"/preHandle.do"}, params={"method=preHandle"})
    public boolean preHandle(	HttpServletRequest request,
            					HttpServletResponse response, 
            					Object controller)
            						throws Exception {
    	logger.debug("======================= preHandle begin =======================");
             	
        	StringBuffer protoc = request.getRequestURL();
        	
        	//System.out.println(protoc);
        	 
        	// URL을 받아온다
        	 

        	String proto =  protoc.toString();
        	 
       	 
        	String[] protoArr = proto.split(":");
        	 
        	if("http".equals(protoArr[0])){
        	System.out.println("========="+protoArr[0]); 
        	//0 번째 배열 즉 http 나 https 이게 https 가 아니면 
        	response.sendRedirect("/httpchk.jsp");  
        	 
        	//https로 보내버린다. 
        	}
        	/*
        	if ( request instanceof MultipartHttpServletRequest) {
        		Enumeration<String> names=request.getParameterNames();
        		while ( names.hasMoreElements()) {
        			String a=names.nextElement();
        			String value=request.getParameter(a);
        			System.out.println("parameter: "+a);
        			if ( ! isValidInput(value)) {
        				response.sendRedirect("xss_detect.do");
        				return false;
        			}
        		}
        	}*/
    
        logger.debug("======================= preHandle end =======================");
        
        return true;

    }
    /*
    private boolean isValidInput(String value) {   
    	if (value != null) {      
    		value = value.replaceAll("", "");
    		for ( String ptn : pattern ) {
    			Pattern scriptPattern = Pattern.compile(ptn,
    			Pattern.CASE_INSENSITIVE);
    			if ( scriptPattern.matcher(value).find() ) return false;
    		}
    		return true;
    	} else {
    		return false;
    	}      
    }       
	*/
	public void afterCompletion(HttpServletRequest request,
					            HttpServletResponse response, 
					            Object controller, 
					            Exception e)
					            	throws Exception {
		
    	logger.debug("======================= afterCompletion begin =======================");
    	
    	
    	logger.debug("======================= afterCompletion end =======================");
    }
    
}