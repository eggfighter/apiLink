package com.kt.psso.onm.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KTFCryptoUtilTest {
	
	/**
	 * @uml.property  name="cryptoUtil"
	 * @uml.associationEnd  
	 */
	private KTFCryptoUtil cryptoUtil;
	private static String encrypted=null;
	@Before
	public void setUp() throws Exception {
		this.cryptoUtil = new KTFCryptoUtil();
		
	}

	@Test
	public void encryptData() {
		String str = "jdbc:sqlserver://172.31.34.232:1433;DatabaseName=PSSO";
		/*jdbc:sqlserver://192.168.151.97:1433;DatabaseName=PSSO/ PSSO / PSSODBPASS$*/
		/*jdbc:sqlserver://125.131.85.68:1433;DatabaseName=PSSO/ PSSO / PSSODBPASS$*/
		encrypted = this.cryptoUtil.encryptData(str);
		//System.out.println(encrypted);
//		assertEquals("kJYUbl6GCPfllG/F0Kj/+g==", encrypted);
		
		/*str = "";
		encrypted = this.cryptoUtil.encryptData(str);*/
		System.out.println("str:"+str+"&encrypted:"+encrypted);
//		assertEquals("wvsisaX8W7+zd7B76EPK5g==", encrypted);
		/*str = "123qwe";
		encrypted = this.cryptoUtil.encryptData(str);
		System.out.println(encrypted);*/
	
		/*String str = "e+qg4UmXewskgGtzV3QgDw==";
		String encrypted = this.cryptoUtil.encryptData(str);
		System.out.println(encrypted);*/

	}
	
	@Test
	public void decryptData() throws Exception {
		String str = "imnJxk4r3wgGEPU6dQ9zql94jRb2rnsO3bo2U+TjCbGKh+YQzOFYMiJ8o8KnGaNYh8CjN6MbAmwZIv8/j7L52A==";
		String decrypted = this.cryptoUtil.decryptData(str);
	    System.out.println("decrypted:"+decrypted);
		//assertEquals("1234", decrypted);
	}
	/*
	@Test(expected=Exception.class)
	public void decryptError() throws Exception {
		String str = "1111111111111";
		this.cryptoUtil.decryptData(str);
	}
	*/
	@Test
	public void hash() {
		String str = "123qwe";
		//String str1 = "herit9600@";
		//String str2 = "PRJVMIX";
		String hash1;
		//String hash2;
		//String hash3;
		
		
		// SHA1
		this.cryptoUtil.setHashAlgorithm(0x0200);
		hash1 = this.cryptoUtil.hash(str);
		//hash2 = this.cryptoUtil.hash(str1);
		//hash3 = this.cryptoUtil.hash(str2);
		System.out.println(hash1);
		//System.out.println(hash2);
		//System.out.println(hash3);
		
		//assertEquals("cRDtpNCeBiql5KOQsKVyrA0sAiA=", hash1);
		
		// SHA256
		/*this.cryptoUtil.setHashAlgorithm(0x0200);
		hash2 = this.cryptoUtil.hash(str);
		assertEquals("A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=", hash2);
		assertEquals(44, hash2.length());*/
	}
}
