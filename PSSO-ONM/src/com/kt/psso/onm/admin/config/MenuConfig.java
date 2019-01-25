package com.kt.psso.onm.admin.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public class MenuConfig {
	/**
	 * @uml.property  name="lOG"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Log LOG = LogFactory.getLog(getClass());
	
	/**
	 * @uml.property  name="config"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	private PropertiesConfiguration config;

	public void setLocations(Resource[] locations) {
		for (Resource location : locations) {
			try {
				this.config = new PropertiesConfiguration();
				this.config.setEncoding("UTF-8");
				this.config.load(location.getFile());
			} catch (ConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		logConfig();
	}
	
	private void logConfig() {
		if (LOG.isInfoEnabled()) {
			LOG.info("MenuConfig ============================================================");
			LOG.info("    Path = " + this.config.getPath());
			Iterator<?> keys = this.config.getKeys();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				LOG.info("    " + key + " = " + this.config.getString(key));
			}
		}
	}
	
	public Map<String,String> map() {
		Iterator<?> keys = this.config.getKeys();
		HashMap<String, String> map = new HashMap<String, String>();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String val = this.config.getString(key);
			map.put(key, val);
		}
		return map;
	}
	public String getMenu1(String key) {
		return this.config.getString(key + ".menu1");
	}
	
	public String getNavi(String key) {
		return this.config.getString(key + ".navi");
	}

}
