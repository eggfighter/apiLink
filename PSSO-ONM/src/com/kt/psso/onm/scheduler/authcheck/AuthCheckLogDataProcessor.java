package com.kt.psso.onm.scheduler.authcheck;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.db.dao.AuthCheckDao;
import com.kt.psso.onm.scheduler.authcheck.AuthCheckVo;
import com.kt.psso.onm.scheduler.LogDataProcessor;
import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;

public class AuthCheckLogDataProcessor implements LogDataProcessor {

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="authCheckDao"
	 * @uml.associationEnd  
	 */
	private AuthCheckDao authCheckDao;
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
	 * @param authCheckDao
	 * @uml.property  name="authCheckDao"
	 */
	public void setAuthCheckDao(AuthCheckDao authCheckDao) {
		this.authCheckDao = authCheckDao;
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
		AuthCheckVo authCheckVo = null;
		authCheckVo = (AuthCheckVo) logParser.parse(line);
		
		if (authCheckVo != null) {			
			if(authCheckVo.getTimeslice().startsWith(time)){
				boolean result = authCheckDao.insertAuthCheck(authCheckVo);
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
