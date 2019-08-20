package com.app.tdd.two.wordcounterkata;

import java.util.HashMap;

public class WordCounterKata {

	public HashMap<String, Integer> countUniqueWords(String str) {
		String regex = " ";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] arr = str.split(regex);
		for (String string : arr) {
			if (map.containsKey(string))
				map.put(string, map.get(string) + 1);
			else
				map.put(string, 1);
		}
		return map;
	}
}
