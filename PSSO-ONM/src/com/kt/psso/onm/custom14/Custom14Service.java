package com.kt.psso.onm.custom14;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.Custom14Dao;
import com.kt.psso.db.dao.CustomerDao;
import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.CustomerVo;
import com.kt.psso.onm.customer.EventLogHelper;
import com.kt.psso.onm.util.AuthUtil;
import com.kt.psso.onm.util.CryptoUtil;

@Service
public class Custom14Service {

	/**
	 * @uml.property  name="custom14Dao"
	 * @uml.associationEnd  
	 */
	private Custom14Dao custom14Dao;
	
	/**
	 * @uml.property  name="cryptoUtil"
	 * @uml.associationEnd  
	 */
	private CryptoUtil cryptoUtil;
	
	private Log LOG = LogFactory.getLog(getClass());
	/**
	 * @uml.property  name="customerDao"
	 * @uml.associationEnd  
	 */
	private CustomerDao customerDao;
	
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

	public List<CustomerVo> list(SearchVo searchVo) throws Exception {
		/* 검색조건 변경으로 주석처리 
		 // encrrn
		String pBday = searchVo.getpBday();
		if (pBday != null && !pBday.equals("")) {
			searchVo.setpEncrrn(cryptoUtil.encryptData(pBday));
		}*/
		List<CustomerVo> list = custom14Dao.listCustom14(searchVo);
		
		for (CustomerVo customerVo : list) {
			// parent_encrrn
			/*String parent_encrrn = custom14Vo.getParent_encrrn();
			if (parent_encrrn != null && !parent_encrrn.equals("")) {
				custom14Vo.setParent_rrn(cryptoUtil.decryptData(parent_encrrn));
			}*/
		}
		
		return list;
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
	
	
	public CustomerVo get(String cn) throws Exception {
		
		CustomerVo customerVo = null;
		try
		{
			customerVo = customerDao.getCustomer(cn);
			// rrn
			/*String encrrn = customerVo.getEncrrn();
			if (encrrn != null && !encrrn.equals("")) {
				customerVo.setRrn(cryptoUtil.decryptData(encrrn));
			}*/
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
	
	public int count() {
		return custom14Dao.countCustom14();
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param custom14Dao
	 * @uml.property  name="custom14Dao"
	 */
	@Autowired
	public void setCustom14Dao(Custom14Dao custom14Dao) {
		this.custom14Dao = custom14Dao;
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
	 * @param customerDao
	 * @uml.property  name="customerDao"
	 */
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
