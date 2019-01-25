package com.kt.psso.onm.scheduler.eventstat.parser;

import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class JoinLogParser implements LogParser {

	@Override
	public Object parse(String line) throws LogParserException {
		if (line == null || line.trim().equals("")) return null;

		//YYYYMMDDHHMMSS|아이디|접속경로|가입방법(I : 아이핀가입자, 그외 주민번호가입자)
		
		String[] split = line.split("\\|");
		if (split.length < 3) {
			throw new LogParserException("log format error - " + line);
		}
		
		String timeslice = split[0].trim();
		timeslice = timeslice.substring(0, 10);
		String domain = split[2].trim();
		domain = domain.replace("http://", "")
					   .replace("https://", "");
		if (domain.contains("/")) {
			domain = domain.substring(0, domain.indexOf("/"));
		}
		
		return new EventStatVo("JOIN", timeslice, domain);
	}
	
}
