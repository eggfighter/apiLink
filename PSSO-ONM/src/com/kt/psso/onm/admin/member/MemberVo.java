package com.kt.psso.onm.admin.member;

import java.io.Serializable;

public class MemberVo implements Serializable {
	private static final long serialVersionUID = -3217741308600939229L;
	
	/**
	 * @uml.property  name="userId"
	 */
	private String userId;
	/**
	 * @uml.property  name="userName"
	 */
	private String userName;
	/**
	 * @uml.property  name="userPswd"
	 */
	private String userPswd;
	/**
	 * @uml.property  name="userPswd2"
	 */
	private String userPswd2;
	/**
	 * @uml.property  name="telNo"
	 */
	private String telNo;
	/**
	 * @uml.property  name="email"
	 */
	private String email;
	/**
	 * @uml.property  name="otherm"
	 */
	private String otherm;
	/**
	 * @uml.property  name="address"
	 */
	private String address;
	/**
	 * @uml.property  name="userType"
	 */
	private String userType;
	/**
	 * @uml.property  name="useFlag"
	 */
	private String useFlag;
	/**
	 * @uml.property  name="userDesc"
	 */
	private String userDesc;
	/**
	 * @uml.property  name="regDate"
	 */
	private String regDate;
	/**
	 * @uml.property  name="updtDate"
	 */
	private String updtDate;
	/**
	 * @uml.property  name="userHost"
	 */
	private String userHost;
	/**
	 * @uml.property  name="pswdUpdtDate"
	 */
	private String pswdUpdtDate;
	/**
	 * @uml.property  name="logTrialCnt"
	 */
	private int logTrialCnt;
	/**
	 * @uml.property  name="logIndc"
	 */
	private String logIndc;
	/**
	 * @uml.property  name="userMacAddress"
	 */
	private String userMacAddress;


	/**
	 * @uml.property  name="authCode"
	 */
	private String authCode;
	/**
	 * @uml.property  name="connIp"
	 */
	private String connIp;
	/**
	 * @uml.property  name="delYn"
	 */
	private String delYn;
	/**
	 * @uml.property  name="lastLogin"
	 */
	private String lastLogin;
	
	/// otp 관련 변수
	/**
	 * @uml.property  name="otpSeq"
	 */
	private String otpSeq;
	/**
	 * @uml.property  name="otpVerfNo"
	 */
	private String otpVerfNo;
	/**
	 * @uml.property  name="inOtpVerfNo"
	 */
	private String inOtpVerfNo;
	
	//sendhist 테이블 insert 하기 위해서 추가
	/**
	 * @uml.property  name="mobile1"
	 */
	private String mobile1;
	/**
	 * @uml.property  name="mobile2"
	 */
	private String mobile2;
	/**
	 * @uml.property  name="mobile3"
	 */
	private String mobile3;
	/**
	 * @uml.property  name="sendDiv"
	 */
	private String sendDiv;
	/**
	 * @uml.property  name="contractnum"
	 */
	private String contractnum;
	
