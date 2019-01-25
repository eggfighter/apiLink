 package com.kt.psso.db.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.custom14.Custom14Vo;
import com.kt.psso.onm.customer.CustomerVo;

@Repository
public class Custom14DaoImpl extends SqlSessionDaoSupport implements Custom14Dao {

	@Override
	public List<CustomerVo> listCustom14(SearchVo searchVo) {
		return getSqlSession().selectList("custom14.listCustom14", searchVo);
	}

	@Override
	public Custom14Vo getCustom14(String cn) {
		return (Custom14Vo) getSqlSession().selectOne("custom14.getCustom14", cn);
	}

	@Override
	public boolean updateCustom14(CustomerVo customerVo) {
		int result = getSqlSession().update("custommer.updateCustomer", customerVo);
		return (result == 1);
	}

	@Override
	public boolean deleteCustom14(Custom14Vo custom14Vo) {
		int result = getSqlSession().delete("custom14.deleteCustom14", custom14Vo);
		return (result == 1);
	}

	@Override
	public int deleteCustom14Before(String day) {
		return getSqlSession().delete("custom14.deleteCustom14Before", day);
	}
	
	@Override
	public int countCustom14() {
		return (Integer) getSqlSession().selectOne("custom14.countCustom14");
	}


}
