package com.app.three;

import java.util.ArrayList;
import java.util.List;

public class LeadersInArray {

	public List<Integer> printLeadersInArray(int arr[]) {
		List<Integer> leaders = new ArrayList<Integer>();
		int size = arr.length;
		int max = arr[size - 1];
		leaders.add(max);

		for (int i = size - 2; i >= 0; i--) {
			if (max < arr[i]) {
				max = arr[i];
				leaders.add(max);
			}
		}
		return leaders;
	}

}
