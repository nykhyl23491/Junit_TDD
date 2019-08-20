package com.app.tdd.passwordverifier;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestPasswordVerifier {
	PasswordVerifier passwordVerifier = new PasswordVerifier();

	@Test
	public void shouldCheckGivenPasswordIsNotNull() {
		boolean actual = passwordVerifier.verifyPassword("Nikhil234");
		assertEquals(true, actual);
		
	}

	@Test(expected = PasswordNotVerifyException.class)
	public void shouldCheckGivenPasswordIsNull() {
	passwordVerifier.verifyPassword(null);
		
	}

	@Test
	public void shouCheckGivenPasswordIsLargerThanEightCharcter() {
		boolean actual = passwordVerifier.verifyPassword("Nikhil234");
		assertEquals(true, actual);
	}

	@Test(expected = PasswordNotVerifyException.class)
	public void shouCheckGivenPasswordIsLesserThanEightCharcter() {
		System.out.println("Inside :: shouCheckGivenPasswordIsLesserThanEightCharcter ");
		passwordVerifier.verifyPassword("nikhil");
	}

	@Test
	public void shouldCheckGivenPasswordHasAtLeastOneUpperCaseLetter() {
		boolean actual = passwordVerifier.verifyPassword("Nikhil234");
		System.out.println("Upper :: " + actual);
		assertEquals(true, actual);
	}

	@Test(expected = PasswordNotVerifyException.class)
	public void shouldCheckGivenPasswordHasNotAtLeastOneUpperCaseLetter() {
		System.out.println("Inside :: shouldCheckGivenPasswordHasNotAtLeastOneUpperCaseLetter");
		passwordVerifier.verifyPassword("nikhi");
	}

	@Test
	public void shouldCheckGivenPasswordHasAtLeastOneLowerCaseLetter() {
		boolean actual = passwordVerifier.verifyPassword("Nikhil234");
		System.out.println("Lower :: " + actual);
		assertEquals(true, actual);
	}

	@Test(expected = PasswordNotVerifyException.class)
	public void shouldCheckGivenPasswordHasNotAtLeastOneLowerCaseLetter() {
		System.out.println("Inside :: shouldCheckGivenPasswordHasNotAtLeastOneLowerCaseLetter");
		passwordVerifier.verifyPassword("NIKHIL234");
	}

	@Test
	public void shouldCheckGivenPasswordHasAtLeastOneDigit() {
		boolean actual = passwordVerifier.verifyPassword("Nikhil234");
		System.out.println("Digit :: " + actual);
		assertEquals(true, actual);
	}

	@Test(expected = PasswordNotVerifyException.class)
	public void shouldCheckGivenPasswordHasNotAtLeastOneDigit() {
		System.out.println("Inside :: shouldCheckGivenPasswordHasNotAtLeastOneDigit");
		passwordVerifier.verifyPassword("nikhil");
	}
}


/*
 * public class TestPasswordVerifier { com.app.demo.tdd.PasswordVerifier
 * passwordVerifier = new com.app.demo.tdd.PasswordVerifier();
 * 
 * @Test public void shouldCheckGivenPasswordIsNotNull() {
 * assertEquals("SUCCESS", passwordVerifier.verifyPassword("Nikhil234")); }
 * 
 * @Test public void shouldCheckGivenPasswordIsNull() {
 * assertEquals("INVALID PASS", passwordVerifier.verifyPassword(null)); }
 * 
 * @Test public void shouCheckGivenPasswordIsLargerThanEightCharcter() {
 * assertEquals("SUCCESS", passwordVerifier.verifyPassword("Nikhil234")); }
 * 
 * @Test public void shouCheckGivenPasswordIsLesserThanEightCharcter() {
 * assertEquals("PASSWORD MUST HAVE MINIMUM 8 CHARS",
 * passwordVerifier.verifyPassword("Nikhil")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasAtLeastOneUpperCaseLetter() {
 * assertEquals("SUCCESS", passwordVerifier.verifyPassword("Nikhil234")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasNotAtLeastOneUpperCaseLetter() {
 * assertEquals("PASSWORD MUST HAVE A UPPER CHAR",
 * passwordVerifier.verifyPassword("nikhil234")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasAtLeastOneLowerCaseLetter() {
 * assertEquals("SUCCESS", passwordVerifier.verifyPassword("Nikhil234")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasNotAtLeastOneLowerCaseLetter() {
 * assertEquals("PASSWORD SHOULD CONTAIN  A LOWER CASE",
 * passwordVerifier.verifyPassword("NIKHIL234")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasAtLeastOneDigit() {
 * assertEquals("SUCCESS", passwordVerifier.verifyPassword("Nikhil234")); }
 * 
 * @Test public void shouldCheckGivenPasswordHasNotAtLeastOneDigit() {
 * assertEquals("PASSWORD MUST HAVE A DIGIT",
 * passwordVerifier.verifyPassword("Nikhillll")); }
 * 
 * }
 */