package com.app.ten;

import java.util.Arrays;

public class StringAnagram {

	public boolean checkStringAnagram(String string1, String string2) {
		char[] arr1 = string1.replaceAll(" ", "").toLowerCase().toCharArray();
		char[] arr2 = string2.replaceAll(" ", "").toLowerCase().toCharArray();
		if (arr1.length != arr2.length)
			return false;
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		return Arrays.equals(arr1, arr2);
	}

}
