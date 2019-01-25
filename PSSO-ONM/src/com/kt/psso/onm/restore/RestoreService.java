package com.kt.psso.onm.restore;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kt.psso.db.dao.RestoreDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.hc.LineWriter;

@Service
public class RestoreService {

	/**
	 * @uml.property  name="restoreDao"
	 * @uml.associationEnd  
	 */
	private RestoreDao restoreDao;
	
	/**
	 * @uml.property  name="userRestoreWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter userRestoreWriter;
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	public List<RestoreVo> list(SearchVo searchVo) {
		
		return restoreDao.listRestore(searchVo);
	}
	public void writeUserRestoreLog(String userId, String regDate, String regIp, String lastLogin) {
		String time = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'").format(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		sb.append(time).append(" "+userId).append(", ")
		  .append(regDate).append(", ")
		  .append(regIp).append(", ")
		  .append(lastLogin).append("\n");
		try {
			userRestoreWriter.write(sb.toString());
		} catch (IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.warn(sb.toString());
			}
		}		
		
	}
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param restoreDao
	 * @uml.property  name="restoreDao"
	 */
	@Autowired
	public void setRestoreDao(RestoreDao restoreDao) {
		this.restoreDao = restoreDao;
	}
	@Autowired
	public void setuserRestoreWriter(LineWriter userRestoreWriter) {
		this.userRestoreWriter = userRestoreWriter;
	}
	

}
