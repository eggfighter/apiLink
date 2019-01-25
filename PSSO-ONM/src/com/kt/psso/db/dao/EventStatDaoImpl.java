package com.kt.psso.db.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class EventStatDaoImpl extends SqlSessionDaoSupport implements EventStatDao {

	@Override
	public EventStatVo getEventStat(EventStatVo eventStatVo) {
		return (EventStatVo) getSqlSession().selectOne("eventstat.getEventStat", eventStatVo);
	}

	@Override
	public boolean insertEventStat(EventStatVo eventStatVo) {
		int result = getSqlSession().insert("eventstat.insertEventStat", eventStatVo);
		return (result == 1);
	}

	@Override
	public boolean updateEventStat(EventStatVo eventStatVo) {
		int result = getSqlSession().update("eventstat.updateEventStat", eventStatVo);
		return (result == 1);
	}

	@Override
	public List<EventStatVo> listEventStatHour(EventStatSearchVo searchVo) {
		return getSqlSession().selectList("eventstat.listEventStatHour", searchVo);
	}

	@Override
	public List<EventStatVo> listEventStatDay(EventStatSearchVo searchVo) {
		return getSqlSession().selectList("eventstat.listEventStatDay", searchVo);
	}

	@Override
	public List<EventStatVo> listEventStatMonth(EventStatSearchVo searchVo) {
		return getSqlSession().selectList("eventstat.listEventStatMonth", searchVo);
	}

	@Override
	public List<EventStatVo> listEventStatBySite(EventStatSearchVo searchVo) {
		return getSqlSession().selectList("eventstat.listEventStatBySite", searchVo);
	}

	@Override
	public int totalEventStatHour(EventStatSearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("eventstat.totalEventStatHour", searchVo);
	}

	@Override
	public int totalEventStatDay(EventStatSearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("eventstat.totalEventStatDay", searchVo);
	}

	@Override
	public int totalEventStatMonth(EventStatSearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("eventstat.totalEventStatMonth", searchVo);
	}

	@Override
	public int totalEventStatBySite(EventStatSearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("eventstat.totalEventStatBySite", searchVo);
	}
	/*@Override
	public List<EventStatVo> listEventStatBySiteExcel(EventStatSearchVo searchVo) {
	    return getSqlSession().selectList("eventstat.listEventStatBySiteExcel", searchVo);
	 }*/


}
