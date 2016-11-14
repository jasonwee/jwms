package co.weetech.util;

public class DateTime {
	
	/**
	 * find the time different and give result in milliseconds. 
	 * 
	 * @param starttime start time in seconds since epoch. older than endtime.
	 * @param endtime end time in seconds since epoch. newer than starttime.
	 * @return the time difference in milliseconds
	 */
	public static long diffTimeInMilliSecond(long starttime, long endtime) {
		long diff = (endtime - starttime) * 1000;
		return diff;
	}
	
	/**
	 * find the time (in milliseconds) different and return result.
	 *  
	 * @param starttime start time in milliseconds since epoch. older than endtime.
	 * @param endtime end time in milliseconds since epoch. newer than starttime.
	 * @return the time difference in milliseconds
	 */
	public static long diffMilliSecondTime(long starttime, long endtime) {
		long diff = endtime - starttime;
		return diff;
	}
	
	/**
	 * find the time (in seconds) different and return result.
	 * 
	 * @param starttime start time in seconds since epoch. older than endtime.
	 * @param endtime end time in seconds since epoch. newer than starttime.
	 * @return the time difference in milliseconds
	 */
	public static long diffTimeInSecond(long starttime, long endtime) {
		long diff = endtime - starttime;
		return diff;
	}
}
