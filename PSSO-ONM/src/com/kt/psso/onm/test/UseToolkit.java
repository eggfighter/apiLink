package com.kt.psso.onm.test;

import	JKTFCrypto.*;

import java.io.File;
import	java.io.IOException;


public class UseToolkit
{
	public static void fileTest()
	{
		File file = new File("\\\\T-PSSO-1\\join_result\\Join_Result_20151223.log");
		

		if (!file.exists()) {
			System.out.println("not Exists");
		}else
		{
			System.out.println("Exists"+file.length());
		}
	}
	public static void main (String[] args) throws IOException
	{
		int nInput;
		UseToolkit	ToolkitSample = new UseToolkit ();
		fileTest();
		do
		{
			ToolkitSample.printMenu ();

			System.out.print ("Input Job Number : ");
			nInput = (int) System.in.read () - '0';
			while(System.in.read() != 0x0A);
			System.out.println();
			
			try
			{
				switch (nInput)
				{
					case 0:
						/* end */
						break;
					case 1:
						/* SSO en/decrypt */
						ToolkitSample.t_SSOLogin();
						break;
					case 2:
						/* DB en/decrypt */
						ToolkitSample.t_SSODB();
						break;
					case 3:
						/* HashEx Stress Test */
						ToolkitSample.t_HashExStressTest();
						break;
				default:
						System.out.println("Input character is undefined!");
						break;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace ();
				continue;
			}
			
			System.gc();
			
		} while (nInput != 0);
		
//		JKTFInstance.deleteInstance();
	}

	public void printMenu ()
	{
		System.out.println ("##### KTFCrypto Java API Sample #####");
		System.out.println ("\t#1. SSOLogin");
		System.out.println ("\t#2. SSODB");
		System.out.println ("\t#3. HashEx Stress Test");
		System.out.println ("\t#0. exit program");
	}
	
	public String getAlgoName (int algo)
	{
		switch (algo)
		{
			case 1:	return 	"SEED";
			case 2:	return 	"3DES";
			case 3:	return 	"DES";
			case 4:	return "AES128";
			case 256: return "SHA1";
			case 512: return "SHA256";
			default:	return "Unknown";
		}
	}
	
	public void t_SSOLogin()
	{
		int rv;
		
		// Symmetric Cipher
		rv = SSOLogin(JKTFCrypto.CIPHER_SEED_ALGO);
		if ( rv < 0 )	return;
		
		rv = SSOLogin(JKTFCrypto.CIPHER_AES_128);
		if ( rv < 0 )	return;
		
		rv = SSOLogin(JKTFCrypto.CIPHER_3DES_ALGO);
		if ( rv < 0 )	return;
		
		// DES �˰?�� �� DES ��� Logic �� ������.(hsw)
//		rv = SSOLogin(JKTFCrypto.CIPHER_DES_ALGO);
//		if ( rv < 0 )	return;
	}
	
	public int SSOLogin(int algo)
	{	
		JKTFCrypto Crypto;		
		try {
			Crypto = new JKTFCrypto ();
		} catch (JKTFException e) {
			System.out.println(e);
			return -1;
		}
		
		System.out.println("***** SSOLogin Algorithm : " + getAlgoName(algo) + " *****");
		
		// UserInfo
		String	UserPhoneNum = "01099998888";
		String  SiteInfo = "PSSO";
		String 	ResrgsNum = "00000001";
		String	UserPassword = "11111";
		
		// Hash
		byte [] pwd = UserPassword.getBytes();
		
		Crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA1); //Default
		String	sha1_pwd = Crypto.Hash(pwd);
		
		System.out.println("PlainData : " + UserPassword);
		System.out.println("HashedData(SHA1) : " + sha1_pwd);		
		
		Crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA256);
		String	sha256_pwd = Crypto.Hash(pwd);
		System.out.println("HashedData(SHA256) : " + sha256_pwd);
		System.out.println("Hash - OK");
		System.out.println();
		
