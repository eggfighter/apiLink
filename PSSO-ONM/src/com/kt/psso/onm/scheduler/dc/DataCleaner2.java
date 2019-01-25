package com.kt.psso.onm.scheduler.dc;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.Custom14Dao;

public class DataCleaner2 {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="custom14Dao"
	 * @uml.associationEnd  
	 */
	private Custom14Dao custom14Dao;

	/**
	 * @param custom14Dao
	 * @uml.property  name="custom14Dao"
	 */
	public void setCustom14Dao(Custom14Dao custom14Dao) {
		this.custom14Dao = custom14Dao;
	}

	public void cleanData(long offset) {
		long time = System.currentTimeMillis() + (offset * 3600000L);
		String day = new SimpleDateFormat("yyyy-MM-dd").format(time);

		if (LOG.isInfoEnabled()) {
			LOG.info("day = " + day);
		}

		int result1 = 0;
		try {
			result1 = custom14Dao.deleteCustom14Before(day);
		} finally {
			if (LOG.isInfoEnabled()) {
				LOG.info("custom14 result = " + result1);
			}
		}

	}

}
