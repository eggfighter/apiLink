package com.kt.psso.onm.admin.scheduler;

public class SchedulerVo {
	/**
	 * @uml.property  name="groupName"
	 */
	private String groupName;
	/**
	 * @uml.property  name="jobName"
	 */
	private String jobName;
	/**
	 * @uml.property  name="cronExpression"
	 */
	private String cronExpression;

	@Override
	public String toString() {
		return "SchedulerVo [groupName=" + groupName + ", jobName=" + jobName
				+ ", cronExpression=" + cronExpression + "]";
	}

	/**
	 * @return
	 * @uml.property  name="groupName"
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 * @uml.property  name="groupName"
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return
	 * @uml.property  name="jobName"
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName
	 * @uml.property  name="jobName"
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return
	 * @uml.property  name="cronExpression"
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * @param cronExpression
	 * @uml.property  name="cronExpression"
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}
