package demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.data.AddressEntity;
import demo.data.SodalityEntity;
import demo.rest.boundaries.AddressBoundary;
import demo.rest.boundaries.SodalityBoundary;
import demo.rest.error.UnvalidException;

@Component
public class SodalityConverter {

	
	
	@Autowired
	public SodalityConverter() {}




	public SodalityBoundary fromEntity(SodalityEntity sodality) {


		try {

			return new SodalityBoundary(
					sodality.getId(),
					sodality.getName(),
					new AddressBoundary(
							sodality.getAddress().getState(),
							sodality.getAddress().getCity(),
							sodality.getAddress().getStreetAddress(),
							sodality.getAddress().getHouseNumber(),
							sodality.getAddress().getZipCode()),
					sodality.getPhone(),
					sodality.getEmail(),
					sodality.getWebSite());



		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert sodality from Entity to Boundary");
		}

	}



	public SodalityEntity toEntity(SodalityBoundary sodality) {


		try {

			return new SodalityEntity(
					sodality.getId(),
					sodality.getName(),
					new AddressEntity(
							sodality.getAddress().getState(),
							sodality.getAddress().getCity(),
							sodality.getAddress().getStreetAddress(),
							sodality.getAddress().getHouseNumber(),
							sodality.getAddress().getZipCode()),
					sodality.getPhone(),
					sodality.getEmail(),
					sodality.getWebSite(),
					null,
					null,
					null,
					null);


		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert sodality from Boundary to Entity");
		}

	}
	
	
}
