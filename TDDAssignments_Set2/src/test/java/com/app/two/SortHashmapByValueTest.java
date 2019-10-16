package com.app.two;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class SortHashmapByValueTest {

	private SortHashmapByValue sortHashmapByValue = new SortHashmapByValue();

	@Test
	public void shouldReturnSortedHashmap() {
		Map<String, Integer> input = new HashMap<String, Integer>();
		input.put("A", 22);
		input.put("B", 44);
		input.put("C", 11);
		input.put("D", 55);
		input.put("E", 66);
		input.put("F", 33);

		Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
		expected.put("C", 11);
		expected.put("A", 22);
		expected.put("F", 33);
		expected.put("B", 44);
		expected.put("D", 55);
		expected.put("E", 66);

		assertEquals(expected.toString(), sortHashmapByValue.sortMapByValue(input).toString());

	}

}
