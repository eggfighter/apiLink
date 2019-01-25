package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.admin.member.MemberVo;
import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.restore.RestoreVo;

public interface RestoreDao {

	List<RestoreVo> listRestore(SearchVo searchVo);

}
