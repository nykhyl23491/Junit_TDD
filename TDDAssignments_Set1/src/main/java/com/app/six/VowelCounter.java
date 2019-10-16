package com.app.six;

import com.app.exception.InvalidInputException;

public class VowelCounter {

	public int getVowelCount(String inputString) {
		int count = 0;
		if(inputString.isEmpty())
			throw new InvalidInputException("String is empty");
		else
		{
			for (int i = 0; i < inputString.length(); i++) 
			{
				char ch = inputString.toLowerCase().charAt(i);
				if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
					++count;
			}
		}
		return count;
	}

}
