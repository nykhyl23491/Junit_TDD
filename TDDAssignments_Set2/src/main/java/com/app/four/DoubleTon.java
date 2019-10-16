package com.app.four;

public class DoubleTon {

	private static DoubleTon instanceOne = null;
	private static DoubleTon instanceTwo = null;
	private static int counter = 0;
	private DoubleTon() {
	}

	public static DoubleTon getInstance() {
		if (instanceOne == null)
			return instanceOne = new DoubleTon();
		else if (instanceTwo == null)
			return instanceTwo = new DoubleTon();
		else {
			if (Math.random() < 0.5)
				return instanceOne;
			else
				return instanceTwo;
		}
	}
	public static void main(String[] args) {
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
		System.out.println(DoubleTon.getInstance());
	}
}
