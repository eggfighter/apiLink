package com.kt.psso.onm.seceder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.SecederDao;
import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.hc.LineWriter;
import com.kt.psso.onm.util.CryptoUtil;

@Service
public class SecederService {

	/**
	 * @uml.property  name="secederDao"
	 * @uml.associationEnd  
	 */
	private SecederDao secederDao;
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	//private CryptoUtil cryptoUtil;
	
	/**
	 * @uml.property  name="secederWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter secederWriter;

	public List<SecederVo> list(SearchVo searchVo) {
		/*// encrrn
		String pNcid = searchVo.getpNcid();
		if (pNcid != null && !pNcid.equals("")) {
			searchVo.setpEncrrn(cryptoUtil.encryptData(pNcid));
		}*/
		
		writeSecederLog(searchVo, "탈퇴자 조회");
		
		return secederDao.listSeceder(searchVo);
		
	}
	
	void writeSecederLog(SearchVo searchVo, String action) {
		String userId = searchVo.getpCn();
		String rrn7 = searchVo.getpRrn7();
		String time = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss']'").format(System.currentTimeMillis());
		StringBuilder sb = new StringBuilder();
		if(rrn7.equals("")){
			sb.append(time).append(" "+action)
			  .append(" || "+userId).append("\n");
		} else {
			sb.append(time).append(" "+action)
			  .append(" || "+rrn7).append("\n");
		}
		try {
			secederWriter.write(sb.toString());
		} catch (IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.warn(sb.toString());
			}
		}		
	}
	
	public int count() {
		return secederDao.countSeceder();
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param secederDao
	 * @uml.property  name="secederDao"
	 */
	@Autowired
	public void setSecederDao(SecederDao secederDao) {
		this.secederDao = secederDao;
	}

	/*@Autowired
	public void setCryptoUtil(CryptoUtil cryptoUtil) {
		this.cryptoUtil = cryptoUtil;
	}*/
	
	/**
	 * @param secederWriter
	 * @uml.property  name="secederWriter"
	 */
	@Autowired
	public void setSecederWriter(LineWriter secederWriter) {
		this.secederWriter = secederWriter;
	}
	
}
