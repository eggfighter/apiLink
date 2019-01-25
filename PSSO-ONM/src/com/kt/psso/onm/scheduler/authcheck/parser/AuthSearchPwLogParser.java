package com.kt.psso.onm.scheduler.authcheck.parser;

import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;
import com.kt.psso.onm.scheduler.authcheck.AuthCheckVo;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class AuthSearchPwLogParser implements LogParser {

	/*2014-07-30 수정*/
	private String eventType;
	
	
	@Override
	public Object parse(String line) throws LogParserException {
		if (line == null || line.trim().equals("")) return null;

		//*1|인증방식|YYYYMMDDHHMMSS|접속IP|로그인아이디|로그인아이디소유자이름|접속경로
		
		String[] split = line.split("\\|");
		if (split.length < 3) {
			throw new LogParserException("log format error - " + line);
		}
		//String eventType ="";
		String timeslice = split[0].trim();		
		eventType = split[1].trim();
		String cn = split[2].trim();
		
		if(eventType.equals("M"))
		{
			eventType = "핸드폰";
		}
		else {
			eventType = "이메일";
		}
		return new AuthCheckVo(eventType, timeslice, cn);
	}
	
}
