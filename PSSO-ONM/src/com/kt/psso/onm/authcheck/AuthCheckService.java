package com.kt.psso.onm.authcheck;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.AuthCheckDao;
import com.kt.psso.onm.common.SearchVo;

@Service
public class AuthCheckService {

	/**
	 * @uml.property  name="authCheckDao"
	 * @uml.associationEnd  
	 */
	private AuthCheckDao authCheckDao;

	public List<AuthCheckSearchVo> list(SearchVo searchVo) {
		return authCheckDao.listAuthCheck(searchVo);
		
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param authCheckDao
	 * @uml.property  name="authCheckDao"
	 */
	@Autowired
	public void setAuthCheckDao(AuthCheckDao authCheckDao) {
		this.authCheckDao = authCheckDao;
	}
	
}
