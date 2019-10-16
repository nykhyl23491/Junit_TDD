package com.app.ten;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NextNumberInSeriesTest {
	
	private NextNumberInSeries nextNumberInSeries = new NextNumberInSeries();
	
	@Test
	public void shouldReturnNextNumberInSeries()
	{
		int arr[]= {7,10,8,11,9,12};
		int expected = 10;
		assertEquals(expected, nextNumberInSeries.getNextNumberofSeries(arr));
	}

}
