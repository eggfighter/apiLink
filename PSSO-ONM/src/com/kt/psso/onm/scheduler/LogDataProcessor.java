package com.kt.psso.onm.scheduler;

public interface LogDataProcessor {
	void init();
	void add(String line) throws LogParserException;
	void save();
}
