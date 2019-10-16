package com.app.one;

import java.util.Arrays;
import java.util.Comparator;

import com.app.exception.InvalidInputException;

public class SecondHighestInArray {

	public int getSecondHighestNumber(int[] arr) {

		if (arr != null) {
			int sortedArr[] = Arrays.stream(arr).boxed().distinct().sorted(Comparator.reverseOrder())
					.mapToInt(Integer::intValue).toArray();
			return sortedArr[1];
		} else
			throw new InvalidInputException("Array Is Null");
	}
}
