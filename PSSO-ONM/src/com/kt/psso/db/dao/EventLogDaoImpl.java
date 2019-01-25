package com.kt.psso.db.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;

@Repository
public class EventLogDaoImpl extends SqlSessionDaoSupport implements EventLogDao {

	@Override
	public List<EventLogVo> listEventLog(SearchVo searchVo) {
		RowBounds rowBounds = new RowBounds(searchVo.getOffset(), searchVo.getLimit());
		return getSqlSession().selectList("eventlog.listEventLog", searchVo, rowBounds);
	}

	@Override
	public int countEventLog(SearchVo searchVo) {
		return (Integer) getSqlSession().selectOne("eventlog.countEventLog", searchVo);
	}

	@Override
	public EventLogVo getEventLog(int seq) {
		return (EventLogVo) getSqlSession().selectOne("eventlog.getEventLog", seq);
	}

	@Override
	public boolean insertEventLog(EventLogVo eventLogVo) {
		int result = getSqlSession().insert("eventlog.insertEventLog", eventLogVo);
		return (result == 1);
	}

}
