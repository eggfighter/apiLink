package com.kt.psso.onm.admin.connstat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HealthLineReader {

	public ArrayList<String[]> readLines(String baseDir, String logName) throws IOException {
		if (baseDir == null) throw new IllegalArgumentException("baseDir is null");
		if (logName == null) throw new IllegalArgumentException("logName is null");

		File dir = new File(baseDir);
		if (!dir.exists()) dir.mkdirs();

		File file = new File(dir, logName);
		if (!file.exists()) {
			return new ArrayList<String[]>();
		}

		BufferedReader reader = null;

		ArrayList<String[]> list = new ArrayList<String[]>();
		String line = null;
		String[] split = null;
		String yn = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				split = line.split("\\|");
				if (yn == null) {
					list.add(0, split);
					yn = split[1];
				} else if (!yn.equals(split[1])) {
					list.add(0, split);
					yn = split[1];
				}
			}
			
		} finally {
			if (reader != null) try { reader.close(); } catch(Exception e) {}
		}

		return list;
	}

}
