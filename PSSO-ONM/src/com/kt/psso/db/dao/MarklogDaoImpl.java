package com.kt.psso.db.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MarklogDaoImpl extends SqlSessionDaoSupport implements MarklogDao {

	@Override
	public boolean deleteMarklogBefore(String day) {
		int result = getSqlSession().delete("marklog.deleteMarklogBefore", day);
		return (result > 0);
	}

}
