package com.app.tdd.two.wordcounterkata;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class TestWordCounterKata {
	WordCounterKata wordCounterKata = new WordCounterKata();

	@Test
	public void shouldReturnCollectionOfUniqueWordWithCount() {
		HashMap<String, Integer> actual = wordCounterKata.countUniqueWords("boom bang boom");

		HashMap<String, Integer> expected = new HashMap<>();
		expected.put("boom", 2);
		expected.put("bang", 1);

		assertEquals(expected, actual);
	}

}
