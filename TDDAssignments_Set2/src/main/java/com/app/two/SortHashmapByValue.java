package com.app.two;

import java.util.LinkedHashMap;
import java.util.Map;

public class SortHashmapByValue {

	public Map<String, Integer> sortMapByValue(Map<String, Integer> input) {
		Map<String, Integer> output = new LinkedHashMap<>();
		input.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEachOrdered(map -> output.put(map.getKey(), map.getValue()));
		return output;
	}
}
