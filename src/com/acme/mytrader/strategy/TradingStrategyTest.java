package com.acme.mytrader.strategy;

import static org.junit.Assert.*;

import org.junit.*;

public class TradingStrategyTest {
	@Test
    public void testReadCurrPrice() {
		String expected = "50.02";
	    String result = TradingStrategy.readCurrPrice();
	    assertEquals(expected, result);
    }
}
