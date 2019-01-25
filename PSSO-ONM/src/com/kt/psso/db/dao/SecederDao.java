package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.seceder.SecederVo;

public interface SecederDao {

	List<SecederVo> listSeceder(SearchVo searchVo);
	
	int countSeceder(); 

}
