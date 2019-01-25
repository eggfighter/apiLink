package com.kt.psso.onm.chknum;

public class ChkNumVo {

	/**
	 * @uml.property  name="cn"
	 */
	private String cn;
	/**
	 * @uml.property  name="otp"
	 */
	private String otp;
	/**
	 * @uml.property  name="usedate"
	 */
	private String usedate;
	/**
	 * @uml.property  name="email"
	 */
	private String email;
	/**
	 * @uml.property  name="mobile"
	 */
	private String mobile;
	
	@Override
	public String toString() {
		return "ChkNumVo [cn=" + cn	+ ", mobile=" + mobile
				+ ", otp=" + otp + ", usedate="	+ usedate + 
				", email=" + email + ", mobile=" + mobile + "]";
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @return
	 * @uml.property  name="cn"
	 */
	public String getCn() {
		return cn;
	}
	/**
	 * @param cn
	 * @uml.property  name="cn"
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}
	/**
	 * @return
	 * @uml.property  name="mobile"
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile
	 * @uml.property  name="mobile"
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getotp() {
		return otp;
	}
	public void setotp(String otp) {
		this.otp = otp;
	}
	public String getusedate() {
		return usedate;
	}
	public void setusedate(String usedate) {
		this.usedate = usedate;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getmobile() {
		return mobile;
	}
	public void setmobile(String mobile) {
		this.mobile = mobile;
	}
	
}
