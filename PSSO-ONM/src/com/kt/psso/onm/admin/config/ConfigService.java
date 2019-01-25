package com.kt.psso.onm.admin.config;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfigService {
	
	/**
	 * @uml.property  name="onmConfig"
	 * @uml.associationEnd  
	 */
	@Autowired
	private OnmConfig onmConfig;
	
	/**
	 * @uml.property  name="menuConfig"
	 * @uml.associationEnd  
	 */
	@Autowired
	private MenuConfig menuConfig;

	public String[] onmKeys() {
		String[] keys = this.onmConfig.map().keySet().toArray(new String[0]);
		Arrays.sort(keys);
		return keys;
	}
	
	public Map<String, String> onmMap() {
		return this.onmConfig.map();
	}
	
	public String[] menuKeys() {
		String[] keys = this.menuConfig.map().keySet().toArray(new String[0]);
		Arrays.sort(keys);
		return keys;
	}

	public Map<String, String> menuMap() {
		return this.menuConfig.map();
	}

}
