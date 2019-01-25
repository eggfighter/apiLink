package com.kt.psso.onm.restore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kt.psso.onm.common.SearchVo;


@Controller
public class RestoreController {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="restoreService"
	 * @uml.associationEnd  
	 */
	private RestoreService restoreService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RestoreValidator());
    }
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException ex) {
		FieldError error = ex.getBindingResult().getFieldError();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		ModelAndView mav = new ModelAndView("error.bind");
		mav.addObject("error", error);
		mav.addObject("allErrors", allErrors);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/restore/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		String cn = searchVo.getpCn();
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		List<RestoreVo> listRestore = null;
		if ((cn != null && !cn.equals(""))) {
			listRestore = restoreService.list(searchVo);
		} else {
			listRestore = new ArrayList<RestoreVo>();
		}
	
		ModelAndView mav = new ModelAndView("restore.list");
		mav.addObject("listRestore", listRestore);
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET, value="/restore/restore.do")
	public ModelAndView insertIdRestore(HttpServletRequest req, HttpServletResponse res, HttpSession session, SearchVo searchVo) throws Exception {
		String cn = searchVo.getpCn();
		
		//boolean result = restoreService.add(searchVo);
		if (LOG.isInfoEnabled()) {
			LOG.info("cn = " + cn);
		}
		 Connection connection = null;        
		 Statement statement = null;        
		 String query = null;        
		 ResultSet resultSet = null;   
		 
		 String userId = null;
		 String regDate = null;
		 String regIp = null;
		 String lastLogin = null;
		 int result = 0 ;
		 try{
			 
			 Context initCtx = new InitialContext();
			 Context envCtx = (Context)initCtx.lookup("java:comp/env");
			 DataSource ds = (DataSource)envCtx.lookup("jdbc/pssodb");
			 connection = ds.getConnection();
			 
			 
			 statement = connection.createStatement();                                      
			 // Query문 실행            
			 query = "insert into PORTALUSER  select * from PORTALUSER_BACKUP where UPPER(CN) = UPPER('"+cn+"')";
			 result = statement.executeUpdate(query);
			 query = "select CN " +
			 		", TO_CHAR(REGDATE, 'YYYY-MM-DD HH24:MI:SS') as REGDATE	" +
			 		", REGIP, TO_CHAR(REGDATE" +
			 		", 'YYYY-MM-DD HH24:MI:SS') as LASTLOGIN from PORTALUSER where UPPER(CN) = UPPER('"+cn+"')";
			 resultSet = statement.executeQuery(query);
			 //RestoreVo restoreVo = (RestoreVo) resultSet;
			 while (resultSet.next()) {
				 userId = resultSet.getString("cn");
				 regDate = resultSet.getString("regdate");
				 regIp = resultSet.getString("regip");
				 lastLogin = resultSet.getString("lastlogin");
			 }
			 
			 restoreService.writeUserRestoreLog(userId, regDate, regIp, lastLogin);
			 
		 } catch (SQLException e) {
			 
			 if (LOG.isInfoEnabled()) {
				 LOG.info(" error message = " + e);
			 }
			 connection.close(); 
			 result = 2;
			 
		 } finally {            
			 if (resultSet != null) {                
				 try {                    
					 resultSet.close();
				 } catch (SQLException e) {                    
					 if (LOG.isInfoEnabled()) {
						 LOG.info(" error message = " + e);
					 }
					 connection.close();
					 result = 2;
				 } if (statement != null) {                 
					 try {                    
						 statement.close();                
					 } catch (SQLException e) {
						 if (LOG.isInfoEnabled()) {
							 LOG.info(" error message = " + e);
						 }
						 connection.close();
						 result = 2;
					 }
				 }  if (connection != null) {                
					 try {                    
						 connection.close();                
					 } catch (SQLException e) {                    
						 if (LOG.isInfoEnabled()) {
							 LOG.info(" error message = " + e);
						 }
						 connection.close(); 
						 result = 2;
					 }
				 }
			 }
		 }
		 connection.close(); 
		 ModelAndView mav = new ModelAndView("restore.list");
		 mav.addObject("result", result);
		 return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param restoreService
	 * @uml.property  name="restoreService"
	 */
	@Autowired
	public void setRestoreService(RestoreService restoreService) {
		this.restoreService = restoreService;
	}	
}
