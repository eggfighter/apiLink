package com.kt.psso.onm.admin.member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kt.psso.db.dao.MemberDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.member.OnmUserDetails;
import com.kt.psso.onm.scheduler.hc.LineWriter;
import com.kt.psso.onm.util.CryptoUtil;

public class MemberService {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="memberDao"
	 * @uml.associationEnd  
	 */
	private MemberDao memberDao;
	
	/**
	 * @uml.property  name="cryptoUtil"
	 * @uml.associationEnd  
	 */
	private CryptoUtil cryptoUtil;
	
	/**
	 * @uml.property  name="onmUserWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter onmUserWriter;

	public List<MemberVo> list(SearchVo searchVo) {
		return memberDao.listMember(searchVo);
	}
	
	public int count(SearchVo searchVo) {
		return memberDao.countMember(searchVo);
	}
	
	public boolean add(MemberVo memberVo) {
		
		encryptPassword(memberVo);
		writeOnmUserLog(memberVo, "ADD");
		return memberDao.insertMember(memberVo);
	}
	
	public boolean insertSendHist(MemberVo memberVo) {
		
		//encryptPassword(memberVo);
		//writeOnmUserLog(memberVo, "ADD");
		if(memberVo.getTelNo() != null){
			String tesNo = memberVo.getTelNo();
			String mobile1;
			String mobile2;
			String mobile3;
			
			mobile1 = tesNo.substring(0, 3);
			memberVo.setMobile1(mobile1);
			
			if(tesNo.length()<11){
				mobile2 = tesNo.substring(3, 6);
				mobile3 = tesNo.substring(7, 10);
			}else {
				mobile2 = tesNo.substring(3, 7);
				mobile3 = tesNo.substring(7, 11);
			
				//memberVo.setMobile2(mobile2);
				//memberVo.setMobile3(mobile3);
			}
			memberVo.setMobile2(mobile2);
			memberVo.setMobile3(mobile3);
		}
		
			
		return memberDao.insertSendHist(memberVo);
	}
	
	void writeOnmUserLog(MemberVo memberVo, String action) {
		String userId = memberVo.getUserId();
		String userType = memberVo.getUserType();
		String time = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'").format(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		sb.append(time).append(" "+action)
		  .append(" "+userId).append(", ")
		  .append(userType).append("\n");
		try {
			onmUserWriter.write(sb.toString());
		} catch (IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.warn(sb.toString());
			}
		}		
	}

	private void encryptPassword(MemberVo memberVo) {
		// userPswd
		String userPswd = memberVo.getUserPswd();
		if (userPswd != null && !userPswd.equals("")) {
			memberVo.setUserPswd(cryptoUtil.hash(userPswd));
		}
	}

	public boolean mod(MemberVo memberVo) {
		//encryptPassword(memberVo);
		//String userPswd = memberVo.getUserPswd();
		/*if (userPswd != null && !"".equals(userPswd)) {
			checkPassword(memberVo);
		}*/
		String userId = memberVo.getUserId();
		String useFlag = memberVo.getUseFlag();
		if("N".equals(useFlag)){
			memberDao.resetTrialCnt(userId);
		}
		// onmUser 로그
		String oldUserType = get(memberVo.getUserId()).getUserType();
		if (!oldUserType.equals(memberVo.getUserType())) {
			writeOnmUserLog(memberVo, "MOD");
		}
		return memberDao.updateMember(memberVo);
	}
	
	public boolean pwMod(MemberVo memberVo) {
		encryptPassword(memberVo);
		String userPswd = memberVo.getUserPswd();
		if (userPswd != null && !"".equals(userPswd)) {
			checkPassword(memberVo);
		}
		memberVo.setUseFlag("1");
		return memberDao.updatePswd(memberVo);
	}
	/**
	 * 패스워드 변경일을 업데이트 하기 위해 패스워드 변경 여부 확인
	 */
	private void checkPassword(MemberVo memberVo) {
		String oldPswd = get(memberVo.getUserId()).getUserPswd();
		if (!oldPswd.equals(memberVo.getUserPswd())) {
			memberVo.setPswdUpdtDate("changed");
		}
	}

	public boolean del(MemberVo memberVo) {
		writeOnmUserLog(memberVo, "DEL");
		return memberDao.deleteMember(memberVo);
	}

	public MemberVo get(String userId) {
		return memberDao.getMember(userId);
	}
	
	public Map<String, String> getRoles() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ROLE_USERREAD", "사용자(읽기)");
		map.put("ROLE_USERWRITE", "사용자(변경)");
		map.put("ROLE_USERALL", "사용자(고급변경)");
		map.put("ROLE_SUPERVISOR", "관리자");
		return map;
	}
	
	/// OTP 번호 생성 관련
	public MemberVo getOtpSeq (String userId) {
		 
		return memberDao.getOtpSeq(userId);
	}
	
	public MemberVo getOtpVerfNo (String otpSeq) {
		 
		return memberDao.getOtpVerfNo(otpSeq);
	}


	/**
	 * 패스워드 오류가 발생했을 때 시도횟수(LogTrialCnt)를 증가시킨다.
	 * 시도횟수(LogTrialCnt)가 이미 5회 이상인 경우 계정을 잠금 처리한다.
	 */

	public UserDetails handleIncorrectPassword(UserDetails userDetails) {
		String userId = userDetails.getUsername();
		MemberVo memberVo = get(userId);
		
		memberVo.setChgFlag("U");
		
		if (memberVo.getLogTrialCnt() < 5) {
			memberDao.increaseTrialCnt(userId);
		} else if (userDetails.isAccountNonLocked()) {
			memberVo.setUseFlag("0");
			memberDao.updateUseFlagFalse(userId);
		}
		//trigger기능 추가
		memberDao.insertUserChngHist(memberVo);
		
		return new OnmUserDetails(memberVo);
	}

	/**
	 * 패스워드 오류가 발생하지 않았을 때 시도횟수(LogTrialCnt)가 0보다 크면 이를 초기화한다.
	 */

	public void handleCorrectPassword(UserDetails userDetails) {
		String userId = userDetails.getUsername();
		MemberVo memberVo = get(userId);
		memberVo.setChgFlag("U");
		if (memberVo.getLogTrialCnt() > 0) {
			memberDao.resetTrialCnt(userId);
			//trigger기능 추가
			memberDao.insertUserChngHist(memberVo);
		}
	}

	public boolean isOverThreeMonth(UsernamePasswordAuthenticationToken authentication) {

		String userId = (String) authentication.getPrincipal();	
		MemberVo memberVo = get(userId);
			
		String pswdUpdtDate = memberVo.getPswdUpdtDate();
		if (pswdUpdtDate == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -3);
		String theDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
		if (pswdUpdtDate.compareTo(theDay) < 0) {
			memberVo.setUseFlag("0");
			memberDao.updateUseFlagFalse(userId);
			
			
			
			return true;
		} else {
			return false;
		}
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param memberDao
	 * @uml.property  name="memberDao"
	 */
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	/**
	 * @param cryptoUtil
	 * @uml.property  name="cryptoUtil"
	 */
	@Autowired
	public void setCryptoUtil(CryptoUtil cryptoUtil) {
		this.cryptoUtil = cryptoUtil;
	}

	/**
	 * @param onmUserWriter
	 * @uml.property  name="onmUserWriter"
	 */
	public void setOnmUserWriter(LineWriter onmUserWriter) {
		this.onmUserWriter = onmUserWriter;
	}

}
