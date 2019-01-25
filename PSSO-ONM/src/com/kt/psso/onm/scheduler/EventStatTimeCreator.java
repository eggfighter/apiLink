package com.kt.psso.onm.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.EventStatDao;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class EventStatTimeCreator {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="eventStatDao"
	 * @uml.associationEnd  
	 */
	private EventStatDao eventStatDao;
	
	public void create(int offset) {
		if (LOG.isInfoEnabled()) {
			LOG.info("begin, offset = " + offset);
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, offset);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

		String domain = "www.show.co.kr";
		for (int i = 0; i < 24; i++) {
			String timeslice = sdf.format(cal.getTime());
			save(new EventStatVo("LOGIN", timeslice, domain));
			save(new EventStatVo("JOIN", timeslice, domain));
			save(new EventStatVo("WITHDRAW", timeslice, domain));
			save(new EventStatVo("UPDATE", timeslice, domain));

			cal.add(Calendar.HOUR, 1);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("end");
		}
	}

	private void save(EventStatVo newVo) {
		EventStatVo oldVo = eventStatDao.getEventStat(newVo);
		LOG.debug("oldVo = " + oldVo+"+++ newVo ="+newVo);
		if (oldVo == null) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("newVo = " + newVo);
			}

			eventStatDao.insertEventStat(newVo);
		}
	}

	/**
	 * @param eventStatDao
	 * @uml.property  name="eventStatDao"
	 */
	public void setEventStatDao(EventStatDao eventStatDao) {
		this.eventStatDao = eventStatDao;
	}

}
