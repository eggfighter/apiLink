package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.CustomerVo;
import com.kt.psso.onm.eventstat.EventStatSearchVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class TestCustomerDaoImpl implements CustomerDao {

	@Override
	public List<CustomerVo> listCustomer(SearchVo searchVo) {
		return null;
	}

	@Override
	public CustomerVo getCustomer(String cn) {
		return null;
	}

	@Override
	public boolean updateCustomer(CustomerVo customerVo) {
		return false;
	}

	@Override
	public boolean updateLast(CustomerVo customerVo) {
		System.out.println(customerVo);
		return false;
	}

	@Override
	public boolean deleteCustomer(CustomerVo customerVo) {
		return false;
	}
	
	@Override
	public int countCustomer() {
		return (Integer) null;
	}

	@Override
	public boolean pwresetCustomer(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUserChngHist(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return false;
	}

}
