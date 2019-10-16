package com.app.four;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayMinMaxTest {
	
	private ArrayMinMax arrayMinMax = new  ArrayMinMax();
	private int arr[] = {1,22,3,6,4};
	@Test
	public void shouldReturnMaximumNumberFromArray()
	{
		assertEquals(22, arrayMinMax.getMaxNumber(this.arr));
	}
	
	@Test
	public void shouldReturnMinimumNumberFromArray()
	{
		assertEquals(1, arrayMinMax.getMinNumber(this.arr));
	}
}
