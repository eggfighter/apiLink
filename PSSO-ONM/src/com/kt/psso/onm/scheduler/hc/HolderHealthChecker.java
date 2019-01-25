package com.kt.psso.onm.scheduler.hc;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import CheckPlus.kisinfo.CheckPlusMobile;

public class HolderHealthChecker {
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="writer"
	 * @uml.associationEnd  
	 */
	private LineWriter writer;

	/**
	 * @uml.property  name="siteCode"
	 */
	private String siteCode;	// CheckPlus 서비스 사이트 코드 (한국신용평가정보에서 발급한 사이트
	/**
	 * @uml.property  name="jumin"
	 */
	private String jumin;		// 주민등록번호
	/**
	 * @uml.property  name="mobileCo"
	 */
	private String mobileCo;	// 이통사 구분 (SKT / KTF / LGT)
	/**
	 * @uml.property  name="mobileNo"
	 */
	private String mobileNo;	// 휴대폰 번호

	public void check() {
		if (LOG.isInfoEnabled()) {
			LOG.info("begin");
		}

		String cpRequest = "HEALTH_CHECK";

		if (LOG.isDebugEnabled()) {
			LOG.debug("siteCode = " + siteCode);
			LOG.debug("jumin = " + jumin);
			LOG.debug("mobileCo = " + mobileCo);
			LOG.debug("mobileNo = " + mobileNo);
			LOG.debug("cpRequest = " + cpRequest);
		}

		// 객체 생성
		CheckPlusMobile cpMobile = new CheckPlusMobile();

		// Method 결과값(iRtn)에 따라, 프로세스 진행여부를 파악합니다.
		int iRtn = cpMobile.fnMobileAuth(siteCode, jumin, mobileCo, mobileNo, cpRequest);

		// Method 결과값에 따른 처리사항
		if (LOG.isInfoEnabled()) {
			if (iRtn == 0) {
				/*
				 * - 응답코드에 따라 SMS 인증 여부를 판단합니다.
				 * 
				 * - 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
				 */
				LOG.info("REQ_SEQ=" + cpMobile.getRequestSEQ()); // 요청 고유번호
				LOG.info("RES_SEQ=" + cpMobile.getResponseSEQ()); // 응답 고유번호
			} else if (iRtn == 1) {
				LOG.info("명의자 불일치입니다. [" + iRtn + "]");
			} else if (iRtn == -7 || iRtn == -8) {
				LOG.info("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
				LOG.info("IP : 203.234.219.39 / Port : 3741");
			} else if (iRtn == -9 || iRtn == -10 || iRtn == 13) {
				LOG.info("입력값 오류 [" + iRtn + "] : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			} else {
				LOG.info("iRtn 값 확인 후, 한국신용평가정보 개발 담당자에게 문의해 주세요. [" + iRtn + "]");
			}
		}

		log(iRtn);

		if (LOG.isInfoEnabled()) {
			boolean success = (iRtn == 1);
			LOG.info("end. success = " + success);
		}

	}

	private void log(int iRtn) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
		String DELIM = "|";
		String line = time;
		// iRtn이 1(명의자 불일치)인 경우를 성공으로 판단한다.
		line += DELIM + (iRtn == 1 ? "Y" : "N");
		line += DELIM + iRtn;
		line += "\n";

		try {
			writer.write(line);
		} catch (IOException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		}
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param writer
	 * @uml.property  name="writer"
	 */
	public void setWriter(LineWriter writer) {
		this.writer = writer;
	}

	/**
	 * @param siteCode
	 * @uml.property  name="siteCode"
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	/**
	 * @param jumin
	 * @uml.property  name="jumin"
	 */
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	/**
	 * @param mobileCo
	 * @uml.property  name="mobileCo"
	 */
	public void setMobileCo(String mobileCo) {
		this.mobileCo = mobileCo;
	}

	/**
	 * @param mobileNo
	 * @uml.property  name="mobileNo"
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
