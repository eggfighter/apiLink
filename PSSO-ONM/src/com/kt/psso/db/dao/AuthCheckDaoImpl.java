package com.kt.psso.db.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.authcheck.AuthCheckSearchVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.authcheck.AuthCheckVo;

@Repository
public class AuthCheckDaoImpl extends SqlSessionDaoSupport implements AuthCheckDao {

	@Override
	public List<AuthCheckSearchVo> listAuthCheck(SearchVo searchVo) {
		return getSqlSession().selectList("authcheck.listAuthCheck", searchVo);
	}
	
	@Override
	public boolean insertAuthCheck(AuthCheckVo authCheckVo) {
		int result = getSqlSession().insert("authcheck.insertAuthCheck", authCheckVo);
		return (result == 1);
	}

}