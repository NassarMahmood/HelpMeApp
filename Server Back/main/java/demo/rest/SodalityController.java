package demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.logic.SodalityService;
import demo.rest.boundaries.SodalityBoundary;

@RestController
public class SodalityController {



	private SodalityService sodalityService;


	@Autowired
	public SodalityController(SodalityService sodalityService) {

		this.sodalityService = sodalityService;
	}






	//**************** ADD SODALITY ****************

	//	URL - http://localhost:8081/sodality/{userId}

	@RequestMapping(
			path = "/sodality/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public SodalityBoundary addSodality(
			@PathVariable("userId") String userId,
			@RequestBody SodalityBoundary sodality) {

		return this.sodalityService.addSodality(userId,sodality);
	}




	//**************** UPDATE SODALITY ****************

	//	URL - http://localhost:8081/sodality/{userId}

	@RequestMapping(
			path = "/sodality/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSodality(
			@PathVariable("userId") String userId,
			@RequestBody SodalityBoundary sodality) {

		this.sodalityService.updateSodality(userId, sodality);
	}




	//**************** DELETE SODALITY ****************

	//	URL - http://localhost:8081/sodality/{userId}

	@RequestMapping(
			path = "/sodality/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSodality(
			@PathVariable("userId") String userId,
			@RequestBody SodalityBoundary sodality) {

		this.sodalityService.deleteSodality(userId, sodality);
	}



	//**************** GET SODALITY BY ID ****************

	//	URL - http://localhost:8081/sodality/{userId}/byId/{sodalityId}

	@RequestMapping(
			path = "/sodality/{userId}/byId/{sodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SodalityBoundary getSodalityById(
			@PathVariable("userId") String userId,
			@PathVariable("sodalityId") Long sodalityId) {

		return this.sodalityService.getSodalityById(userId, sodalityId);
	}




	//**************** GET ALL SODALITY ****************

	//	URL - http://localhost:8081/sodality/{userId}/all

	@RequestMapping(
			path = "/sodality/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SodalityBoundary[] getAllSodalities(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.sodalityService.getAllSodalities(userId, page, size).toArray(new SodalityBoundary[0]);
	}






















}
