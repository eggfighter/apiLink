package com.kt.psso.onm.scheduler;

import java.io.BufferedReader;
import java.io.IOException;

public class LineCollectorImpl implements LineCollector {

	@Override
	public void collectLines(BufferedReader reader, LogDataProcessor[] logDataProcessors) throws IOException, LogParserException {
		// init logDataProcessors
		for (LogDataProcessor logDataProcessor : logDataProcessors) {
			logDataProcessor.init();
		}
		// add lines
		String line = null;
		while ((line = reader.readLine()) != null) {
			for (LogDataProcessor logDataProcessor : logDataProcessors) {
				logDataProcessor.add(line);
			}
		}
		// save
		for (LogDataProcessor logDataProcessor : logDataProcessors) {
			logDataProcessor.save();
		}
	}

}
