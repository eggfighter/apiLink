package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.custom14.Custom14Vo;
import com.kt.psso.onm.customer.CustomerVo;

public interface Custom14Dao {

	List<CustomerVo> listCustom14(SearchVo searchVo);

	Custom14Vo getCustom14(String cn);

	boolean updateCustom14(CustomerVo customerVo);

	boolean deleteCustom14(Custom14Vo custom14Vo);

	int deleteCustom14Before(String day);
	
	int countCustom14(); 

}
