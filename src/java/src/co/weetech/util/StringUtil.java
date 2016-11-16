package co.weetech.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class StringUtil {
	
	/**
	 * convert string into hex. the hex is appended with 0a.
	 * 
	 * @param string the string to be convert.
	 * 
	 * @return the hex in string format.
	 * 
	 * @throws UnsupportedEncodingException if the string is not utf-8 encoded.
	 */
	public static String toHex(String string) throws UnsupportedEncodingException {		
		return String.format("%02x".concat("0a"), new BigInteger(1, string.getBytes("UTF-8"))); 
	}

}