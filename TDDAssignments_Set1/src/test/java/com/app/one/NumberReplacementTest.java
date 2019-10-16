package com.app.one;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NumberReplacementTest {

	private NumberReplacement numberReplacement = new NumberReplacement();
	

	@Test
	public void shouldCheckNumberToReplaceIsNotNull() {
		numberReplacement.replaceNumberWithinArrayList(25, 6);
		assertNotNull(25);
	}
	
	@Test
	public void shouldCheckReplacementNumberIsNotNull() {
		numberReplacement.replaceNumberWithinArrayList(25, 6);
		assertNotNull(6);
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldCheckNumberToReplaceIsNull() {
		numberReplacement.replaceNumberWithinArrayList(null, 6);
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldCheckReplacementNumberIsNull() {
		numberReplacement.replaceNumberWithinArrayList(25,null);
	}
	
	@Test
	public void shouldCheckNumberIsPresentInArrayList() {
		List<Integer> numList = new ArrayList<Integer>(Arrays.asList(55,25,368));
		numberReplacement.replaceNumberWithinArrayList(25, 6);
		assertEquals(true, numList.contains(25));
	}

	@Test(expected = RuntimeException.class)
	public void shouldCheckNumberIsNotPresentInArrayList() {
		List<Integer> numList = new ArrayList<Integer>(Arrays.asList(55,25,368));
		numberReplacement.replaceNumberWithinArrayList(100, 6);
		assertEquals(false, numList.contains(100));
	}

	@Test
	public void shouldReturnNewListAfterReplacement()
	{
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(55,6,368));
		List<Integer> actual = numberReplacement.replaceNumberWithinArrayList(25, 6);
		assertEquals(expected, actual);
	}
}
