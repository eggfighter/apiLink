package com.kt.psso.onm.scheduler.lastlog;

import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;
import com.kt.psso.onm.customer.CustomerVo;

public class LastLogParser implements LogParser {

	@Override
	public Object parse(String line) throws LogParserException {
		if (line == null || line.trim().equals("")) return null;

		//*1|YYYYMMDDHHMMSS|접속IP|로그인아이디|로그인아이디소유자이름|접속경로
		
		String[] split = line.split("\\|");
		if (split.length < 6) {
			throw new LogParserException("log format error - " + line);
		}
		
		String timeslice = split[1].trim();
		String domain = split[5].trim();
		domain = domain.replace("http://", "")
					   .replace("https://", "");
		if (domain.contains("/")) {
			domain = domain.substring(0, domain.indexOf("/"));
			if(domain.length() > 32){
				domain = domain.substring(0,32);
			}
		}
		String userId = split[3].trim();
		
		return new CustomerVo(userId, timeslice, domain);
	}
	
}