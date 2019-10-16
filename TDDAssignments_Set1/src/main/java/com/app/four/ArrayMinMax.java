package com.app.four;

import java.util.Arrays;

public class ArrayMinMax {

	public int getMaxNumber(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length - 1];
	}

	public int getMinNumber(int[] arr) {
		Arrays.sort(arr);
		return arr[0];
	}

}
