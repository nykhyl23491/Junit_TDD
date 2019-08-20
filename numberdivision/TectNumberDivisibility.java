package com.app.tdd.numberdivision;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//"Fizz" if number is divisible by 3
//"buzz" if number is divisible by 5
//"FizzBuzz" if number is divisible by 3 and 5
//the number otherwise

public class TectNumberDivisibility {
	NumberDivisibility numberDivisibility = new NumberDivisibility();

	@Test
	public void shouldPrintFizzIfNumberIsDivisibleByThree() {
		String actual = numberDivisibility.numberDividibility(18);
		assertEquals("Fizz", actual);
	}
	
	@Test
	public void shouldPrintFizzIfNumberIsDivisibleByFive() {
		String actual = numberDivisibility.numberDividibility(20);
		assertEquals("Buzz", actual);
	}
	
	@Test
	public void shouldPrintFizzIfNumberIsDivisibleByThreeAndFive() {
		String actual = numberDivisibility.numberDividibility(15);
		assertEquals("FizzBuzz", actual);
	}
	
	@Test
	public void shouldReturnNumberIfNotDivisibleByThreeAndFive() {
		String actual = numberDivisibility.numberDividibility(22);
		assertEquals("22", actual);
	}
	
	
}
