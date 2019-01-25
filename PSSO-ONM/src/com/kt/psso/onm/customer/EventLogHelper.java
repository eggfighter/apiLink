package com.kt.psso.onm.customer;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.kt.psso.db.dao.EventLogDao;
import com.kt.psso.onm.custom14.Custom14Vo;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;

public class EventLogHelper {

	/**
	 * @uml.property  name="eventLogDao"
	 * @uml.associationEnd  
	 */
	private EventLogDao eventLogDao;
	
	/**
	 * @uml.property  name="editorId"
	 */
	private String editorId;

	/**
	 * @uml.property  name="operation"
	 */
	private String operation;

	/**
	 * @uml.property  name="customer"
	 * @uml.associationEnd  
	 */
	private CustomerVo customer;

	/**
	 * @uml.property  name="customerVo"
	 * @uml.associationEnd  
	 */
	private CustomerVo customerVo;

	public EventLogHelper(String editorId) {
		this.editorId = editorId;
	}

	/**
	 * @return
	 * @uml.property  name="eventLogDao"
	 */
	public EventLogDao getEventLogDao() {
		return eventLogDao;
	}

	/**
	 * @param eventLogDao
	 * @uml.property  name="eventLogDao"
	 */
	public void setEventLogDao(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}

	public void saveMod(CustomerVo customer, CustomerVo customerVo) {
		if (customer == null || customerVo == null) return;
		
		this.operation = "E";
		this.customer = customer;
		this.customerVo = customerVo;
		
		saveMod("customert", "고객TYPE");
		saveMod("homephone", "집 전화번호");
		saveMod("receiver", "SMS 수신");
		saveMod("mreceiver", "Email 수신");
		saveMod("updatetime", "변경일시");
		saveMod("birthday", "생년월일");
		saveMod("contractnum", "계약번호");
		saveMod("otherm", "타 이메일 주소");
		saveMod("conncidname", "계약자(가입자) 이름");
		saveMod("brn", "사업자 등록번호");
		saveMod("displayname", "한글 이름");
		saveMod("pwdhint", "비밀번호 힌트");
		saveMod("pwdanswer", "질문의 답변");
		saveMod("lastlogin", "마지막접속일");
		saveMod("pwdupdatetime", "비밀번호 변경일");
//		saveEventLog("nominatorid", "추천자 ID");
		saveMod("regip", "가입경로");
		saveMod("realnamechoice", "실명인증 여부");
		saveMod("svcoff", "폰 일시정지 여부");
		saveMod("kpm", "KPM 가입여부");
		saveMod("encuserpwd", "비밀번호");
	}
	
	private void saveMod(String field, String fieldname) {
		EventLogVo eventLogVo = new EventLogVo();
		try {
			String p1 = BeanUtils.getSimpleProperty(customer, field);
			String p2 = BeanUtils.getSimpleProperty(customerVo, field);
			if (p1 != null && p2 != null && !p1.equals(p2)) {
				eventLogVo.setEditorId(editorId);
				eventLogVo.setUserId(customerVo.getCn());
				eventLogVo.setOperation(operation);
				eventLogVo.setFieldname(fieldname);
				eventLogVo.setBefore(p1);
				eventLogVo.setAfter(p2);
				eventLogVo.setReason(customerVo.getReason());
				eventLogVo.setUrl("");
				eventLogVo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
				eventLogVo.setIp("-");

				eventLogDao.insertEventLog(eventLogVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveView(CustomerVo customerVo) {
		if (customerVo == null) return;

		this.operation = "S";

		EventLogVo eventLogVo = new EventLogVo();
		eventLogVo.setEditorId(editorId);
		eventLogVo.setUserId(customerVo.getCn());
		eventLogVo.setOperation(operation);
		eventLogVo.setFieldname("");
		eventLogVo.setBefore("");
		eventLogVo.setAfter("");
		eventLogVo.setReason("-");
		eventLogVo.setUrl("");
		eventLogVo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		eventLogVo.setIp("-");

		eventLogDao.insertEventLog(eventLogVo);
	}
}
