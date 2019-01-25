package com.kt.psso.onm.eventstat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EventStatServiceTest {

	private EventStatService service;

	@Before
	public void setUp() throws Exception {
		this.service = new EventStatService();
	}

	@Test
	public void month() {
		EventStatSearchVo searchVo = new EventStatSearchVo();
		searchVo.setpPeriod("MONTH");
		searchVo.setpYear("2011");
		searchVo.setpMonth("12");
		searchVo.setpDay("20");
		searchVo.setpYear2("2012");
		searchVo.setpMonth2("12");
		searchVo.setpDay2("20");
		
		assertEquals("2011.12 ~ 2012.12", service.getDateCondition(searchVo));
	}

	@Test
	public void day() {
		EventStatSearchVo searchVo = new EventStatSearchVo();
		searchVo.setpPeriod("DAY");
		searchVo.setpYear("2011");
		searchVo.setpMonth("12");
		searchVo.setpDay("20");
		searchVo.setpYear2("2012");
		searchVo.setpMonth2("01");
		searchVo.setpDay2("20");
		
		assertEquals("2011.12.20 ~ 2012.01.20", service.getDateCondition(searchVo));
	}

	@Test
	public void hour() {
		EventStatSearchVo searchVo = new EventStatSearchVo();
		searchVo.setpPeriod("HOUR");
		searchVo.setpYear("2011");
		searchVo.setpMonth("12");
		searchVo.setpDay("20");
		searchVo.setpYear2("2011");
		searchVo.setpMonth2("12");
		searchVo.setpDay2("21");
		
		assertEquals("2011.12.20 ~ 2011.12.21", service.getDateCondition(searchVo));
	}

	@Test
	public void isNull() {
		EventStatSearchVo searchVo = new EventStatSearchVo();
		
		assertNull(service.getDateCondition(searchVo));
	}
}
