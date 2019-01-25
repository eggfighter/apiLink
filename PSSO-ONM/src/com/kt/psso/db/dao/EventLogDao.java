package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;

public interface EventLogDao {

	List<EventLogVo> listEventLog(SearchVo searchVo);

	int countEventLog(SearchVo searchVo); 

	EventLogVo getEventLog(int seq);

	boolean insertEventLog(EventLogVo eventLogVo);

}
