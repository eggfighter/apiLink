package com.kt.psso.onm.scheduler.hc;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sciclient.SocketClient;

public class IpinHealthChecker {
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
	 * @uml.property  name="userId"
	 */
	private String userId;		// 회원사ID
	/**
	 * @uml.property  name="host"
	 */
	private String host;
	/**
	 * @uml.property  name="port"
	 */
	private String port;
	/**
	 * @uml.property  name="timeout"
	 */
	private int timeout;
	/**
	 * @uml.property  name="docCode"
	 */
	private String docCode;		// 전문종별코드
	/**
	 * @uml.property  name="jobCode"
	 */
	private String jobCode;		// 업무구분코드
	/**
	 * @uml.property  name="returnCode"
	 */
	private String returnCode;	// 응답코드

	/**
	 * @uml.property  name="jumin"
	 */
	private String jumin;		// 주민번호
	/**
	 * @uml.property  name="code"
	 */
	private String code;		// 식별코드

	public void check() {
		if (LOG.isInfoEnabled()) {
			LOG.info("begin");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("userId = " + userId);
			LOG.debug("host = " + host);
			LOG.debug("port = " + port);
			LOG.debug("timeout = " + timeout);
			LOG.debug("docCode = " + docCode);
			LOG.debug("jobCode = " + jobCode);
			LOG.debug("returnCode = " + returnCode);
			LOG.debug("jumin = " + jumin);
			LOG.debug("code = " + code);
		}

		String strResult = ""; // 실명확인결과값
		String strSendBuf = ""; // 전송전문 Buf
		StringBuffer strRecvBuf = new StringBuffer("");

		String rtnText = "";
		String strDI = ""; // 중복가입확인정보
		String strVer = ""; // CI 버전정보
		String strCI = ""; // CI 연계정보
		String strCI2 = ""; // CI 연계정보2
		int len = 0;
		int id_len = 0;

		SocketClient sock = new SocketClient();

		try {
			if (jumin.length() == 13) {
				id_len = userId.length();

				if (id_len == 0) {
					strResult = "6";
				} else if (id_len == 6) {
					strSendBuf = userId + " " + docCode + jobCode + returnCode + code + jumin;
				} else if (id_len == 7) {
					strSendBuf = userId + docCode + jobCode + returnCode + code + jumin;
				} else {
					strResult = "6";
				}

				if (strSendBuf.length() > 0) {
					strRecvBuf = sock.SendWritePacket(host, port, timeout, strSendBuf);
					rtnText = strRecvBuf.substring(0, strRecvBuf.length());

					if (strRecvBuf.length() >= 31) {
						returnCode = strRecvBuf.substring(14, 17);

						if (returnCode.equals("000")) {
							// 방화벽이 안열린 경우
							if (strRecvBuf.toString().equals(strSendBuf + "5")) {
								throw new Exception("Connection Timeout");
							}
							if (jobCode.equals("297")) {
								strDI = strRecvBuf.substring(31, 95);
								strVer = strRecvBuf.substring(95, 96);
								strCI = strRecvBuf.substring(96, 184);
								strCI2 = strRecvBuf.substring(184, 272);
								strResult = "정상처리";
							} else {
								strResult = "업무 구분 코드 오류";
							}
						} else if (returnCode.equals("001")) {
							strResult = "사용자 ID 오류";
						} else if (returnCode.equals("002")) {
							strResult = "비밀번호 오류";
						} else if (returnCode.equals("003")) {
							strResult = "사용권한 없음";
						} else if (returnCode.equals("111")) {
							strResult = "서울신용평가정보 System 장애";
						} else if (returnCode.equals("112")) {
							strResult = "서울신용평가정보 DataBase 장애";
						} else if (returnCode.equals("113")) {
							strResult = "서울신용평가정보 DataBase 처리 실패";
						} else if (returnCode.equals("298")) {
							strResult = "타기관 아이핀번호 오류";
						} else if (returnCode.equals("299")) {
							strResult = "전문 Format Type Error";
						} else if (returnCode.equals("301")) {
							strResult = "전문 종별 코드 오류";
						} else if (returnCode.equals("302")) {
							strResult = "업무 구분 코드 오류";
						} else if (returnCode.equals("303")) {
							strResult = "응답 코드 오류";
						} else {
							strResult = "기타에러";
						}
					} else {
						strResult = "전문 응답길이 오류";
					}
				}
			} else {
				strResult = "전문 Format Type Error";
			}

			if (LOG.isDebugEnabled()) {
				LOG.debug("strSendBuf = " + strSendBuf);
				LOG.debug("rtnText = " + rtnText);
				LOG.debug("strResult = " + strResult);
			}
		} catch (Exception e) {
			strResult = e.getMessage();
		} finally {
			sock.Disconnect();
		}

		log(returnCode, strResult);

		if (LOG.isInfoEnabled()) {
			boolean success = "정상처리".equals(strResult);
			LOG.info("end. success = " + success);
		}

	}

	private void log(String returnCode, String result) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
		String DELIM = "|";
		String line = time;
		line += DELIM + ("000".equals(returnCode) && "정상처리".equals(result) ? "Y" : "N");
		line += DELIM + returnCode;
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
	 * @param userId
	 * @uml.property  name="userId"
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param host
	 * @uml.property  name="host"
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param port
	 * @uml.property  name="port"
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @param timeout
	 * @uml.property  name="timeout"
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @param docCode
	 * @uml.property  name="docCode"
	 */
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	/**
	 * @param jobCode
	 * @uml.property  name="jobCode"
	 */
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	/**
	 * @param returnCode
	 * @uml.property  name="returnCode"
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @param jumin
	 * @uml.property  name="jumin"
	 */
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	/**
	 * @param code
	 * @uml.property  name="code"
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
