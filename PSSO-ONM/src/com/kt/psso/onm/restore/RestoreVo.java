package com.kt.psso.onm.restore;

public class RestoreVo {
	// form
	/**
	 * @uml.property  name="act"
	 */
	private String act;
	// customer
	/**
	 * @uml.property  name="cn"
	 */
	private String cn;
	/**
	 * @uml.property  name="displayname"
	 */
	private String displayname;
	/**
	 * @uml.property  name="mobile"
	 */
	private String mobile;
	/**
	 * @uml.property  name="regdate"
	 */
	private String regdate;
	/**
	 * @uml.property  name="regip"
	 */
	private String regip;
	/**
	 * @uml.property  name="kpm"
	 */
	private String kpm;
	public RestoreVo() {
	}
	
	@Override
	public String toString() {
		return "RestoreVo [act=" + act +"cn=" + cn + ", displayname=" + displayname + ", mobile="
				+ mobile + ", regdate="
				+ regdate + ", regip=" + regip + ", kpm=" + kpm+"]";
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @return
	 * @uml.property  name="act"
	 */
	public String getAct() {
		return act;
	}
	/**
	 * @param act
	 * @uml.property  name="act"
	 */
	public void setAct(String act) {
		this.act = act;
	}	
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
	 * @uml.property  name="displayname"
	 */
	public String getDisplayname() {
		return displayname;
	}
	/**
	 * @param displayname
	 * @uml.property  name="displayname"
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	/**
	 * @return
	 * @uml.property  name="regdate"
	 */
	public String getRegdate() {
		return regdate;
	}
	/**
	 * @param regdate
	 * @uml.property  name="regdate"
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	/**
	 * @return
	 * @uml.property  name="regip"
	 */
	public String getRegip() {
		return regip;
	}
	/**
	 * @param regip
	 * @uml.property  name="regip"
	 */
	public void setRegip(String regip) {
		this.regip = regip;
	}
	/**
	 * @return
	 * @uml.property  name="kpm"
	 */
	public String getKpm() {
		return kpm;
	}
	/**
	 * @param kpm
	 * @uml.property  name="kpm"
	 */
	public void setKpm(String kpm) {
		this.kpm = kpm;
	}
}
