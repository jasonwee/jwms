package co.weetech.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateTimeTest {
	
	@Test
	public void diffTimeInMilliSecond() {
		long res = DateTime.diffTimeInMilliSecond(1478423128l, 1478423143l);
		assertEquals(15000, res);
	}
	
	@Test
	public void diffTimeInSecond() {
		long res = DateTime.diffTimeInSecond(1478423128l, 1478423143l);
		assertEquals(15, res);
	}
	
	@Test
	public void diffMilliSecondTime() {
		long res = DateTime.diffMilliSecondTime(1478423276000l, 1478423298000l);
		assertEquals(22000, res);
	}
	
}
