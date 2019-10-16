package com.app.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberReplacement {

	private List<Integer> numList = new ArrayList<Integer>(Arrays.asList(55, 25, 368));

	public List<Integer> replaceNumberWithinArrayList(Integer numberToReplace, Integer replacement) {

		if (numberToReplace != null && replacement != null) {
			if (this.numList.contains(numberToReplace)) {
				int index = numList.indexOf(numberToReplace);
				numList.remove(index);
				numList.add(index, replacement);
				return this.numList;
			} else
				throw new RuntimeException("Number Not Found");
		} else
			throw new RuntimeException("Number Is Null");
	}

}
