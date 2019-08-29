package com.app.tdd.stringcalc;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	private static final Logger LOGGER = Logger.getLogger(StringCalculator.class.getName());
	private static final String DEFAULT_DELIMETER = "[, \n]";
	private static final Pattern CUSTOM_DELIMETER = Pattern.compile("//(.*)\n(.*)");
	public int calculator(String expression) {
		int sum = 0;
		String[] operands;
		if ("".equals(expression))
			return 0;
		else if (expression.startsWith("//")) {
			Matcher matcher = CUSTOM_DELIMETER.matcher(expression);
			matcher.find();
			operands = matcher.group(2).split(matcher.group(1));
		} else {
			operands = expression.split(DEFAULT_DELIMETER);
		}
		sum = Arrays.asList(operands).parallelStream().mapToInt(StringCalculator::convertStringToInteger).sum();
		LOGGER.info("Addition of " + expression + " is " + sum);
		return sum;
	}

	public static int convertStringToInteger(String token) {
		int num = 0;
		num = Integer.parseInt(token);
		if (num < 0)
			throw new NegativeNumberExeption(" Negative Number Not Allowed");
		if (num > 1000)
			num = 0;
		return num;
	}
	
	/**
	public int strCalculator(String expression) {
		String delimiter = ",";
		if (expression.startsWith("//")) {
			delimiter = expression.substring(2, 3);
			expression = expression.substring(4);
		}
		int total = 0;
		if (expression.isEmpty())
			return 0;
		else if (expression.length() == 1)
			return Integer.parseInt(expression);
		else {
			String[] arr = expression.split("[, | \n| ;" + delimiter + "]");
			for (String operand : arr) {
				int num = Integer.parseInt(operand);
				if (num < 0)
					throw new NegativeNumberExeption("Negative Number Not allowed");
				if (num >= 1000)
					num = 0;
				total += num;
			}
		}
		return total;
	}
	*/
}