package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.CustomerVo;

public interface CustomerDao {

	List<CustomerVo> listCustomer(SearchVo searchVo);

	CustomerVo getCustomer(String cn);

	boolean updateCustomer(CustomerVo customerVo);
	
	boolean updateLast(CustomerVo customerVo);

	boolean deleteCustomer(CustomerVo customerVo);
	
	boolean pwresetCustomer(CustomerVo customerVo);
	
	int countCustomer(); 
	
	boolean insertUserChngHist(CustomerVo customerVo);

}
