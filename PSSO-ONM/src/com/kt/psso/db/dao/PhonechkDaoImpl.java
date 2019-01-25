package com.kt.psso.db.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class PhonechkDaoImpl extends SqlSessionDaoSupport implements PhonechkDao {

	@Override
	public boolean deletePhonechkBefore(String day) {
		int result = getSqlSession().delete("phonechk.deletePhonechkBefore", day);
		return (result > 0);
	}

}
