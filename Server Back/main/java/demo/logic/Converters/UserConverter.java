package demo.logic.Converters;


import java.time.LocalDate;

import org.springframework.stereotype.Component;

import demo.data.UserEntity;
import demo.rest.boundaries.AddressBoundary;
import demo.rest.boundaries.BirthDate;
import demo.rest.boundaries.Gender;
import demo.rest.boundaries.UserBoundary;
import demo.rest.boundaries.UserRole;
import demo.rest.error.UnvalidException;

@Component
public class UserConverter {




	public UserConverter() {}




	public UserBoundary fromEntity(UserEntity user) {


		try {

			return new UserBoundary(user.getUserId(),
					user.getFisrtName(),
					user.getLastName(),
					new BirthDate(
							user.getBirthDate().getDayOfMonth(),
							user.getBirthDate().getMonthValue(),
							user.getBirthDate().getYear()),
					Gender.valueOf(user.getGender().name()),
					user.getPhone(),
					new AddressBoundary(
							user.getAddress().getState(),
							user.getAddress().getCity(),
							user.getAddress().getStreetAddress(),
							user.getAddress().getHouseNumber(),
							user.getAddress().getZipCode()),
					user.getActive(),
					user.getSignUpTimestamp(),
					user.getEmail(),
					user.getPassword(),
					UserRole.valueOf(user.getRole().name()),
					user.isHaveRequest());



		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert user from Entity to Boundary");
		}

	}



	public UserEntity toEntity(UserBoundary user) {

		System.err.println(user);
		
		try {

			return new UserEntity(user.getUserId(),
					user.getFisrtName(),
					user.getLastName(),
					LocalDate.of(
							user.getBirthdate().getYears(),
							user.getBirthdate().getMonths(),
							user.getBirthdate().getDays()),
					demo.data.Gender.valueOf(user.getGender().name()),
					user.getPhone(),
					new demo.data.AddressEntity(
							user.getAddress().getState(),
							user.getAddress().getCity(),
							user.getAddress().getStreetAddress(),
							user.getAddress().getHouseNumber(),
							user.getAddress().getZipCode()),
					user.getActive(),
					user.getSignUpTimestamp(),
					user.getEmail(),
					user.getPassword(),
					demo.data.UserRole.valueOf(user.getRole().name()),
					null, null,
					user.isHaveRequest());


		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert user from Boundary to Entity");
		}

	}




}



