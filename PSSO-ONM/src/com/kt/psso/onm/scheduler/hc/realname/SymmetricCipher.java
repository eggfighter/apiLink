//Title:        KAIT Socket Programming
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Seung Sang Sun
//Company:      NGL Communication
//Description:  e-mail : threes@suwon-c.ac.kr

package com.kt.psso.onm.scheduler.hc.realname;

import java.security.*;

public class SymmetricCipher
{
	public static byte[] SEED_CBC_ENCRYPT(byte[] encryptStr, byte[] key)
	{
		byte[] encryptText = null;

	  try {
      byte plainText[] = encryptStr;

	    BlockCipher blockCipher = new SEED();
	    blockCipher.engineSetMode("CBC");
	    blockCipher.engineSetPadding("PKCS5Padding");

               int length = key.length;
                for(int i = 0; i < length; i++)
                        key[i] ^= key[length - i - 1];



	    blockCipher.engineInit(BlockCipher.ENCRYPT_MODE, key, new SecureRandom());
	    //System.out.println("input message (string) : " + new String(plainText));

	    // Encrypt the message
	    encryptText = blockCipher.engineDoFinal(plainText, 0, plainText.length);
	    //debug("The Encrypted message(hexadecimal): ", encryptText);
		}
	  catch(Exception e) {
	    e.printStackTrace();
	  }

    return encryptText;
	}

  public static byte[] SEED_CBC_DECRYPT(byte[] decryptBytes, byte[] key)
  {
		byte[] plainText = null;

    // initialize the Cipher in decryption mode
    try {
    	byte encryptText[] = decryptBytes;

      BlockCipher blockCipher = new SEED();
      blockCipher.engineSetMode("CBC");
      blockCipher.engineSetPadding("PKCS5Padding");

               int length = key.length;
                for(int i = 0; i < length; i++)
                        key[i] ^= key[length - i - 1];

      blockCipher.engineInit(BlockCipher.DECRYPT_MODE, key, new SecureRandom());
      //debug("The inputed Message(hexadecimal): ", encryptText);

      //Decrypt the message
      plainText = blockCipher.engineDoFinal(encryptText, 0, encryptText.length);
      //debug("The Decrypted message: ", plainText);
//      System.out.println("decrypt message " + new String(plainText));
    }
    catch(Exception e) {
    	e.printStackTrace();
    }

    return plainText;
	}

  public static byte[] SEED_ECB_ENCRYPT(byte[] encryptStr)
  {
    byte[] encryptText = null;

    try {
//      byte plainText[] = encryptStr.getBytes();
      byte plainText[] = encryptStr;

      BlockCipher blockCipher = new SEED();
      blockCipher.engineSetMode("ECB");
      blockCipher.engineSetPadding("PKCS5Padding");

      byte[] key = new byte[16];
      key = blockCipher.getKey();

      blockCipher.engineInit(BlockCipher.ENCRYPT_MODE, key, new SecureRandom());
      debug("The inputed Message(hexadecimal): ", plainText);
      //System.out.println("input message (string) : " + new String(plainText));

      // Encrypt the message
      encryptText = blockCipher.engineDoFinal(plainText, 0, plainText.length);
      debug("The Encrypted message(hexadecimal): ", encryptText);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return encryptText;
  }

  public static byte[] SEED_ECB_DECRYPT(byte[] decryptBytes)
  {
    byte[] plainText = null;

    // initialize the Cipher in decryption mode
    try {
      byte encryptText[] = decryptBytes;

      BlockCipher blockCipher = new SEED();
      blockCipher.engineSetMode("ECB");
      blockCipher.engineSetPadding("PKCS5Padding");

      byte[] key = new byte[16];
      key = blockCipher.getKey();

      blockCipher.engineInit(BlockCipher.DECRYPT_MODE, key, new SecureRandom());
      debug("The inputed Message(hexadecimal): ", encryptText);

      //Decrypt the message
      plainText = blockCipher.engineDoFinal(encryptText, 0, encryptText.length);
      debug("The Decrypted message: ", plainText);
//      System.out.println("decrypt message " + new String(plainText));
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return plainText;
  }

	protected static void debug(String msg, byte bytes[])
	{
		System.out.print(msg);
		for(int i = 0; i < bytes.length; i++)
			if((bytes[i] > 15) || (bytes[i] < 0))
				System.out.print(java.lang.Integer.toHexString(bytes[i] & 0xff) + " ");
			else
				System.out.print("0"+java.lang.Integer.toHexString(bytes[i] & 0xff) + " ");

		System.out.println(" ");
	}
} 