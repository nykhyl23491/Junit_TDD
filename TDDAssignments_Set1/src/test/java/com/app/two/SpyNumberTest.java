package com.app.two;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpyNumberTest {          
	
	private SpyNumber spyNumber = new SpyNumber();
	
	@Test
	public void shouldCheckGivenNumberIsSpyNumber()
	{
		assertEquals(true, spyNumber.checkSpyNumber(22));
	}
	
	@Test
	public void shouldCheckGivenNumberIsNotSpyNumber()
	{
		assertEquals(false, spyNumber.checkSpyNumber(33));
	}
}
