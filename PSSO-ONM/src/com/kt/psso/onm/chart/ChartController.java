package com.kt.psso.onm.chart;

import java.io.IOException;
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

import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.eventstat.EventStatService;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

@Controller
public class ChartController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="eventStatService"
	 * @uml.associationEnd  
	 */
	@Autowired
	private EventStatService eventStatService;

	/**
	 * @uml.property  name="chartService"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ChartService chartService;

	@RequestMapping(method=RequestMethod.GET, value="/eventstat/time/chart.do")
	public ModelAndView listByTime(HttpServletRequest req, HttpServletResponse res, EventStatSearchVo searchVo) throws IOException {
		List<EventStatVo> listEventStat = eventStatService.listByTime(searchVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		res.setContentType("image/jpeg");
		chartService.writeBarChart(res.getOutputStream(), chartService.createDataset(listEventStat), searchVo);

		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/authstat/time/chart.do")
	public ModelAndView authListByTime(HttpServletRequest req, HttpServletResponse res, EventStatSearchVo searchVo) throws IOException {
		List<EventStatVo> listEventStat = eventStatService.listByTime(searchVo);

		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		res.setContentType("image/jpeg");
		chartService.writeBarChart(res.getOutputStream(), chartService.createDataset(listEventStat), searchVo);

		return null;
	}


}
