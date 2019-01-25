package com.kt.psso.onm.admin.connstat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.psso.onm.admin.config.OnmConfig;

@Service
public class ConnStatService {

	/**
	 * @uml.property  name="onmConfig"
	 * @uml.associationEnd  
	 */
	@Autowired
	private OnmConfig onmConfig;

	/**
	 * @uml.property  name="lineReader"
	 * @uml.associationEnd  
	 */
	private HealthLineReader lineReader = new HealthLineReader();

	public List<String[]> listRealname() throws IOException {
		String baseDir = onmConfig.map().get("baseDir.healthcheck");
		String logName = onmConfig.map().get("logName.realname");
		ArrayList<String[]> list = lineReader.readLines(baseDir, logName);
		return formatDate(list);
	}

	public List<String[]> listIpin() throws IOException {
		String baseDir = onmConfig.map().get("baseDir.healthcheck");
		String logName = onmConfig.map().get("logName.ipin");
		ArrayList<String[]> list = lineReader.readLines(baseDir, logName);
		return formatDate(list);
	}

	public List<String[]> listHolder() throws IOException {
		String baseDir = onmConfig.map().get("baseDir.healthcheck");
		String logName = onmConfig.map().get("logName.holder");
		ArrayList<String[]> list = lineReader.readLines(baseDir, logName);
		return formatDate(list);
	}

	private ArrayList<String[]> formatDate(ArrayList<String[]> list) {
		for (String[] array : list) {
			array[0] = array[0].substring(0, 4) + "-"
					 + array[0].substring(4, 6) + "-"
					 + array[0].substring(6, 8) + " "
					 + array[0].substring(8, 10) + ":"
					 + array[0].substring(10, 12) + ":"
					 + array[0].substring(12);
		}
		return list;
	}

}
