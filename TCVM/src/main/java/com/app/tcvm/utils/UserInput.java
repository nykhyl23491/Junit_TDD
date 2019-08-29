package com.app.tcvm.utils;

import java.util.Scanner;

public class UserInput {
	private static Scanner sc = new Scanner(System.in);

	public int getUserInput() {
		return sc.nextInt();
	}
}
