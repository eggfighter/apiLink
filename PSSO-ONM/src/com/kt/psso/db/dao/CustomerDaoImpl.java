package com.kt.psso.db.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.customer.CustomerVo;

@Repository
public class CustomerDaoImpl extends SqlSessionDaoSupport implements CustomerDao {

	@Override
	public List<CustomerVo> listCustomer(SearchVo searchVo) {
		RowBounds rowBounds = new RowBounds(searchVo.getOffset(), searchVo.getLimit());
		return getSqlSession().selectList("customer.listCustomer", searchVo, rowBounds);
	}

	@Override
	public CustomerVo getCustomer(String cn) {
		return (CustomerVo) getSqlSession().selectOne("customer.getCustomer", cn);
	}

	@Override
	public boolean updateCustomer(CustomerVo customerVo) {
		int result = getSqlSession().update("customer.updateCustomer", customerVo);
		return (result == 1);
	}
	
	@Override
	public boolean updateLast(CustomerVo customerVo) {
		int result = getSqlSession().update("customer.updateLast", customerVo);
		return (result == 1);
	}

	@Override
	public boolean deleteCustomer(CustomerVo customerVo) {
		int result = getSqlSession().delete("customer.deleteCustomer", customerVo);
		return (result == 1);
	}
		
	@Override
	public boolean pwresetCustomer(CustomerVo customerVo) {
		int result = getSqlSession().delete("customer.pwresetCustomer", customerVo);
		return (result == 1);
	}
	
	@Override
	public int countCustomer() {
		return (Integer) getSqlSession().selectOne("customer.countCustomer");
	}
	
	@Override
	
	public boolean insertUserChngHist(CustomerVo customerVo) {
		int result = getSqlSession().insert("customer.insertUserChngHist", customerVo);
		return (result == 1);
	}
	
}
