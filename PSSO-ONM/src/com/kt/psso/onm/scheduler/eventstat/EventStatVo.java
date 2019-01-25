package com.kt.psso.onm.scheduler.eventstat;

public class EventStatVo {
	
	/**
	 * @uml.property  name="event_type"
	 */
	private String event_type;
	/**
	 * @uml.property  name="timeslice"
	 */
	private String timeslice;
	/**
	 * @uml.property  name="domain"
	 */
	private String domain;
	/**
	 * @uml.property  name="cnt"
	 */
	private int cnt;
	/**
	 * @uml.property  name="year"
	 */
	private String year;
	/**
	 * @uml.property  name="month"
	 */
	private String month;
	/**
	 * @uml.property  name="day"
	 */
	private String day;
	/**
	 * @uml.property  name="hour"
	 */
	private String hour;
	/**
	 * @uml.property  name="regdate"
	 */
	private String regdate;
	/**
	 * @uml.property  name="upddate"
	 */
	private String upddate;

	/**
	 * @uml.property  name="category"
	 */
	private String category;
	
	public EventStatVo() {
	}
	
	public EventStatVo(String event_type, String timeslice, String domain) {
		this.event_type = event_type;
		this.timeslice = timeslice;
		this.domain = domain;
		
		this.year	= timeslice.substring(0, 4);
		this.month	= timeslice.substring(4, 6);
		this.day	= timeslice.substring(6, 8);
		this.hour	= timeslice.substring(8, 10);
	}

	@Override
	public String toString() {
		return "EventStatVo [event_type=" + event_type + ", timeslice="
				+ timeslice + ", domain=" + domain + ", cnt=" + cnt + ", year="
				+ year + ", month=" + month + ", day=" + day + ", hour=" + hour
				+ ", regdate=" + regdate + ", upddate=" + upddate
				+ ", category=" + category + "]";
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
	 * @uml.property  name="domain"
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 * @uml.property  name="domain"
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return
	 * @uml.property  name="cnt"
	 */
	public int getCnt() {
		return cnt;
	}

	/**
	 * @param cnt
	 * @uml.property  name="cnt"
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	/**
	 * @return
	 * @uml.property  name="year"
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 * @uml.property  name="year"
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return
	 * @uml.property  name="month"
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 * @uml.property  name="month"
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return
	 * @uml.property  name="day"
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day
	 * @uml.property  name="day"
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return
	 * @uml.property  name="hour"
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * @param hour
	 * @uml.property  name="hour"
	 */
	public void setHour(String hour) {
		this.hour = hour;
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
	 * @uml.property  name="upddate"
	 */
	public String getUpddate() {
		return upddate;
	}

	/**
	 * @param upddate
	 * @uml.property  name="upddate"
	 */
	public void setUpddate(String upddate) {
		this.upddate = upddate;
	}

	/**
	 * @return
	 * @uml.property  name="category"
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 * @uml.property  name="category"
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
