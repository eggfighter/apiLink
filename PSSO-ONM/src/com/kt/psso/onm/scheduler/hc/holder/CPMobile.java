package com.kt.psso.onm.scheduler.hc.holder;

import CheckPlus.kisinfo.*;

public class CPMobile {

	public CPMobile() {

	}

	public static void main(String[] args) {

		String siteCode = ""; // CheckPlus 서비스 사이트 코드 (한국신용평가정보에서 발급한 사이트코드)

		String jumin = ""; // 주민등록번호
		String mobileCo = ""; // 이통사 구분 (SKT / KTF / LGT)
		String mobileNo = ""; // 휴대폰 번호

		/*
		 * ┌ sCPRequest 변수에 대한 설명
		 * ───────────────────────────────────────────────────── [CP 요청번호]로 귀사에서
		 * 데이타를 임의로 정의하시면 됩니다.
		 * 
		 * CP 요청번호는 인증 완료 후, 결과 데이타에 함께 제공되며 데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기
		 * 위한 목적으로 이용하실 수 있습니다.
		 * 
		 * 따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
		 * └────────────────────────────────────────────────────────────────────
		 */
		String sCPRequest = "TEST";

		// 객체 생성
		CheckPlusMobile cpMobile = new CheckPlusMobile();

		// Method 결과값(iRtn)에 따라, 프로세스 진행여부를 파악합니다.
		int iRtn = cpMobile.fnMobileAuth(siteCode, jumin, mobileCo,
				mobileNo, sCPRequest);

		// Method 결과값에 따른 처리사항
		if (iRtn == 0) {
			/*
			 * - 응답코드에 따라 SMS 인증 여부를 판단합니다.
			 * 
			 * - 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
			 */
			System.out.println("REQ_SEQ=" + cpMobile.getRequestSEQ()); // 요청
																		// 고유번호
			System.out.println("RES_SEQ=" + cpMobile.getResponseSEQ()); // 응답
																		// 고유번호
		} else if (iRtn == 1) {
			System.out.println("명의자 불일치입니다. [" + iRtn + "]");
		} else if (iRtn == -7 || iRtn == -8) {
			System.out
					.println("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			System.out.println("IP : 203.234.219.38 / Port : 3739");
		} else if (iRtn == -9 || iRtn == -10 || iRtn == 13) {
			System.out
					.println("입력값 오류 ["
							+ iRtn
							+ "] : fnRequest 함수 처리시, 필요한 5개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
		} else {
			System.out.println("iRtn 값 확인 후, 한국신용평가정보 개발 담당자에게 문의해 주세요. ["
					+ iRtn + "]");
		}
	}
}