package com.kt.psso.onm.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropertiesConfigurationTest {
	
	private static PropertiesConfiguration config;
	
	@BeforeClass
	public static void setUpBeforeClass() throws ConfigurationException {
		File file = new File("build/psso.properties");
		if (file.exists()) {
			file.delete();
		}
		config = new PropertiesConfiguration(file);
		config.addProperty("test.something", "Some Value");
		config.save();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		File file = new File("build/psso.properties");
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	public void something() {
		String string = config.getString("test.something");
		assertEquals("Some Value", string);
	}
	
	@Test
	public void another() {
		config.addProperty("test.another", "Another Value");
		String another = (String) config.getProperty("test.another");
		assertEquals("Another Value", another);
	}
	
	@Test
	public void isOtherExists() throws ConfigurationException {
		config.setAutoSave(true);
		config.addProperty("test.other", "Other Value");
		
		File file = new File("build/psso.properties");
		PropertiesConfiguration newConfig = new PropertiesConfiguration(file);
		String other = (String) newConfig.getProperty("test.other");
		assertNotNull(other);
		assertEquals("Other Value", other);
	}
}
