package com.app.nine;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SumOfEvenNumber {
	public int getSumOfEvenNumber(int arr[]) {
		return Arrays.stream(arr).boxed().filter(num -> num % 2 == 0).collect(Collectors.summingInt(even -> even + 0));
	}

	public static void main(String[] args) {
		int arr[] = { 1, 3, 5, 7, 9 };
		SumOfEvenNumber oj = new SumOfEvenNumber();
		System.out.println(oj.getSumOfEvenNumber(arr));
	}
}
