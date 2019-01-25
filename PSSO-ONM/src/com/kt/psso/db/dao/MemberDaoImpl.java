package com.kt.psso.db.dao;

import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.SearchVo;


public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {
	
		 	
	@Override
	public List<MemberVo> listMember(SearchVo searchVo) {
		RowBounds rowBounds = new RowBounds(searchVo.getOffset(), searchVo.getLimit());
		return getSqlSession().selectList("member.listMember", searchVo, rowBounds);
	}
	
	@Override
	public int countMember(SearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("member.countMember", searchVo);
	}
	
	@Override
	public MemberVo getMember(String userId) {
		return (MemberVo)getSqlSession().selectOne("member.getMember", userId);
	}
	@Override
	public MemberVo getOtpSeq(String userId) {
		return (MemberVo) getSqlSession().selectOne("member.getOtpSeq", userId);
	}
	@Override
	public MemberVo getOtpVerfNo(String otpSeq) {
		return (MemberVo) getSqlSession().selectOne("member.getOtpVerfNo", otpSeq);
	}

	@Override
	public boolean insertMember(MemberVo memberVo) {
		int result = getSqlSession().insert("member.insertMember", memberVo);
		return (result == 1);
	}
	
	@Override
	public boolean insertSendHist(MemberVo memberVo) {
		int result = getSqlSession().insert("member.insertSendHist", memberVo);
		return (result == 1);
	}

	@Override
	public boolean updateMember(MemberVo memberVo) {
		int result = getSqlSession().update("member.updateMember", memberVo);
		return (result == 1);
	}
	
	@Override
	public boolean updatePswd(MemberVo memberVo) {
		int result = getSqlSession().update("member.updatePswd", memberVo);
		return (result == 1);
	}

	@Override
	public boolean deleteMember(MemberVo memberVo) {
		int result = getSqlSession().delete("member.deleteMember", memberVo);
		return (result == 1);
	}

	@Override
	public boolean increaseTrialCnt(String userId) {
		int result = getSqlSession().update("member.increaseTrialCnt", userId);
		return (result == 1);
	}

	@Override
	public boolean resetTrialCnt(String userId) {
		int result = getSqlSession().update("member.resetTrialCnt", userId);
		return (result == 1);
	}

	@Override
	public boolean updateUseFlagFalse(String userId) {
		int result = getSqlSession().update("member.updateUseFlagFalse", userId);
		return (result == 1);
	}
	
	@Override
	public boolean insertUserChngHist(MemberVo memberVo) {
		int result = getSqlSession().insert("member.insertUserChngHist",memberVo);
		return (result == 1);
	}
}