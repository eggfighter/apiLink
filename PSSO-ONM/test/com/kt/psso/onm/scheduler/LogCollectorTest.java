package com.kt.psso.onm.scheduler;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/kt/psso/onm/scheduler/scheduler-context-test.xml"})
public class LogCollectorTest {

	@Autowired
	private LogCollector loginLogCollector1;
	@Autowired
	private LogCollector lastLogCollector1;
	@Autowired
	private LogCollector authCertificateCollector1;


	@Test
	public void testLoginLogCollector() {
		loginLogCollector1.collect(-24);
		assertTrue(true);
	}
	
	@Test
	public void testLastLogCollector() {
		lastLogCollector1.collect(-24);
		assertTrue(true);
	}
	
	@Test
	public void testAuthCertificateLogCollector() {
		authCertificateCollector1.collect(-24);
		assertTrue(true);
	}

}
