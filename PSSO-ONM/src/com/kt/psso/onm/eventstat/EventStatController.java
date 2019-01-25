package com.kt.psso.onm.eventstat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

@Controller
public class EventStatController {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="eventStatService"
	 * @uml.associationEnd  
	 */
	private EventStatService eventStatService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new EventStatValidator());
    }
	
	@ExceptionHandler(BindException.class)
	public ModelAndView handleBindException(BindException ex) {
		FieldError error = ex.getBindingResult().getFieldError();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		ModelAndView mav = new ModelAndView("error.bind");
		mav.addObject("error", error);
		mav.addObject("allErrors", allErrors);
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/eventstat/time/list.do")
	public ModelAndView listByTime(HttpServletRequest req, HttpServletResponse res, @Valid EventStatSearchVo searchVo) {
		int total = eventStatService.totalByTime(searchVo);
		List<EventStatVo> listEventStat = eventStatService.listByTime(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("eventstat.time.list");
		mav.addObject("listEventStat", listEventStat);
		mav.addObject("total", total);
		mav.addObject("yearArr", eventStatService.getYearArr());
		mav.addObject("monthArr", eventStatService.getMonthArr());
		mav.addObject("dayArr", eventStatService.getDayArr());
		mav.addObject("eventTypeCondition", eventStatService.getEventTypeCondition(searchVo));
		mav.addObject("periodCondition", eventStatService.getPeriodCondition(searchVo));
		mav.addObject("dateCondition", eventStatService.getDateCondition(searchVo));
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET, value="/eventstat/site/list.do")
	public ModelAndView listBySite(HttpServletRequest req, HttpServletResponse res, @Valid EventStatSearchVo searchVo) {
		int total = eventStatService.totalBySite(searchVo);
		List<EventStatVo> listEventStat = eventStatService.listBySite(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("eventstat.site.list");
		mav.addObject("listEventStat", listEventStat);
		mav.addObject("total", total);
		mav.addObject("yearArr", eventStatService.getYearArr());
		mav.addObject("monthArr", eventStatService.getMonthArr());
		mav.addObject("dayArr", eventStatService.getDayArr());
		mav.addObject("eventTypeCondition", eventStatService.getEventTypeCondition(searchVo));
		mav.addObject("dateCondition", eventStatService.getDateCondition(searchVo));
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/authstat/time/list.do")
	public ModelAndView authListByTime(HttpServletRequest req, HttpServletResponse res, @Valid EventStatSearchVo searchVo) {
		int total = eventStatService.totalByTime(searchVo);
		List<EventStatVo> listEventStat = eventStatService.listByTime(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("authstat.time.list");
		mav.addObject("listEventStat", listEventStat);
		mav.addObject("total", total);
		mav.addObject("yearArr", eventStatService.getYearArr());
		mav.addObject("monthArr", eventStatService.getMonthArr());
		mav.addObject("dayArr", eventStatService.getDayArr());
		mav.addObject("eventTypeCondition", eventStatService.getEventTypeCondition(searchVo));
		mav.addObject("periodCondition", eventStatService.getPeriodCondition(searchVo));
		mav.addObject("dateCondition", eventStatService.getDateCondition(searchVo));
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET, value="/authstat/site/list.do")
	public ModelAndView authListBySite(HttpServletRequest req, HttpServletResponse res, @Valid EventStatSearchVo searchVo) {
		int total = eventStatService.totalBySite(searchVo);
		List<EventStatVo> listEventStat = eventStatService.listBySite(searchVo);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("params = " + searchVo);
		}

		ModelAndView mav = new ModelAndView("authstat.site.list");
		mav.addObject("listEventStat", listEventStat);
		mav.addObject("total", total);
		mav.addObject("yearArr", eventStatService.getYearArr());
		mav.addObject("monthArr", eventStatService.getMonthArr());
		mav.addObject("dayArr", eventStatService.getDayArr());
		mav.addObject("eventTypeCondition", eventStatService.getEventTypeCondition(searchVo));
		mav.addObject("dateCondition", eventStatService.getDateCondition(searchVo));
		return mav;
	}
	
	/*
	// 2012-05-31 엑셀 추가
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, value={"/eventstat/site/excel.do"})
	  public View excel(ModelMap model, HttpServletRequest req, HttpServletResponse res, @Valid EventStatSearchVo searchVo) {
	    model.addAttribute("eventStatsVo", this.eventStatService.listBySiteExcel(searchVo));
	    return new EventStatExcel();
	  }*/


	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param eventStatService
	 * @uml.property  name="eventStatService"
	 */
	@Autowired
	public void setEventStatService(EventStatService eventStatService) {
		this.eventStatService = eventStatService;
	}

}
