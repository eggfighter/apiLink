package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public interface EventStatDao {

	EventStatVo getEventStat(EventStatVo eventStatVo);

	boolean insertEventStat(EventStatVo eventStatVo);

	boolean updateEventStat(EventStatVo eventStatVo);

	List<EventStatVo> listEventStatHour(EventStatSearchVo searchVo);

	List<EventStatVo> listEventStatDay(EventStatSearchVo searchVo);

	List<EventStatVo> listEventStatMonth(EventStatSearchVo searchVo);

	List<EventStatVo> listEventStatBySite(EventStatSearchVo searchVo);
	
	//List<EventStatVo> listEventStatBySiteExcel(EventStatSearchVo paramEventStatSearchVo);

	int totalEventStatHour(EventStatSearchVo searchVo);

	int totalEventStatDay(EventStatSearchVo searchVo);

	int totalEventStatMonth(EventStatSearchVo searchVo);

	int totalEventStatBySite(EventStatSearchVo searchVo);

}
