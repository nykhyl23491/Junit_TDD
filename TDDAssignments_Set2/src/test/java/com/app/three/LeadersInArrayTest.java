package com.app.three;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LeadersInArrayTest 
{
	private LeadersInArray leadersInArray = new LeadersInArray();
	
	@Test
	public void shouldReturnLeadersInAnArray()
	{
		int arr[] = { 16, 17, 4, 3, 5, 2 };
		List<Integer> expected = new ArrayList(Arrays.asList(2,5,17));
		List<Integer> actual = leadersInArray.printLeadersInArray(arr);
		assertEquals(expected, actual);
	}
}
