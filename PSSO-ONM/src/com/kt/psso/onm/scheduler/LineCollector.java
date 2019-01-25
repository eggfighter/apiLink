package com.kt.psso.onm.scheduler;

import java.io.BufferedReader;
import java.io.IOException;

public interface LineCollector {
	
	void collectLines(BufferedReader reader, LogDataProcessor[] logDataProcessors) throws IOException, LogParserException;
}