	/*
	 * 2014-11-17트리거 관련 플래그 추가
	 * chnghist 테이블 insert 하기 위해서 추가
	 */
	/**
	 * @uml.property  name="chgFlag"
	 */
	private String chgFlag;
	
	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userName=" + userName
				+ ", telNo=" + telNo + ", email=" + email + ", address="
				+ address + ", userType=" + userType + ", useFlag=" + useFlag
				+ ", userDesc=" + userDesc + ", regDate=" + regDate
				+ ", updtDate=" + updtDate + ", userHost=" + userHost
				+ ", pswdUpdtDate=" + pswdUpdtDate + ", logTrialCnt="
				+ logTrialCnt + ", logIndc=" + logIndc + ", userMacAddress="
				+ userMacAddress + "]";
	}
	
	//-------------------- Getter & Setter --------------------//
	/**
	 * @return
	 * @uml.property  name="chgFlag"
	 */
	public String getChgFlag() {
		return chgFlag;
	}
	/**
	 * @return
	 * @uml.property  name="chgFlag"
	 */
	public void setChgFlag(String chgFlag) {
		this.chgFlag = chgFlag;
	}

	/**
	 * @return
	 * @uml.property  name="userId"
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 * @uml.property  name="userId"
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return
	 * @uml.property  name="userName"
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 * @uml.property  name="userName"
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return
	 * @uml.property  name="userPswd"
	 */
	public String getUserPswd() {
		return userPswd;
	}
	/**
	 * @param userPswd
	 * @uml.property  name="userPswd"
	 */
	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}
	/**
	 * @return
	 * @uml.property  name="userPswd2"
	 */
	public String getUserPswd2() {
		return userPswd2;
	}
	/**
	 * @param userPswd2
	 * @uml.property  name="userPswd2"
	 */
	public void setUserPswd2(String userPswd2) {
		this.userPswd2 = userPswd2;
	}
	/**
	 * @return
	 * @uml.property  name="telNo"
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * @param telNo
	 * @uml.property  name="telNo"
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * @return
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return
	 * @uml.property  name="address"
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 * @uml.property  name="address"
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return
	 * @uml.property  name="userType"
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType
	 * @uml.property  name="userType"
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return
	 * @uml.property  name="useFlag"
	 */
	public String getUseFlag() {
		return useFlag;
	}
	/**
	 * @param useFlag
	 * @uml.property  name="useFlag"
	 */
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	/**
	 * @return
	 * @uml.property  name="userDesc"
	 */
	public String getUserDesc() {
		return userDesc;
	}
	/**
	 * @param userDesc
	 * @uml.property  name="userDesc"
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	/**
	 * @return
	 * @uml.property  name="regDate"
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate
	 * @uml.property  name="regDate"
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return
	 * @uml.property  name="updtDate"
	 */
	public String getUpdtDate() {
		return updtDate;
	}
	/**
	 * @param updtDate
	 * @uml.property  name="updtDate"
	 */
	public void setUpdtDate(String updtDate) {
		this.updtDate = updtDate;
	}
	/**
	 * @return
	 * @uml.property  name="userHost"
	 */
	public String getUserHost() {
		return userHost;
	}
	/**
	 * @param userHost
	 * @uml.property  name="userHost"
	 */
	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}
	/**
	 * @return
	 * @uml.property  name="pswdUpdtDate"
	 */
	public String getPswdUpdtDate() {
		return pswdUpdtDate;
	}
	/**
	 * @param pswdUpdtDate
	 * @uml.property  name="pswdUpdtDate"
	 */
	public void setPswdUpdtDate(String pswdUpdtDate) {
		this.pswdUpdtDate = pswdUpdtDate;
	}
	/**
	 * @return
	 * @uml.property  name="logTrialCnt"
	 */
	public int getLogTrialCnt() {
		return logTrialCnt;
	}
	/**
	 * @param logTrialCnt
	 * @uml.property  name="logTrialCnt"
	 */
	public void setLogTrialCnt(int logTrialCnt) {
		this.logTrialCnt = logTrialCnt;
	}
	/**
	 * @return
	 * @uml.property  name="logIndc"
	 */
	public String getLogIndc() {
		return logIndc;
	}
	/**
	 * @param logIndc
	 * @uml.property  name="logIndc"
	 */
	public void setLogIndc(String logIndc) {
		this.logIndc = logIndc;
	}
	/**
	 * @return
	 * @uml.property  name="userMacAddress"
	 */
	public String getUserMacAddress() {
		return userMacAddress;
	}
	/**
	 * @param userMacAddress
	 * @uml.property  name="userMacAddress"
	 */
	public void setUserMacAddress(String userMacAddress) {
		this.userMacAddress = userMacAddress;
	}
	
	/**
	 * @return
	 * @uml.property  name="authCode"
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * @param authCode
	 * @uml.property  name="authCode"
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	/**
	 * @return
	 * @uml.property  name="connIp"
	 */
	public String getConnIp() {
		return connIp;
	}

	/**
	 * @param connIp
	 * @uml.property  name="connIp"
	 */
	public void setConnIp(String connIp) {
		this.connIp = connIp;
	}

	/**
	 * @return
	 * @uml.property  name="delYn"
	 */
	public String getDelYn() {
		return delYn;
	}

	/**
	 * @param delYn
	 * @uml.property  name="delYn"
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	/**
	 * @return
	 * @uml.property  name="otpSeq"
	 */
	public String getOtpSeq() {
		return otpSeq;
	}

	/**
	 * @param otpSeq
	 * @uml.property  name="otpSeq"
	 */
	public void setOtpSeq(String otpSeq) {
		this.otpSeq = otpSeq;
	}

	/**
	 * @return
	 * @uml.property  name="otpVerfNo"
	 */
	public String getOtpVerfNo() {
		return otpVerfNo;
	}

	/**
	 * @param otpVerfNo
	 * @uml.property  name="otpVerfNo"
	 */
	public void setOtpVerfNo(String otpVerfNo) {
		this.otpVerfNo = otpVerfNo;
	}
	
	/**
	 * @return
	 * @uml.property  name="inOtpVerfNo"
	 */
	public String getInOtpVerfNo() {
		return inOtpVerfNo;
	}

	/**
	 * @param inOtpVerfNo
	 * @uml.property  name="inOtpVerfNo"
	 */
	public void setInOtpVerfNo(String inOtpVerfNo) {
		this.inOtpVerfNo = inOtpVerfNo;
	}

	/**
	 * @return
	 * @uml.property  name="sendDiv"
	 */
	public String getSendDiv() {
		return sendDiv;
	}

	/**
	 * @param sendDiv
	 * @uml.property  name="sendDiv"
	 */
	public void setSendDiv(String sendDiv) {
		this.sendDiv = sendDiv;
	}

	/**
	 * @return
	 * @uml.property  name="mobile1"
	 */
	public String getMobile1() {
		return mobile1;
	}

	/**
	 * @param mobile1
	 * @uml.property  name="mobile1"
	 */
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	/**
	 * @return
	 * @uml.property  name="mobile2"
	 */
	public String getMobile2() {
		return mobile2;
	}

	/**
	 * @param mobile2
	 * @uml.property  name="mobile2"
	 */
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	/**
	 * @return
	 * @uml.property  name="mobile3"
	 */
	public String getMobile3() {
		return mobile3;
	}

	/**
	 * @param mobile3
	 * @uml.property  name="mobile3"
	 */
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	/**
	 * @return
	 * @uml.property  name="lastLogin"
	 */
	public String getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 * @uml.property  name="lastLogin"
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return
	 * @uml.property  name="contractnum"
	 */
	public String getContractnum() {
		return contractnum;
	}

	/**
	 * @param contractnum
	 * @uml.property  name="contractnum"
	 */
	public void setContractnum(String contractnum) {
		this.contractnum = contractnum;
	}

	/**
	 * @return
	 * @uml.property  name="otherm"
	 */
	public String getOtherm() {
		return otherm;
	}

	/**
	 * @param otherm
	 * @uml.property  name="otherm"
	 */
	public void setOtherm(String otherm) {
		this.otherm = otherm;
	}
	
	
}
