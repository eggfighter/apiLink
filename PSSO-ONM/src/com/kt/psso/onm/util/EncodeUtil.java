package com.kt.psso.onm.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * ---------------------------------------------------------Project<br>
 * Project 		: e-HRD<br>
 * System Name  : cutil
 * File Name    : EncodeUtil.java<br>
 * Creater		: keegun<br>
 * Create Date	: 2004-04-19<br>
 * <br>
 * ---------------------------------------------------------Modify<br>
 * Modify Date	: Modifier : Modify Contents<br>
 * 2004-04-19	: keegun   : create<br>
 * ...<br>
 * <br>
 * ---------------------------------------------------------Description<br>
 * Description	: Encode 공통 모듈<br>
 * <br>
 * ---------------------------------------------------------JavaDocs<br>
 * @author <br>
 * @version v1.0<br>
 */
public class EncodeUtil
{
	/**
	 * HTML 에 있는 태그문자(' <','>')들을 HTML 코드문자로 변환
	 * 
	 * @param sHTML
	 * @return String : HTML 코드문자
	 * @throws Exception
	 */
	public static String stringEncode(String sHTML) throws Exception
	{
		if (sHTML == null)
			return null;
		String s, sResult = "";
		for (int i = 0; i < sHTML.length(); i++)
		{
			s = sHTML.substring(i, i + 1);
			if (s.equals("<"))
			{
				sResult += "&lt;";
			}
			else if (s.equals(">"))
			{
				sResult += "&gt;";
			}
			else if (s.equals("&"))
			{
				sResult += "&amp;";
			}
			else
			{
				sResult += s;
			}
		}
		return sResult;
	}

	/**
	 * 문자코드 변환 ( 8859_1 --> KSC5601 )
	 * 
	 * @param str : 변환할 문자열
	 * @return String : 변환된 문자열
	 * @throws Exception
	 */
	public static String asc2ksc(String str) throws Exception
	{
		String new_str = null;

		if (str == null)
			new_str = "";
		else
			new_str = new String(str.getBytes("8859_1"), "KSC5601");

		return new_str;
	}

	/**
	 * String 배열의 문자코드 변환 ( 8859_1 --> KSC5601 )
	 * 
	 * @param str : 변환할 String 배열
	 * @return String[] : 변환된 String 배열
	 * @throws Exception
	*/
	public static String[] asc2ksc(String[] str) throws Exception
	{
		String[] new_str = null;

		new_str = new String[str.length];
		for (int i = 0; i < str.length; i++)
			new_str[i] = asc2ksc(str[i]);

		return new_str;
	}

	/**
	 * String의 문자코드 변환 ( KSC5601 --> 8859_1 )
	 * 
	 * @param str : 변환할 String
	 * @return String : 변환된 String
	 * @throws Exception
	*/
	public static String ksc2asc(String str) throws Exception
	{
		String new_str = null;

		new_str = new String(str.getBytes("KSC5601"), "8859_1");

		return new_str;
	}

	/**
	 * String 배열의 문자코드 변환 ( KSC5601 --> 8859_1 )
	 * 
	 * @param str : 변환할 String 배열
	 * @return String[] : 변환된 String 배열
	 * @throws Exception
	*/
	public static String[] ksc2asc(String[] str) throws Exception
	{
		String[] new_str = null;

		for (int i = 0; i < str.length; i++)
			new_str[i] = ksc2asc(str[i]);

		return new_str;
	}

	/**
	 * String의 문자코드 변환 ( 8859_1 --> UTF8 )
	 * 
	 * @param str : 변환할 String
	 * @return String : 변환된 String
	 * @throws Exception
	*/
	public static String asc2utf8(String str) throws Exception
	{
		String new_str = null;

		if (str == null)
			new_str = "";
		else
			new_str = new String(str.getBytes("8859_1"), "UTF8");

		return new_str;
	}

	/**
	 * String 배열의 문자코드 변환 ( 8859_1 --> UTF8 )
	 * 
	 * @param str : 변환할 String 배열
	 * @return String [] : 변환된 String 배열
	 * @throws Exception
	*/
	public static String[] asc2utf8(String[] str) throws Exception
	{
		String[] new_str = null;

		new_str = new String[str.length];
		for (int i = 0; i < str.length; i++)
			new_str[i] = asc2ksc(str[i]);

		return new_str;
	}

	/**
	 * Returns the default number format for the specified locale
	 * 
	 * @param number
	 * @return String format(number)
	 * @throws Exception
	 */
	public static String toCurrencyFormat(long number) throws Exception
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);

		if (number == 0)
			return null;
		return nf.format(number);
	}

	/**
	 * Returns the default number format for the specified locale
	 * 
	 * @param number
	 * @return String nf.format(nf.parseObject(number))
	 * @throws Exception
	 */
	public static String toCurrencyFormat(String number) throws Exception
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);

		if (number.equals("0"))
			return null;
		return nf.format(nf.parseObject(number));
	}

	/**
	 * Returns the default number format for the specified locale
	 * 
	 * @param number
	 * @return String nf.format(number)
	 * @throws Exception
	 */
	public static String toCurrencyFormat(double number) throws Exception
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);

		if (number == 0)
			return null;
		return nf.format(number);
	}

	/**
	 * Returns the default number format for the specified locale
	 * 
	 * @param currency
	 * @return long  nf.parse(currency).longValue()
	 * @throws Exception
	 */
	public static long toNumberFormat(String currency) throws Exception
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);

		if (currency == null)
			return 0;
		return nf.parse(currency).longValue();
	}

	/**
	 * Returns the default number format for the specified locale
	 * 
	 * @param currency
	 * @return double  nf.parse(currency).doubleValue()
	 * @throws Exception
	 */
	public static double toDoubleNumberFormat(String currency) throws Exception
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);

		if (currency == null)
			return 0;
		return nf.parse(currency).doubleValue();
	}

	/**
	 * JSP에서 한글깨짐 변환 함수
	 * 
	 * @param ps_str : 한글 변환할 Data
	 * @return String : 한글 변환된 Data
	 * @throws Exception
	 */
	public static String setKorLng(String ps_str) throws Exception
	{

		if (ps_str == null)
			return "";
		else
			return new String(ps_str.getBytes("ISO-8859-1"), "UTF8");
	}

	/**
	 * URL encoding을 한다
	 * 
	 * @param pStr : 대상 String
	 * @return String : URL encoding된 String
	 * @throws Exception
	 */
	public static String urlEncode(String pStr) throws Exception
	{
		if (pStr == null)
			return "";
		else
			return URLEncoder.encode(pStr, "utf-8");
	}

	/**
	 * URL decoding을 한다
	 * 
	 * @param pStr : 대상 String
	 * @return String : URL decoding된 String
	 * @throws Exception
	 */
	public static String urlDecode(String pStr) throws Exception
	{
		if (pStr == null)
			return "";
		else
			return URLDecoder.decode(pStr, "utf-8");
	}

	public static String URLDecode(String value, String charset)
    {
		String result = null;
		try
		{
		    result = URLDecoder.decode(value, charset);
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}
		return result;
    }	
	
    public static String setURLDecode(String value)
    {
	String result = null;

	try
	{
	    if(value != null)
	    {
		value = value.trim();
		String filePath = value.substring( 0, value.lastIndexOf("/")+1 );
		String fileName = value.substring( value.lastIndexOf("/")+1, value.length() );
		fileName = URLDecode(fileName, "euc-kr");

		result = filePath + fileName;
	    }
	}
	catch(Exception e)
	{
	    e.printStackTrace();
	}

	return result;
    }
    
    
}

