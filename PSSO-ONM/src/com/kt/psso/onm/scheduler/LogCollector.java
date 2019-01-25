package com.kt.psso.onm.scheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;

public class LogCollector {
	
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * 로그파일이 있는 디렉토리
	 * @uml.property  name="baseDir"
	 */
	private String baseDir;
	/**
	 * 로그파일명
	 * @uml.property  name="logName"
	 */
	private String logName;
	/**
	 * 로그파일 백업 위치
	 * @uml.property  name="backupDir"
	 */
	private String backupDir;
	/**
	 * 로그파일 캐릭터셋
	 * @uml.property  name="charsetName"
	 */
	private String charsetName = "EUC_KR";
	/**
	 * 로그파일의 각 라인을 읽어서 logDataProcessors 들에게 처리 위임
	 * @uml.property  name="lineCollector"
	 * @uml.associationEnd  
	 */
	private LineCollector lineCollector;
	/**
	 * 로그파일 라인 처리 담당
	 * @uml.property  name="logDataProcessors"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private LogDataProcessor[] logDataProcessors;
	
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

	/**
	 * @param backupDir
	 * @uml.property  name="backupDir"
	 */
	public void setBackupDir(String backupDir) {
		this.backupDir = backupDir;
	}

	/**
	 * @param charsetName
	 * @uml.property  name="charsetName"
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	/**
	 * @param lineCollector
	 * @uml.property  name="lineCollector"
	 */
	public void setLineCollector(LineCollector lineCollector) {
		this.lineCollector = lineCollector;
	}

	/**
	 * @param logDataProcessors
	 * @uml.property  name="logDataProcessors"
	 */
	public void setLogDataProcessors(LogDataProcessor[] logDataProcessors) {
		this.logDataProcessors = logDataProcessors;
	}

	public void collect(long offset) {
		long time = System.currentTimeMillis() + (offset * 3600000L);
		String fileName = new SimpleDateFormat(logName).format(time);

		File file = new File(baseDir + "/" + fileName);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("enter, file = " + file);
		}

		if (!file.exists()) {
			if (LOG.isInfoEnabled()) {
				LOG.info("file not found, file = " + file);
			}
			return;
		}

		BufferedReader reader = null;
		try {
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
				
				// collect lines
				lineCollector.collectLines(reader, logDataProcessors);
			} finally {
				if (reader != null) try { reader.close(); } catch (Exception e) {}
			}
			
			// backup
			if (backupDir == null) {
				if (LOG.isInfoEnabled()) {
					LOG.info("backupDir is null, backup skipping, file = " + file);
				}
				return;
			}
			boolean backupFlag = false;
			File dir = new File(backupDir + new SimpleDateFormat("'/'yyyy-MM'/'").format(time));
			if (!dir.exists()) dir.mkdirs();
			
			File backupFile = new File(dir, fileName);
			int copiedBytes = FileCopyUtils.copy(file, backupFile);
			if (copiedBytes > 0) {
				if (file.delete()) {
					backupFlag = true;
				}
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("backup " + (backupFlag ? "success" : "fail") + ", file = " + backupFile);
			}
			
		} catch (IOException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		} catch (LogParserException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		} catch (RuntimeException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		}					
		
	}
	
}
