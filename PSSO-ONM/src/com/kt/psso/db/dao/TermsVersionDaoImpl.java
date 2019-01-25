package com.kt.psso.db.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.terms.TermsVersionVo;

@Repository
public class TermsVersionDaoImpl extends SqlSessionDaoSupport implements TermsVersionDao {

	public List<TermsVersionVo> listTermsVersion(SearchVo searchVo) {
		RowBounds rowBounds = new RowBounds(searchVo.getOffset(), searchVo.getLimit());
		return getSqlSession().selectList("termsVersion.listTermsVersion", searchVo, rowBounds);
	}

	public int countTermsVersion(SearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("termsVersion.countTermsVersion", searchVo);
	}

	public boolean insertTermsVersion(TermsVersionVo termsVersionVo) {
		int result = getSqlSession().insert("termsVersion.insertTermsVersion", termsVersionVo);
		return (result == 1);
	}

	public boolean deleteTermsVersion(TermsVersionVo termsVersionVo) {
		int result = getSqlSession().delete("termsVersion.deleteTermsVersion", termsVersionVo);
		return (result == 1);
	}

}
