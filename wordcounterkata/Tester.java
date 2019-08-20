package com.app.tdd.two.wordcounterkata;

import java.util.HashMap;

public class Tester {
	public static void main(String[] args) 
	{
		WordCounterKata wordCounterKata = new WordCounterKata();
		HashMap< String,Integer> map = wordCounterKata.countUniqueWords("boom bang boom");
		map.forEach((k,v)->System.out.println(k + " ---"+v));
		
		
		String password = "abcdefgh";
		boolean status = password.chars().anyMatch(ch -> Character.isUpperCase(ch));
		System.out.println(status);
		
	}

}
