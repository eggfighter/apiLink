package com.kt.psso.onm.scheduler.eventlog.parser;

import com.kt.psso.onm.scheduler.LogParser;
import com.kt.psso.onm.scheduler.LogParserException;
import com.kt.psso.onm.scheduler.eventlog.EventLogVo;

public class UpdateInsertLogParser implements LogParser {

	@Override
	public Object parse(String line) throws LogParserException {
		if (line == null || line.trim().equals("")) return null;

		//YYYYMMDDHHMMSS|아이디|E|변경필드|변경이전값|변경값|사용자가 수정|접속경로
		
		String[] split = line.split("\\|");
		if (split.length < 8) {
			throw new LogParserException("log format error - " + line);
		}
		
		String time			= split[0].trim();
		String userId		= split[1].trim();
		String operation	= split[2].trim();
		String fieldname	= split[3].trim();
		String before		= split[4].trim();
		String after		= split[5].trim();
		String editorId		= split[6].trim();
		String url 			= split[7].trim();

		EventLogVo eventLogVo = new EventLogVo();
		eventLogVo.setEditorId(editorId);
		eventLogVo.setUserId(userId);
		eventLogVo.setOperation(operation);
		eventLogVo.setFieldname(fieldname);
		eventLogVo.setBefore(before);
		eventLogVo.setAfter(after);
		eventLogVo.setReason("사용자가 수정");
		eventLogVo.setUrl(url);
		eventLogVo.setTime(time);
		eventLogVo.setIp("-");
		
		return eventLogVo;
	}

}
