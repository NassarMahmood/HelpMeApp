package demo.logic.Converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.data.JoiningRequestsEntity;
import demo.rest.boundaries.JoiningRequestsBoundary;
import demo.rest.error.UnvalidException;

@Component
public class JoiningRequestConverter {

	
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private SodalityConverter sodalityConverter;
	
	
	
	@Autowired
	public JoiningRequestConverter() {
//		this.userConverter = userConverter;
//		this.sodalityConverter = sodalityConverter;
//		UserConverter userConverter, SodalityConverter sodalityConverter
	}




	public JoiningRequestsBoundary fromEntity(JoiningRequestsEntity joiningRequest) {


		try {

			return new JoiningRequestsBoundary(
					joiningRequest.getId(),
					this.userConverter.fromEntity(joiningRequest.getUser()),
					joiningRequest.getRequestDate(),
					this.sodalityConverter.fromEntity(joiningRequest.getSodality()),
					joiningRequest.isAcceptable());



		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert joining request from Entity to Boundary");
		}

	}



	public JoiningRequestsEntity toEntity(JoiningRequestsBoundary joiningRequest) {


		try {

			return new JoiningRequestsEntity(joiningRequest.getId(),
					this.userConverter.toEntity(joiningRequest.getUser()),
					joiningRequest.getRequestDate(),
					this.sodalityConverter.toEntity(joiningRequest.getSodality()),
					joiningRequest.isAcceptable());


		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert joining request from Boundary to Entity");
		}

	}
	
	
}
