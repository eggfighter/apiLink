package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.scheduler.authcheck.AuthCheckVo;
import com.kt.psso.onm.authcheck.AuthCheckSearchVo;
import com.kt.psso.onm.common.SearchVo;

public class TestAuthCheckDaoImpl implements AuthCheckDao {

	@Override
	public List<AuthCheckSearchVo> listAuthCheck(SearchVo searchVo) {
		return null;
	}
	
	@Override
	public boolean insertAuthCheck(AuthCheckVo authCheckVo) {
		System.out.println(authCheckVo);
		return false;
	}

}
