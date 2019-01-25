package com.kt.psso.onm.scheduler.hc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LineWriter {

	/**
	 * @uml.property  name="baseDir"
	 */
	private String baseDir;
	/**
	 * @uml.property  name="logName"
	 */
	private String logName;

	public void write(String line) throws IOException {
		File dir = new File(baseDir);
		if (!dir.exists()) dir.mkdirs();

		File file = new File(dir, logName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(line);
			
		} finally {
			if (writer != null) try { writer.close(); } catch(Exception e) {}
		}

	}

	/**
	 * @param baseDir
	 * @uml.property  name="baseDir"
	 */
	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	/**
	 * @param logName
	 * @uml.property  name="logName"
	 */
	public void setLogName(String logName) {
		this.logName = logName;
	}

}
