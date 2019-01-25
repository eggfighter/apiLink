package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.authcheck.AuthCheckSearchVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.scheduler.authcheck.AuthCheckVo;

public interface AuthCheckDao {

	List<AuthCheckSearchVo> listAuthCheck(SearchVo searchVo);

	boolean insertAuthCheck(AuthCheckVo authCheckVo);

}
