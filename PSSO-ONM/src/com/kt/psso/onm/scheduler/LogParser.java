package com.kt.psso.onm.scheduler;


public interface LogParser {
	Object parse(String line) throws LogParserException;
}
