package com.kt.psso.db.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.restore.RestoreVo;

@Repository
public class RestoreDaoImpl extends SqlSessionDaoSupport implements RestoreDao {

	@Override
	public List<RestoreVo> listRestore(SearchVo searchVo) {
		RowBounds rowBounds = new RowBounds(searchVo.getOffset(), searchVo.getLimit());
		return getSqlSession().selectList("restore.listRestore", searchVo, rowBounds);
	}
}
