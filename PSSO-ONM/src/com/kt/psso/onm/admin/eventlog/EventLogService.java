package com.kt.psso.onm.admin.eventlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;

@Service
public class EventLogService {

	/**
	 * @uml.property  name="eventLogDao"
	 * @uml.associationEnd  
	 */
	private EventLogDao eventLogDao;

	public List<EventLogVo> list(SearchVo searchVo) {
		return eventLogDao.listEventLog(searchVo);
	}

	public int count(SearchVo searchVo) {
		return eventLogDao.countEventLog(searchVo);
	}

	public EventLogVo get(int seq) {
		return eventLogDao.getEventLog(seq);
	}

	public Map<String, String> getOperations() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("E", "변경");
		map.put("S", "읽기");
		return map;
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param eventLogDao
	 * @uml.property  name="eventLogDao"
	 */
	@Autowired
	public void setEventLogDao(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}

}
