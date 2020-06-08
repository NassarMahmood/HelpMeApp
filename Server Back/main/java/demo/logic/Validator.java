package demo.logic;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.passay.AlphabeticalSequenceRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.springframework.stereotype.Component;

import demo.rest.boundaries.AddressBoundary;
import demo.rest.boundaries.BirthDate;
import demo.rest.boundaries.IndigentBoundary;
import demo.rest.boundaries.JoiningRequestsBoundary;
import demo.rest.boundaries.SodalityBoundary;
import demo.rest.boundaries.UserBoundary;
import demo.rest.error.UnvalidException;




@Component
public class Validator {


	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";





	public void validateUser(UserBoundary user) {

		validateID(user.getUserId());

		validateString(user.getFisrtName(), "user first name");

		validateString(user.getLastName(), "user last name");

		validateBirthDate(user.getBirthdate());

		validatePhone(user.getPhone());

		validateAddress(user.getAddress());

		validateEmail(user.getEmail());

		validatePassword(user.getPassword());

	}




	public void validateSodality(SodalityBoundary sodality) {


		validateString(sodality.getName(), "sodality name");

		validateAddress(sodality.getAddress());

		validatePhone(sodality.getPhone());

		validateEmail(sodality.getEmail());

	}




	public void validateIndigent(IndigentBoundary newIndigent) {

		validateID(newIndigent.getIndigentId());

		validateString(newIndigent.getFisrtName(), "indigent first name");

		validateString(newIndigent.getLastName(), "indigent last name");

		validateBirthDate(newIndigent.getBirthdate());

		validatePhone(newIndigent.getPhone());

		validateAddress(newIndigent.getAddress());

		validateEatdays(newIndigent.getEatDays());

	}


	





	public void validateEatdays(int days) {

		if(days < 0 || days > 100)
			throw new UnvalidException("eat days must be between 0 - 100");

	}






	public void validateBirthDate(BirthDate birthDate) {


		if(birthDate == null)
			throw new UnvalidException("unvalid birth date!");


		if(birthDate.getYears() > LocalDate.now().getYear() || birthDate.getYears() < 1900)
			throw new UnvalidException("unvalid birth date year!");

		if(birthDate.getMonths() > 12 || birthDate.getMonths() < 1 )
			throw new UnvalidException("unvalid birth date month!");

		if(birthDate.getDays() > 31 || birthDate.getDays() < 1 )
			throw new UnvalidException("unvalid birth date days!");

	}





	public void validateActive(Boolean active) {

		if(active == null)
			throw new UnvalidException("unvalid active status!");

	}






	public void validateAge(int age) {

		if(age < 0 || age > 120)
			throw new UnvalidException("age must be between 0 - 120");
	}



	public void validateString(String string , String type) {

		if(string == null || string == "")
			throw new UnvalidException(type+" is embty ..!");

		if(string.length() > 32)
			throw new UnvalidException("unvalid "+type+" ..! max letters 32");
	}



	public void validateID(String id) {

		Pattern pattern = Pattern.compile("\\d{9}");
		if (id == null || !pattern.matcher(id).matches())
			throw new UnvalidException("The id " + id + " is invalid!");
	}



	public void validatePhone(String phone) {

		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		if (phone == null || !pattern.matcher(phone).matches())
			throw new UnvalidException("The phone: " + phone + " is invalid!");
	}



	public void validateEmail(String email) {

		//		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{0,9}$";
		//		Pattern pat = Pattern.compile(emailRegex);
		//		if (email == null || !pat.matcher(email).matches())
		//			throw new UnvalidException("The email: " + email + " is invalid!");
		//		

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (email == null || !matcher.matches())
			throw new UnvalidException("The email: " + email + " is invalid!");		

	}

	public void validatePassword(String password) {

		final PasswordValidator validator = new PasswordValidator(Arrays.asList(
				new LengthRule(8, 30), 
				new UppercaseCharacterRule(1), 
				new DigitCharacterRule(1), 
				new SpecialCharacterRule(1), 
				new NumericalSequenceRule(3,false),
				new AlphabeticalSequenceRule(3,false),
				new QwertySequenceRule(3,false),
				new WhitespaceRule()));
		final RuleResult result = validator.validate(new PasswordData(password));
		if (!result.isValid()) {
			throw new UnvalidException("The email: " + validator.getMessages(result) + " is invalid!");		
		}

	}


	public void validateAddress(AddressBoundary address) {

		validateString(address.getState() , "state");

		validateString(address.getCity() , "city");

		validateString(address.getStreetAddress() , "street");

	}




	




























}
