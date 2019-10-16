package com.app.seven;

import com.app.exception.InvalidInputException;

public class CapitalizeString {

	public String convertString(String input) {
		StringBuilder output = new StringBuilder();
		if (input.isEmpty())
			throw new InvalidInputException("String is empty");
		else {
			String arr[] = input.split(" ");
			for (int i = 0; i < arr.length; i++) {
				output.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1, arr[i].length() - 1))
						.append(Character.toUpperCase(arr[i].charAt(arr[i].length() - 1))).append(" ");
			}
		}
		return output.toString().trim();
	}
}
