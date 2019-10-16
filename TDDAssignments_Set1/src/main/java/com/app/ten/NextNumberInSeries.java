package com.app.ten;

public class NextNumberInSeries {
	public int getNextNumberofSeries(int arr[]) {
		int num = arr[0];
		int next = num;
		for (int i = 1; i <= arr.length; i++) {
			if (i % 2 == 0)
				next = next - 2;
			else
				next = next + 3;
		}
		return next;
	}
}