		// 2012.04.25 added hsw - HashEx
		Crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA1); //Default
		String	sha1_pwdEx = Crypto.Hash(pwd);
		
		System.out.println("PlainData : " + UserPassword);
		System.out.println("HashedExData(SHA1) : " + sha1_pwdEx);		
		
		Crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA256);
		String	sha256_pwdEx = Crypto.Hash(pwd);
		System.out.println("HashedExData(SHA256) : " + sha256_pwdEx);
		System.out.println("HashEx - OK");
		System.out.println();
		
		// Create & Encrypt SessionKey
		String sdata = "KTFE_phonenum=" + UserPhoneNum + "&SSOE_siteinfo=" + SiteInfo ;
		sdata += "&KTFE_resrgstnum=" + ResrgsNum + "&KTFE_userhash=" + sha256_pwd;
		
		String keyid = Crypto.getKeyId();
		System.out.println("Keyid : " + keyid);
		
		String encSessionKey = Crypto.CreateSessionKey();
		if (Crypto.getErrorCode() < 0 || encSessionKey == null) {
			System.out.println("CreateSessionKey Failed : " + Crypto.getErrorCode());
			return -1;
		}
		System.out.println("Encrypted SessionKey :" + encSessionKey);
		System.out.println("CreateSessionKey - OK");
		System.out.println();
		
		// Encrypt Data
		Crypto.setCipherAlgorithm(algo);
		System.out.println("CipherAlgorithm : [" + algo + "] " + getAlgoName(algo));
		System.out.println("Input Data : " + sdata);
		
		byte [] btdata  = sdata.getBytes();
		String encdata = Crypto.EncryptData(btdata);
		if (Crypto.getErrorCode() < 0 || encdata == null){
			System.out.println("EncryptData Failed : " + Crypto.getErrorCode());
			return -1;
		}
		
		System.out.println("EncryptedData : " + encdata);
		System.out.println("EncryptData - OK");
		System.out.println();
		
		
		/////////////////////////////////////////////////////////////////////////
		// Decrypt SessionKey
		Crypto.DecryptSessionKey(encSessionKey);
		if (Crypto.getErrorCode() < 0){
			System.out.println("DecryptSessionKey Failed : " + Crypto.getErrorCode());
			return -1;
		}
		System.out.println("Decrypt SessionKey - OK");
		System.out.println();
		
		// Decrypt Data		
		byte[] decdata = Crypto.DecryptData(encdata);
		if (Crypto.getErrorCode() < 0 || decdata == null){
			System.out.println("DecryptData Failed : " + Crypto.getErrorCode());
			return -1;
		}
		System.out.println("DecryptedData : " + new String(decdata));
		System.out.println("DecryptData - OK");
		System.out.println();
		
		// modified by swhan
		// ���� �ڿ� ������ ���Ͽ� �ݵ�� ȣ��Ǿ�� �Ѵ�.
		//Crypto.release();
		
		return 0;
	}
	
	public void t_SSODB()
	{
		int rv;
		
		// Symmetric Cipher
		rv = SSODB(JKTFSymmetricKey.CIPHER_SEED_ALGO);
		if ( rv < 0 )	return;
		
		rv = SSODB(JKTFSymmetricKey.CIPHER_AES_128);
		if ( rv < 0 )	return;
		
		rv = SSODB(JKTFSymmetricKey.CIPHER_3DES_ALGO);
		if ( rv < 0 )	return;
		
		// DES �˰?�� �� DES ��� Logic �� ������.(hsw)
//		rv = SSODB(JKTFSymmetricKey.CIPHER_DES_ALGO);
//		if ( rv < 0 )	return;
	}
	
	public int SSODB( int algo )
	{
		JKTFSymmetricKey SymmKey;
		
		try {
			SymmKey = new JKTFSymmetricKey ();
		} catch (JKTFException e) {
			System.out.println(e);
			return -1;
		}
		
		System.out.println("***** SSODB Algorithm : " + getAlgoName(algo) + " *****");
		
		// Init SecretKey
		SymmKey.InitSecretKey();
		if ( SymmKey.getErrorCode() < 0 )
		{
			System.out.println("InitSecretKey Failed : " + SymmKey.getErrorCode());
			return -1;
		}
		System.out.println("InitSecretKey - OK");
		System.out.println();
		
		// Encrypt Data
		SymmKey.setCipherAlgorithm(algo);
		
		String src = "8205222094621";		
		//byte[] data = new byte[]{38,32,30,35,32,32,32,30,39,34,36,32,31};
		byte [] data = src.getBytes();
	
		String sdata = new String(data);
		System.out.println("Plain Data : " + sdata + " Length(byte) :" + data.length);
		String encdata = SymmKey.EncryptData(data);	
		if (SymmKey.getErrorCode() < 0 || encdata == null){
			System.out.println("EncryptData Failed : " + SymmKey.getErrorCode());
			return -1;
		}
		
		System.out.println("EncryptedData : " + encdata);
		System.out.println("EncryptData - OK");
		System.out.println();
		
		// Decrypt Data
		byte[] decdata = SymmKey.DecryptData(encdata);
		if (SymmKey.getErrorCode() < 0 || decdata == null){
			System.out.println("DecryptData Failed : " + SymmKey.getErrorCode());
			return -1;
		}
		
		System.out.println("DecryptedData : " + new String(decdata) + " Length(byte) :" + decdata.length);
		System.out.println("DecryptData - OK");
		System.out.println();
		
		// Hash
		String sha1_hashed = SymmKey.Hash(data);
		if ( SymmKey.getErrorCode() < 0 || sha1_hashed == null ) {
			System.out.println("Hash(SHA1) Failed : " + SymmKey.getErrorCode());
			return -1;
		}		
		System.out.println("HashedData(SHA1) : " + sha1_hashed);
		
		SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA256);
		String	sha256_hashed = SymmKey.Hash(data);
		if ( SymmKey.getErrorCode() < 0 || sha256_hashed == null ) {
			System.out.println("Hash(SHA256) Failed : " + SymmKey.getErrorCode());
			return -1;
		}		
		System.out.println("HashedData(SHA256) : " + sha256_hashed);

		System.out.println("Hash - OK");
		System.out.println();
		
		// HashEx
		SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA1);
		String sha1_hashExed = SymmKey.Hash(data);
		if ( SymmKey.getErrorCode() < 0 || sha1_hashed == null ) {
			System.out.println("HashEx(SHA1) Failed : " + SymmKey.getErrorCode());
			return -1;
		}		
		System.out.println("HashExedData(SHA1) : " + sha1_hashExed);
		
		SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA256);
		String	sha256_hashExed = SymmKey.Hash(data);
		if ( SymmKey.getErrorCode() < 0 || sha256_hashed == null ) {
			System.out.println("Hash(SHA256) Failed : " + SymmKey.getErrorCode());
			return -1;
		}		
		System.out.println("HashExedData(SHA256) : " + sha256_hashExed);

		System.out.println("Hash - OK");
		System.out.println();
		
		// modified by swhan
		// ���� �ڿ� ������ ���Ͽ� �ݵ�� ȣ��Ǿ�� �Ѵ�.
		//SymmKey.release();
		
		return 0;
	}
	
	public int t_HashExStressTest()
	{
		final int loopCount = 1003;
		
		// �׽�Ʈ�� ������ �� (�ʴ� ó�� ���� ������ �׽�Ʈ��)
		int dataLengthForProcessPerSecond = 10*1024*1024;	// 10MB
		byte[] dataForProcessPerSecond = new byte[dataLengthForProcessPerSecond]; 
		for(int i=0; i<dataForProcessPerSecond.length; i++){
			dataForProcessPerSecond[i] = (byte)i;
		}
		
		long start = 0;
		long end = 0;
		
		int errCount = 0;
		
		String src = "8205222094621";		
		byte [] data = src.getBytes();
		
		JKTFSymmetricKey SymmKey;
		
		try {
			SymmKey = new JKTFSymmetricKey ();
		} catch (JKTFException e) {
			System.out.println(e);
			return -1;
		}
		
		start = System.currentTimeMillis();
		
		// ������ �׽�Ʈ (SHA1)
		for(int i=0; i<loopCount; i++){
			// HashEx
			SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA1);
			String sha1_hashExed = SymmKey.Hash(data);
			if ( SymmKey.getErrorCode() < 0 || sha1_hashExed == null ) {
				errCount++;
				continue;
			}
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("HashEx Error Rate Result (SHA1) : " + (end - start) + "ms, error count : " + errCount);
		
		// �ʴ� ó�� ���� �׽�Ʈ(SHA1)
		start = 0;
		end = 0;		
		errCount = 0;
		
		start = System.currentTimeMillis();
		SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA1);
		String sha1_hashExed = SymmKey.Hash(dataForProcessPerSecond);
		if ( SymmKey.getErrorCode() < 0 || sha1_hashExed == null ) {
			System.out.println("Error occured to HashEx Process Per Second.");			
		}else{
			end = System.currentTimeMillis();
			long totalTime = (end-start) * 1000; // calculate to second
			long processPerSecond = dataLengthForProcessPerSecond / totalTime;
			System.out.println("HashEx Process Per Second Result (SHA1) : " + processPerSecond + " MB/s");
		}
		
		// ������ �׽�Ʈ (SHA256)
		start = 0;
		end = 0;		
		errCount = 0;
		
		start = System.currentTimeMillis();
		
		for(int i=0; i<loopCount; i++){
			
			SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA256);
			String	sha256_hashExed = SymmKey.Hash(data);
			if ( SymmKey.getErrorCode() < 0 || sha256_hashExed == null ) {
				errCount++;
				continue;
			}
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("HashEx Result (SHA256) : " + (end - start) + "ms, error count : " + errCount);
		
		// �ʴ� ó�� ���� �׽�Ʈ(SHA256)
		start = 0;
		end = 0;		
		errCount = 0;
		
		start = System.currentTimeMillis();
		SymmKey.setHashAlgorithm(JKTFSymmetricKey.HASH_SHA256);
		String sha256_hashExed = SymmKey.Hash(dataForProcessPerSecond);
		if ( SymmKey.getErrorCode() < 0 || sha256_hashExed == null ) {
			System.out.println("Error occured to HashEx Process Per Second.");			
		}else{
			end = System.currentTimeMillis();
			long totalTime = (end-start) * 1000; // calculate to second
			long processPerSecond = dataLengthForProcessPerSecond / totalTime;
			System.out.println("HashEx Process Per Second Result (SHA256) : " + processPerSecond + " MB/s");
		}
		
		// modified by swhan
		// ���� �ڿ� ������ ���Ͽ� �ݵ�� ȣ��Ǿ�� �Ѵ�.
		//SymmKey.release();
		
		return 0;
	}
}




