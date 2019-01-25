package com.kt.psso.db.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.chknum.ChkNumVo;
import com.kt.psso.onm.common.SearchVo;

@Repository
public class ChkNumDaoImpl extends SqlSessionDaoSupport implements ChkNumDao {

	@Override
	public List<ChkNumVo> listChkNum(SearchVo searchVo) {
		return getSqlSession().selectList("chkNum.listChkNum", searchVo);
	}

}