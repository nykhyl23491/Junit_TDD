package com.app.tdd.passwordverifier;

import java.util.function.Predicate;

public class PasswordVerifier {

	public boolean verifyPassword(String password) {
		if (null == password)
			throw new PasswordNotVerifyException("Password should not be null");
		else if (hasAtLeastOneLowerCaseCharacter.test(password)) 
		{
			if (hasLengthOfEight.and(hasAtOneLeastUpperCaseCharacter).or(hasAtLeastOneDigit).test(password))
				return true;
			else
				throw new PasswordNotVerifyException(
						"Password should contain at leat one upper or digit and more than eight chars");
		} 
		else
			throw new PasswordNotVerifyException("Password should contain atleast one lower case");
	}

	Predicate<String> hasAtLeastOneDigit = password -> password.chars().anyMatch(Character::isDigit);

	Predicate<String> hasAtOneLeastUpperCaseCharacter = password -> password.chars().anyMatch(Character::isUpperCase);

	Predicate<String> hasAtLeastOneLowerCaseCharacter = password -> password.chars().anyMatch(Character::isLowerCase);

	Predicate<String> hasLengthOfEight = password -> password.length() >= 8 ? true : false;

}
