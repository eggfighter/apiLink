package com.kt.psso.onm.scheduler.hc;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kt.psso.onm.scheduler.hc.realname.KaitClient;

public class RealnameHealthChecker {
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());

	/**
	 * @uml.property  name="writer"
	 * @uml.associationEnd  
	 */
	private LineWriter writer;

	/**
	 * @uml.property  name="host"
	 */
	private String host;
	/**
	 * @uml.property  name="port"
	 */
	private int port;
	/**
	 * @uml.property  name="key"
	 */
	private String key;
	/**
	 * @uml.property  name="message"
	 */
	private String message;

	public void check() {
		if (LOG.isInfoEnabled()) {
			LOG.info("begin");
		}

		KaitClient client = new KaitClient(host, port, key, message);
		boolean success = client.run();

		log(success);

		if (LOG.isInfoEnabled()) {
			LOG.info("end. success = " + success);
		}

	}

	private void log(boolean success) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
		String DELIM = "|";
		String line = time + DELIM + (success ? "Y" : "N") + "\n";

		try {
			writer.write(line);
		} catch (IOException e) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(e.getMessage(), e);
			}
		}
	}

	//-------------------- Getter & Setter --------------------//

	/**
	 * @param writer
	 * @uml.property  name="writer"
	 */
	public void setWriter(LineWriter writer) {
		this.writer = writer;
	}

	/**
	 * @param host
	 * @uml.property  name="host"
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param port
	 * @uml.property  name="port"
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param key
	 * @uml.property  name="key"
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param message
	 * @uml.property  name="message"
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
