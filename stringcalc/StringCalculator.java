package com.app.tdd.stringcalc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StringCalculator {
	
	 private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public int calculator(String string) {
		String delimiter = "";
		if (string.startsWith("//")) {
			delimiter = string.substring(2, 3);
			string = string.substring(4);
		}
		int total = 0;
		if (string.isEmpty())
			return 0;

		else if (string.length() == 1)
			return Integer.parseInt(string);
		else {
			String[] arr = string.split("[, | \n | ;" + delimiter + "]");
			for (String operand : arr) {
				int num = Integer.parseInt(operand);
				if (num < 0)
					throw new NegativeNumberExeption("Negative Number Not allowed");
				if (num >= 1000)
					num = 0;
				total += num;
			}
		}
		logger.log(Level.INFO,total+"");
		return total;
		
	}

}
