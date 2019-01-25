package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class TestEventStatDaoImpl implements EventStatDao {

	@Override
	public EventStatVo getEventStat(EventStatVo eventStatVo) {
		return null;
	}

	@Override
	public boolean insertEventStat(EventStatVo eventStatVo) {
		return false;
	}

	@Override
	public boolean updateEventStat(EventStatVo eventStatVo) {
		return false;
	}

	@Override
	public List<EventStatVo> listEventStatHour(EventStatSearchVo searchVo) {
		return null;
	}

	@Override
	public List<EventStatVo> listEventStatDay(EventStatSearchVo searchVo) {
		return null;
	}

	@Override
	public List<EventStatVo> listEventStatMonth(EventStatSearchVo searchVo) {
		return null;
	}

	@Override
	public List<EventStatVo> listEventStatBySite(EventStatSearchVo searchVo) {
		return null;
	}

	@Override
	public int totalEventStatHour(EventStatSearchVo searchVo) {
		return 0;
	}

	@Override
	public int totalEventStatDay(EventStatSearchVo searchVo) {
		return 0;
	}

	@Override
	public int totalEventStatMonth(EventStatSearchVo searchVo) {
		return 0;
	}

	@Override
	public int totalEventStatBySite(EventStatSearchVo searchVo) {
		return 0;
	}
	/*
	public List<EventStatVo> listEventStatBySiteExcel(EventStatSearchVo searchVo) {
	    return null;
	 }*/

}
