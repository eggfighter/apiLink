package com.kt.psso.onm.terms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.TermsVersionDao;
import com.kt.psso.onm.common.SearchVo;

@Service
public class TermsVersionService {

	/**
	 * @uml.property  name="termsVersionDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private TermsVersionDao termsVersionDao;

	public List<TermsVersionVo> list(SearchVo searchVo) {
		return termsVersionDao.listTermsVersion(searchVo);
	}

	public int count(SearchVo searchVo) {
		return termsVersionDao.countTermsVersion(searchVo);
	}

	public boolean add(TermsVersionVo termsVersionVo) {
		return termsVersionDao.insertTermsVersion(termsVersionVo);
	}

	public boolean del(TermsVersionVo termsVersionVo) {
		return termsVersionDao.deleteTermsVersion(termsVersionVo);
	}

}
