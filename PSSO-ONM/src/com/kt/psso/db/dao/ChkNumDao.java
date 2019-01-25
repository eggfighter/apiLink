package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.chknum.ChkNumVo;
import com.kt.psso.onm.common.SearchVo;

public interface ChkNumDao {

	List<ChkNumVo> listChkNum(SearchVo searchVo);

}
