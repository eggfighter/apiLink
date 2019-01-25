package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.CustomerVo;


public interface MemberDao {

	List<MemberVo> listMember(SearchVo searchVo);
	
	int countMember(SearchVo searchVo); 
	
	MemberVo getMember(String userId);
	
	MemberVo getOtpSeq(String userId);
	
	MemberVo getOtpVerfNo(String otpSeq);
	
	boolean insertMember(MemberVo memberVo);
	
	boolean updateMember(MemberVo memberVo);
	
	boolean updatePswd(MemberVo memberVo);
	
	boolean deleteMember(MemberVo memberVo);

	boolean increaseTrialCnt(String userId);

	boolean resetTrialCnt(String userId);

	boolean updateUseFlagFalse(String userId);

	boolean insertSendHist(MemberVo memberVo);
	
	boolean insertUserChngHist(MemberVo memberVo);
	
}
