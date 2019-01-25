package com.kt.psso.onm.admin.eventlog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;
import com.kt.psso.onm.util.PagingUtil;

@Controller
public class EventLogController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="eventLogService"
	 * @uml.associationEnd  
	 */
	private EventLogService eventLogService;
	
	@RequestMapping(method=RequestMethod.GET, value="/in/eventlog/list.do")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res, SearchVo searchVo) {
		List<EventLogVo> listEventLog = eventLogService.list(searchVo);
		int count = eventLogService.count(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("admin.eventlog.list");
		mav.addObject("listEventLog", listEventLog);
		PagingUtil pagingUtil = new PagingUtil(count, searchVo.getpPageNo(), searchVo.getpPageCount());
		mav.addObject("paging", pagingUtil);
		mav.addObject("operations", eventLogService.getOperations());
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET, value="/in/eventlog/view.do")
	public ModelAndView view(HttpServletRequest req, HttpServletResponse res, EventLogVo eventLogVo) {
		int seq = eventLogVo.getSeq();

		if (LOG.isInfoEnabled()) {
			LOG.info("seq = " + seq);
		}

		if (seq > 0) {
			eventLogVo = eventLogService.get(seq);
		}
		ModelAndView mav = new ModelAndView("admin.eventlog.view");
		mav.addObject("eventLogVo", eventLogVo);
		mav.addObject("operations", eventLogService.getOperations());
		return mav;
	}

	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param eventLogService
	 * @uml.property  name="eventLogService"
	 */
	@Autowired
	public void setEventLogService(EventLogService eventLogService) {
		this.eventLogService = eventLogService;
	}

}
