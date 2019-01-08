package com.secw.JUnit;

import com.secw.tool.Calculate;

public class CalculateTest extends junit.framework.TestCase{
	public void testCalculate(){
		assertEquals("09:30", Calculate.minToHour(570));
		assertEquals("13:05", Calculate.minToHour(785));
		assertEquals(570, Calculate.stringToTime("09:30"));
		
	}
}
