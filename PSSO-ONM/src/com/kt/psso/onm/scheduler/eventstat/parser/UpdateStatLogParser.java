package com.kt.psso.onm.scheduler.eventstat.parser;

import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;
import com.kt.psso.onm.scheduler.eventstat.EventStatVo;

public class UpdateStatLogParser implements LogParser {

	@Override
	public Object parse(String line) throws LogParserException {
		if (line == null || line.trim().equals("")) return null;

		//YYYYMMDDHHMMSS|아이디|E|변경필드|변경이전값|변경값|사용자가 수정|접속경로
		
		String[] split = line.split("\\|");
		if (split.length < 8) {
			throw new LogParserException("log format error - " + line);
		}
		
		String timeslice = split[0].trim();
		timeslice = timeslice.substring(0, 10);
		String domain = split[7].trim();
		domain = domain.replace("http://", "")
					   .replace("https://", "");
		if (domain.contains("/")) {
			domain = domain.substring(0, domain.indexOf("/"));
		}
		
		return new EventStatVo("UPDATE", timeslice, domain);
	}
	
}
