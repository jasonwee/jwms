package co.weetech.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

public class StringUtilTest {

	
	@Test
	public void toHex() {
		try {
			assertEquals("444f4e450a", StringUtil.toHex("DONE"));
		} catch (UnsupportedEncodingException e) {
			fail("oh crap");
			e.printStackTrace();
		}
	}
	
}
