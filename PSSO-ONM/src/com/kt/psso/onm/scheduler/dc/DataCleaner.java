package com.kt.psso.onm.scheduler.dc;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.MarklogDao;
import com.kt.psso.db.dao.PhonechkDao;

public class DataCleaner {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="marklogDao"
	 * @uml.associationEnd  
	 */
	private MarklogDao marklogDao;

	/**
	 * @uml.property  name="phonechkDao"
	 * @uml.associationEnd  
	 */
	private PhonechkDao phonechkDao;

	/**
	 * @param marklogDao
	 * @uml.property  name="marklogDao"
	 */
	public void setMarklogDao(MarklogDao marklogDao) {
		this.marklogDao = marklogDao;
	}

	/**
	 * @param phonechkDao
	 * @uml.property  name="phonechkDao"
	 */
	public void setPhonechkDao(PhonechkDao phonechkDao) {
		this.phonechkDao = phonechkDao;
	}

	public void cleanData(long offset) {
		long time = System.currentTimeMillis() + (offset * 3600000L);
		String day = new SimpleDateFormat("yyyy-MM-dd").format(time);

		if (LOG.isInfoEnabled()) {
			LOG.info("day = " + day);
		}

		boolean result1 = false;
		boolean result2 = false;
		try {
			result1 = marklogDao.deleteMarklogBefore(day);
			result2 = phonechkDao.deletePhonechkBefore(day);
		} finally {
			if (LOG.isInfoEnabled()) {
				LOG.info("marklog result = " + result1);
				LOG.info("phonechk result = " + result2);
			}
		}

	}

}
