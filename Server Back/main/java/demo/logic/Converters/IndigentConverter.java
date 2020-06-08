package demo.logic.Converters;

import static org.assertj.core.api.Assertions.in;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.data.IndigentEntity;
import demo.rest.boundaries.AddressBoundary;
import demo.rest.boundaries.BirthDate;
import demo.rest.boundaries.Gender;
import demo.rest.boundaries.IndigentBoundary;
import demo.rest.error.UnvalidException;

@Component
public class IndigentConverter {

	private SodalityConverter sodalityConverter;
	
	@Autowired
	public IndigentConverter(SodalityConverter sodalityConverter) {
		this.sodalityConverter = sodalityConverter;
	}




	public IndigentBoundary fromEntity(IndigentEntity indigent) {


		try {

			return new IndigentBoundary(
					indigent.getIndigentId(),
					indigent.getFisrtName(),
					indigent.getLastName(),
					new BirthDate(
							indigent.getBirthdate().getDayOfMonth(),
							indigent.getBirthdate().getMonthValue(),
							indigent.getBirthdate().getYear()),
					Gender.valueOf(indigent.getGender().name()),
					indigent.getPhone(),
					new AddressBoundary(
							indigent.getAddress().getState(),
							indigent.getAddress().getCity(),
							indigent.getAddress().getStreetAddress(),
							indigent.getAddress().getHouseNumber(),
							indigent.getAddress().getZipCode()),
					indigent.getActive(),
					indigent.getSignUpTimestamp(),
					(indigent.getSodality() != null)?this.sodalityConverter.fromEntity(indigent.getSodality()): null,
					indigent.getEatDays(),
					indigent.getNotes());



		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert indigent from Entity to Boundary");
		}

	}



	public IndigentEntity toEntity(IndigentBoundary indigent) {


		try {

			return new IndigentEntity(
					indigent.getIndigentId(),
					indigent.getFisrtName(),
					indigent.getLastName(),
					LocalDate.of(
							indigent.getBirthdate().getYears(),
							indigent.getBirthdate().getMonths(),
							indigent.getBirthdate().getDays()),
					demo.data.Gender.valueOf(indigent.getGender().name()),
					indigent.getPhone(),
					new demo.data.AddressEntity(
							indigent.getAddress().getState(),
							indigent.getAddress().getCity(),
							indigent.getAddress().getStreetAddress(),
							indigent.getAddress().getHouseNumber(),
							indigent.getAddress().getZipCode()),
					indigent.getActive(),
					indigent.getSignUpTimestamp(),
					indigent.getEatDays(),
					indigent.getNotes(),
					(indigent.getSodality() != null) ? this.sodalityConverter.toEntity(indigent.getSodality()): null,
					null);


		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert indigent from Boundary to Entity");
		}

	}
	
	
	
	
	
}
