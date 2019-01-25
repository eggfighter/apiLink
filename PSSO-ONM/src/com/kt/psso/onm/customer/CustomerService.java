package com.kt.psso.onm.customer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.CustomerDao;
import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.hc.LineWriter;
import com.kt.psso.onm.util.AuthUtil;
import com.kt.psso.onm.util.CryptoUtil;

@Service
public class CustomerService {

	/**
	 * @uml.property  name="customerDao"
	 * @uml.associationEnd  
	 */
	private CustomerDao customerDao;
	
	/**
	 * @uml.property  name="cryptoUtil"
	 * @uml.associationEnd  
	 */
	private CryptoUtil cryptoUtil;

	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="customerWriter"
	 * @uml.associationEnd  
	 */
	private LineWriter customerWriter;

	/**
	 * @uml.property  name="authUtil"
	 * @uml.associationEnd  
	 */
	private AuthUtil authUtil = new AuthUtil();

	/**
	 * @uml.property  name="eventLogDao"
	 * @uml.associationEnd  
	 */
	private EventLogDao eventLogDao;

	public List<CustomerVo> list(SearchVo searchVo) {
		/*// encrrn
		String pNcid = searchVo.getpNcid();
		if (pNcid != null && !pNcid.equals("")) {
			searchVo.setpEncrrn(cryptoUtil.encryptData(pNcid));
		}
		writeCustomerLog(searchVo, "고객 조회");*/
		return customerDao.listCustomer(searchVo);
	}

	public boolean mod(HttpSession session, CustomerVo customerVo) {
		// encuserpwd
		String userpwd = customerVo.getUserpwd();
		if (userpwd != null && !userpwd.equals("")) {
			customerVo.setEncuserpwd(cryptoUtil.hash(userpwd.trim()));
		}
		//save chngHist
		customerVo.setChgFlag("U");
		boolean flag = chngHistIns(customerVo);				
		
		
		// save EventLog
		CustomerVo customer = customerDao.getCustomer(customerVo.getCn());
		
		EventLogHelper eventLogHelper = new EventLogHelper(authUtil.getUserId(session));
		eventLogHelper.setEventLogDao(eventLogDao);
		eventLogHelper.saveMod(customer, customerVo);

		return customerDao.updateCustomer(customerVo);
	}
	/*
	 * 2014-11-17 트리거를 쿼리로
	 * */
	public boolean chngHistIns(CustomerVo customerVo) {
		return customerDao.insertUserChngHist(customerVo);
	}
	
	public boolean del(CustomerVo customerVo) {
		customerVo.setChgFlag("U");
		
		boolean flag = false;
		flag = customerDao.deleteCustomer(customerVo);
		
		if (flag)
			flag = chngHistIns(customerVo);
		
		return flag; 
	}
	
	public boolean pwreset(CustomerVo customerVo) {
		boolean flag = false;
		flag = customerDao.pwresetCustomer(customerVo);
		
		if (flag)
			flag = chngHistIns(customerVo);
		
		return flag;
	}

	public CustomerVo get(String cn) throws Exception {
		CustomerVo customerVo = null;
		try
		{
			customerVo = customerDao.getCustomer(cn);
			// conncid
			String encconncid = customerVo.getEncconncid();
			if (encconncid != null && !encconncid.equals("")) {
				customerVo.setConncid(cryptoUtil.decryptData(encconncid));
			}
			// ncid
			String encncid = customerVo.getEncncid();
			if (encncid != null && !encncid.equals("")) {
				customerVo.setNcid(cryptoUtil.decryptData(encncid));
			}
			
		}catch(NullPointerException ne)
		{
			if (LOG.isInfoEnabled()) {
				LOG.warn(ne.toString());
			}
		}
		// rrn
	
		/*String encrrn = customerVo.getEncrrn();
		if (encrrn != null && !encrrn.equals("")) {
			customerVo.setRrn(cryptoUtil.decryptData(encrrn));
		}*/
		
		return customerVo;
		
	}

	public CustomerVo get(HttpSession session, String cn) throws Exception {
		CustomerVo customerVo = get(cn);

		// save EventLog
		if (customerVo != null) {
			EventLogHelper eventLogHelper = new EventLogHelper(authUtil.getUserId(session));
			eventLogHelper.setEventLogDao(eventLogDao);
			eventLogHelper.saveView(customerVo);
		}

		return customerVo;
	}

	public String[][] getPwdHintArr() {
		return new String[][] {
				{"1", "가장 기억에 남는 추억의 장소는?"},
				{"2", "나의 좌우명은?"},
				{"3", "나의 보물 제1호는?"}, 
				{"4", "본인의 출생날짜는?"},
				{"5", "나의 출신 초등학교는?"}, 
				{"6", "인상 깊에 읽은 책 이름은?"},
				{"7", "인상 깊게 본 영화 이름은?"},
				{"8", "내가 존경하는 인물은?"},
				{"9", "나의 노래방 애창곡은?"},
				{"10", "다시 태어나면 되고 싶은 것은?"},
				{"11", "좋아하는 스포츠팀 이름은?"},
				{"12", "내 어릴적 별명은?"}
			};
	}
	
	void writeCustomerLog(SearchVo searchVo, String action) {
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
			customerWriter.write(sb.toString());
		} catch (IOException e) {
			if (LOG.isInfoEnabled()) {
				LOG.warn(sb.toString());
			}
		}		
	}
	
	public int count() {
		return customerDao.countCustomer();
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param customerDao
	 * @uml.property  name="customerDao"
	 */
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * @param cryptoUtil
	 * @uml.property  name="cryptoUtil"
	 */
	@Autowired
	public void setCryptoUtil(CryptoUtil cryptoUtil) {
		this.cryptoUtil = cryptoUtil;
	}

	/**
	 * @param eventLogDao
	 * @uml.property  name="eventLogDao"
	 */
	@Autowired
	public void setEventLogDao(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}
	
	/**
	 * @param customerWriter
	 * @uml.property  name="customerWriter"
	 */
	@Autowired
	public void setCustomerWriter(LineWriter customerWriter) {
		this.customerWriter = customerWriter;
	}

}
