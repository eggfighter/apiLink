package com.kt.psso.onm.terms;

public class TermsVersionVo {
	/**
	 * @uml.property  name="version"
	 */
	private String version;
	/**
	 * @uml.property  name="regdate"
	 */
	private String regdate;

	@Override
	public String toString() {
		return "TermsVersionVo [version=" + version + ", regdate=" + regdate
				+ "]";
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @return
	 * @uml.property  name="version"
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 * @uml.property  name="version"
	 */
	public void setVersion(String version) {
		this.version = version;
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

}
