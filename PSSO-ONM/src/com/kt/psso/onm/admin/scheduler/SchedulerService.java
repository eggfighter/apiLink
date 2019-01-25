package com.kt.psso.onm.admin.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

	/**
	 * @uml.property  name="schedulerFactory"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	public List<SchedulerVo> list() throws SchedulerException {
		ArrayList<SchedulerVo> list = new ArrayList<SchedulerVo>();

		Scheduler scheduler = schedulerFactory.getScheduler();
		String[] jobGroupNames = scheduler.getJobGroupNames();
		for (String groupName : jobGroupNames) {
			String[] jobNames = scheduler.getJobNames(groupName);
			for (String jobName : jobNames) {
				Trigger[] triggersOfJob = scheduler.getTriggersOfJob(jobName, groupName);
				String cronExpression = ((CronTrigger) triggersOfJob[0]).getCronExpression();
				SchedulerVo schedulerVo = new SchedulerVo();
				schedulerVo.setGroupName(groupName);
				schedulerVo.setJobName(jobName);
				schedulerVo.setCronExpression(cronExpression);
				list.add(schedulerVo);
			}
		}

		return list;
	}

}
