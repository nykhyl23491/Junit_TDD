package com.app.two;

public class SpyNumber {

	public boolean checkSpyNumber(int number) {
		int rem = 0, sum = 0, mul = 1;
		while (number != 0) {
			rem = number % 10;
			sum = sum + rem;
			mul = mul * rem;
			number = number / 10;
		}
		return sum == mul;
	}
}
