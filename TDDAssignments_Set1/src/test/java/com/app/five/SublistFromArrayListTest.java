package com.app.five;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SublistFromArrayListTest {
	
	private SublistFromArrayList sublistFromArrayList = new SublistFromArrayList();
	
	private List<Integer> numList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,0));
	
	@Test
	public void shouldReturnSublistFromArrayList()
	{
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(3,4,5));
		assertEquals(expected, sublistFromArrayList.getSublistFromArraylist(numList,2,5));
	}
}
