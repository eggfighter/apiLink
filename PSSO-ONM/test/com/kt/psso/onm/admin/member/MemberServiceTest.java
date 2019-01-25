package com.kt.psso.onm.admin.member;


import org.junit.Before;
import org.junit.Test;

import com.kt.psso.onm.scheduler.hc.LineWriter;

public class MemberServiceTest {

	private MemberVo memberVo;
	private MemberService memberService;

	@Before
	public void setUp() throws Exception {
		// writer
		LineWriter onmUserWriter = new LineWriter();
		onmUserWriter.setBaseDir("C:/psso-logs/onmUser");
		onmUserWriter.setLogName("onmUser.log");
		
		// memberService
		memberService = new MemberService();
		memberService.setOnmUserWriter(onmUserWriter);
		
		// memberVo
		memberVo = new MemberVo();
		memberVo.setUserId("sdft35");
		memberVo.setUserId("ROLE_SUPERVISOR");
	}
	
	@Test
	public void testWriteOnmUserLog() {
		memberService.writeOnmUserLog(memberVo, "ADD");
	}

}
