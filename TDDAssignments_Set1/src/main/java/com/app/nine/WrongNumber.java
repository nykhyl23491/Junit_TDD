package com.app.nine;

public class WrongNumber {

	public int findWrongNumberFromSeries(int[] arr) {
		int wrongNumber = 0;

		for (int i = 0, j = 1; i < arr.length; i = i + 2, j = j + 2) {
			if (i + 2 < arr.length && arr[i] + 5 != arr[i + 2]) {
				wrongNumber = arr[i + 2];
				break;
			}
			if (j + 2 < arr.length && arr[j] + 10 != arr[j + 2]) {
				wrongNumber = arr[j + 2];
				break;
			}
		}
		return wrongNumber;
	}
}
