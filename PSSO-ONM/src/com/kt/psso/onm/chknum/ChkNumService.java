package com.kt.psso.onm.chknum;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.db.dao.ChkNumDao;
import com.kt.psso.onm.common.SearchVo;

@Service
public class ChkNumService {

	/**
	 * @uml.property  name="chkNumDao"
	 * @uml.associationEnd  
	 */
	private ChkNumDao chkNumDao;

	public List<ChkNumVo> list(SearchVo searchVo) {
		return chkNumDao.listChkNum(searchVo);
		
	}
	
	//-------------------- Getter & Setter --------------------//
	
	/**
	 * @param chkNumDao
	 * @uml.property  name="chkNumDao"
	 */
	@Autowired
	public void setChkNumDao(ChkNumDao chkNumDao) {
		this.chkNumDao = chkNumDao;
	}
	
}
