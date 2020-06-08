package demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.logic.JoiningRequestsService;
import demo.rest.boundaries.JoiningRequestsBoundary;




@RestController
public class JoiningRequestsController {


	
	private JoiningRequestsService joiningRequestsService;
	
	
	@Autowired
	public JoiningRequestsController(JoiningRequestsService joiningRequestsService) {

		this.joiningRequestsService = joiningRequestsService;
	}
	
	
	
	
	
	//**************** ADD JIONING REQUEST ****************

	//	URL - http://localhost:8081/jioningRequest/{userId}

	@RequestMapping(
			path = "/jioningRequest/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public JoiningRequestsBoundary addJioningRequest(
			@PathVariable("userId") String userId,
			@RequestBody JoiningRequestsBoundary request) {

		return this.joiningRequestsService.addJioningRequest(userId, request);
	}




	//**************** UPDATE JIONING REQUEST ****************

	//	URL - http://localhost:8081/jioningRequest/{userId}

	@RequestMapping(
			path = "/jioningRequest/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateJioningRequest(
			@PathVariable("userId") String userId,
			@RequestBody JoiningRequestsBoundary request) {

		this.joiningRequestsService.updateJioningRequest(userId, request);
	}





	//**************** DELETE JIONING REQUEST ****************

	//	URL - http://localhost:8081/jioningRequest/{userId}

	@RequestMapping(
			path = "/jioningRequest/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteJioningRequest(
			@PathVariable("userId") String userId,
			@RequestBody JoiningRequestsBoundary request) {

		this.joiningRequestsService.deleteJioningRequest(userId, request);
	}





	//**************** GET ALL JIONING REQUEST ****************

	//	URL - http://localhost:8081/jioningRequest/{userId}/all

	@RequestMapping(
			path = "/jioningRequest/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public JoiningRequestsBoundary[] getAllJioningRequest(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.joiningRequestsService.getAllJioningRequest(userId, page, size).toArray(new JoiningRequestsBoundary[0]);
	}

	
	
	
	
	
}
