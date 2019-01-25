package com.kt.psso.db.dao;

import java.util.List;

import com.kt.psso.onm.common.SearchVo;
import com.kt.psso.onm.terms.TermsVersionVo;

public interface TermsVersionDao {

	List<TermsVersionVo> listTermsVersion(SearchVo searchVo);

	int countTermsVersion(SearchVo searchVo);

	boolean insertTermsVersion(TermsVersionVo termsVersionVo);

	boolean deleteTermsVersion(TermsVersionVo termsVersionVo);

}
