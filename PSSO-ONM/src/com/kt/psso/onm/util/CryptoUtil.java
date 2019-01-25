package com.kt.psso.onm.util;

public interface CryptoUtil {

	String encryptData(String source);
	
	String decryptData(String encrypted) throws Exception;
	
	String hash(String source);

}
