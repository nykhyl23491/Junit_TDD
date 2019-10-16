package com.app.five;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOfCharacter {

	public String getCountOfCharacter(String input) {
		StringBuilder resultString = new StringBuilder();
		Map<Character, Integer> map = input.chars().boxed().collect(Collectors
				.toMap(k -> Character.valueOf((char) k.intValue()), v -> 1, Integer::sum, LinkedHashMap::new));
		map.forEach((k, v) -> resultString.append(k).append(v).toString());
		return resultString.toString().replaceAll("1", "");
	}

	public static void main(String[] args) {
		CountOfCharacter obj = new CountOfCharacter();
		System.out.println(obj.getCountOfCharacter("iamyashemployee"));
	}
}
