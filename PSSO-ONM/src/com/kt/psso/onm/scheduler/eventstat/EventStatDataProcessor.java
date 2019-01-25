package com.kt.psso.onm.scheduler.eventstat;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.EventStatDao;
import com.kt.psso.onm.scheduler.LogDataProcessor;
import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;

public class EventStatDataProcessor implements LogDataProcessor {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="map"
	 * @uml.associationEnd  qualifier="key:java.lang.String com.kt.psso.onm.scheduler.eventstat.EventStatVo"
	 */
	private HashMap<String, EventStatVo> map;

	/**
	 * @uml.property  name="eventStatDao"
	 * @uml.associationEnd  
	 */
	private EventStatDao eventStatDao;
	/**
	 * @uml.property  name="logParser"
	 * @uml.associationEnd  
	 */
	private LogParser logParser;
	
	/**
	 * @param eventStatDao
	 * @uml.property  name="eventStatDao"
	 */
	public void setEventStatDao(EventStatDao eventStatDao) {
		this.eventStatDao = eventStatDao;
	}

	/**
	 * @param logParser
	 * @uml.property  name="logParser"
	 */
	public void setLogParser(LogParser logParser) {
		this.logParser = logParser;
	}

	@Override
	public void init() {
		map = new HashMap<String, EventStatVo>();
	}

	@Override
	public void add(String line) throws LogParserException {
		EventStatVo eventStatVo = null;
		eventStatVo = (EventStatVo) logParser.parse(line);
		
		if (eventStatVo != null) add(eventStatVo);
	}

	private void add(EventStatVo newVo) {
		String event_type = newVo.getEvent_type();
		String timeslice = newVo.getTimeslice();
		String domain = newVo.getDomain();
		String key = event_type + timeslice + domain;
		
		EventStatVo eventStatVo = map.get(key);
		if (eventStatVo == null) {
			eventStatVo = new EventStatVo(event_type, timeslice, domain);
			eventStatVo.setCnt(1);
			map.put(key, eventStatVo);
		} else {
			int cnt = eventStatVo.getCnt();
			eventStatVo.setCnt(++cnt);
		}
	}

	@Override
	public void save() {
		Iterator<EventStatVo> iterator = map.values().iterator();
		while (iterator.hasNext()) {
			EventStatVo next = iterator.next();
			save(next);
		}
	}

	private void save(EventStatVo newVo) {
		boolean result = false;
		EventStatVo oldVo = eventStatDao.getEventStat(newVo);
		if (oldVo == null) {
			result = eventStatDao.insertEventStat(newVo);
		} else {
			int cnt = newVo.getCnt() + oldVo.getCnt();
			newVo.setCnt(cnt);
			result = eventStatDao.updateEventStat(newVo);
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("result = " + result);
		}
	}

}
