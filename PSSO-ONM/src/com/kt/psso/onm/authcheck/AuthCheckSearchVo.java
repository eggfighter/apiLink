package com.kt.psso.onm.authcheck;

public class AuthCheckSearchVo {

	/**
	 * @uml.property  name="cn"
	 */
	private String cn;
	/**
	 * @uml.property  name="event_type"
	 */
	private String event_type;
	/**
	 * @uml.property  name="timeslice"
	 */
	private String timeslice;
	/**
	 * @uml.property  name="otherm"
	 */
	private String otherm;
	/**
	 * @uml.property  name="displayname"
	 */
	private String displayname;

	@Override
	public String toString() {
		return "AuthCheckVo [cn=" + cn	+ ", event_type=" + event_type
				+ ", timeslice=" + timeslice + ", otherm="	+ otherm + 
				", displayname=" + displayname + "]";
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
	 * @uml.property  name="event_type"
	 */
	public String getEvent_type() {
		return event_type;
	}
	/**
	 * @param event_type
	 * @uml.property  name="event_type"
	 */
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	/**
	 * @return
	 * @uml.property  name="timeslice"
	 */
	public String getTimeslice() {
		return timeslice;
	}
	/**
	 * @param timeslice
	 * @uml.property  name="timeslice"
	 */
	public void setTimeslice(String timeslice) {
		this.timeslice = timeslice;
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
	
}
