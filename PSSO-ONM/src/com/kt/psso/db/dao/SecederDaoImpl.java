package com.kt.psso.db.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.seceder.SecederVo;

@Repository
public class SecederDaoImpl extends SqlSessionDaoSupport implements SecederDao {

	@Override
	public List<SecederVo> listSeceder(SearchVo searchVo) {
		return getSqlSession().selectList("seceder.listSeceder", searchVo);
	}
	
	@Override
	public int countSeceder() {
		return (Integer) getSqlSession().selectOne("seceder.countSeceder");
	}


}
