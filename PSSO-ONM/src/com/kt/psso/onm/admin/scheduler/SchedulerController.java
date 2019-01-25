package com.kt.psso.onm.admin.scheduler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchedulerController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="schedulerService"
	 * @uml.associationEnd  
	 */
	@Autowired
	private SchedulerService schedulerService;
	
	@RequestMapping(method=RequestMethod.GET, value="/in/scheduler/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res) throws SchedulerException {
		List<SchedulerVo> listScheduler = schedulerService.list();

		if (LOG.isInfoEnabled()) {
			LOG.info("listScheduler.size() = " + listScheduler.size());
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("listScheduler = " + listScheduler);
		}

		ModelAndView mav = new ModelAndView("admin.scheduler.view");
		mav.addObject("listScheduler", listScheduler);
		return mav;
	}

}
