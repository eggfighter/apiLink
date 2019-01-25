package com.kt.psso.onm.scheduler.eventlog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.scheduler.LogDataProcessor;
import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;

public class EventLogDataProcessor implements LogDataProcessor {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="eventLogDao"
	 * @uml.associationEnd  
	 */
	private EventLogDao eventLogDao;
	/**
	 * @uml.property  name="logParser"
	 * @uml.associationEnd  
	 */
	private LogParser logParser;

	/**
	 * @uml.property  name="count"
	 */
	private int count;

	/**
	 * @param eventLogDao
	 * @uml.property  name="eventLogDao"
	 */
	public void setEventLogDao(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
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
		count = 0;
	}

	@Override
	public void add(String line) throws LogParserException {
		EventLogVo eventLogVo = null;
		eventLogVo = (EventLogVo) logParser.parse(line);

		if (eventLogVo != null) {
			boolean result = eventLogDao.insertEventLog(eventLogVo);
			if (LOG.isDebugEnabled()) {
				LOG.debug("result = " + result);
			}
		}
		
		count++;
	}

	@Override
	public void save() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("count = " + count);
		}
	}

}
