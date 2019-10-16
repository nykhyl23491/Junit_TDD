package com.app.five;

import java.util.List;

public class SublistFromArrayList {

	public List<Integer> getSublistFromArraylist(List<Integer> numList, int startIndex, int endIndex) {

		return numList.subList(startIndex, endIndex);

	}

}
