package com.kt.psso.onm.scheduler.lastlog;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.CustomerDao;
import com.kt.psso.onm.customer.CustomerVo;
import com.kt.psso.onm.scheduler.LogDataProcessor;
import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;

public class LastLogDataProcessor implements LogDataProcessor {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="customerDao"
	 * @uml.associationEnd  
	 */
	private CustomerDao customerDao;
	/**
	 * @uml.property  name="logParser"
	 * @uml.associationEnd  
	 */
	private LogParser logParser;
	/**
	 * @uml.property  name="time"
	 */
	private String time;

	/**
	 * @uml.property  name="count"
	 */
	private int count;

	/**
	 * @param customerDao
	 * @uml.property  name="customerDao"
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
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
		long nowTime = System.currentTimeMillis() - 3600000;
		time = new SimpleDateFormat("yyyyMMddHH").format(nowTime); 
		count = 0;
	}

	@Override
	public void add(String line) throws LogParserException {
		CustomerVo customerVo = null;
		customerVo = (CustomerVo) logParser.parse(line);
		
		if (customerVo != null) {			
			if(customerVo.getLastlogin().startsWith(time)){
				boolean result = customerDao.updateLast(customerVo);
				if (LOG.isDebugEnabled()) {
					LOG.debug("result = " + result);
				}
			}
			else return;
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
