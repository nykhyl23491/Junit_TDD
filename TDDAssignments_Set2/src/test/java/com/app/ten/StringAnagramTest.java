package com.app.ten;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringAnagramTest {
	
	private StringAnagram anagram = new StringAnagram();
	
	@Test
	public void shouldReturnTrueIfStringsAreAnagram()
	{
		boolean expected = true;
		boolean actual = anagram.checkStringAnagram("Peek", "Keep");
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseIfStringsAreNotAnagram()
	{
		boolean expected = false;
		boolean actual = anagram.checkStringAnagram("xyz", "abcd");
		assertEquals(expected, actual);
	}


}
