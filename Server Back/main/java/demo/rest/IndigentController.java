package demo.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.rest.boundaries.IndigentBoundary;
import demo.logic.IndigentService;



@RestController
public class IndigentController {



	private IndigentService indigentService;





	@Autowired
	public IndigentController(IndigentService indigentService) {
		this.indigentService = indigentService;
	}







	//********************************* ADD INDIGENT *********************************

	//	URL - http://localhost:8081/indigent/{userId}

	@RequestMapping(
			path = "/indigent/{userId}", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary addIndigent(
			@PathVariable("userId") String userId,
			@RequestBody IndigentBoundary newIndigent) {

		return this.indigentService.addIndigent(userId, newIndigent);
	}




	//********************************* UPDATE INDIGENT *********************************

	// URL - http://localhost:8081/indigent/{userId}

	@RequestMapping(
			path = "/indigent/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateIndigent(
			@PathVariable("userId") String userId,
			@RequestBody IndigentBoundary updateIndigent) {

		this.indigentService.updateIndigent(userId, updateIndigent);
	}




	//********************************* DELETE INDIGENT *********************************

	// URL - http://localhost:8081/indigent/{userId}
	@RequestMapping(
			path = "/indigent/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deletedIndigent(
			@PathVariable("userId") String userId,
			@RequestBody IndigentBoundary deletedIndigent) {

		this.indigentService.deletedIndigent(userId, deletedIndigent);

	}






	//********************************* GET INDIGENT BY ID AND SODALITY ID *********************************

	//	URL - http://localhost:8081/indigent/{userId}/byIndigentId/{IndigentId}/bySodalityId/{sodalityId}

	@RequestMapping(
			path = "/indigent/{userId}/byIndigentId/{IndigentId}/bySodalityId/{sodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary getIndigentByIdAndSodalityId(
			@PathVariable("userId") String userId,
			@PathVariable("IndigentId") String IndigentId,
			@PathVariable("sodalityId") Long sodalityId){

		return this.indigentService.getIndigentByIdAndSodalityId(userId, IndigentId, sodalityId);
	}




	//********************************* GET INDIGENTS BY FIRST NAME AND SODALITY ID *********************************

	//	URL - http://localhost:8081/indigent/{userId}/byFirstName/{indigentFisrtName}/bySodalityId/{sodalityId}
	@RequestMapping(
			path = "/indigent/{userId}/byFirstName/{indigentFisrtName}/bySodalityId/{sodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary[] getIndigentsByFirstNameAndSodalityId(
			@PathVariable("userId") String userId,
			@PathVariable("indigentFisrtName") String indigentFisrtName,
			@PathVariable("sodalityId") Long sodalityId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page){

		return this.indigentService.getIndigentsByFirstNameAndSodalityId(userId, indigentFisrtName, sodalityId, size, page).toArray(new IndigentBoundary[0]);
	}








	//********************************* GET ALL INDIGENTS BY SODALITY ID *********************************

	//	URL - http://localhost:8081/indigent/{userId}/bySodalityId/{sodalityId}

	@RequestMapping(
			path = "/indigent/{userId}/bySodalityId/{sodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary[] getAllIndigentsBySodalityId(
			@PathVariable("userId") String userId,
			@PathVariable("sodalityId") Long sodalityId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.indigentService.getAllIndigentsBySodalityId(userId, sodalityId, size, page).toArray(new IndigentBoundary[0]);
	}





	//********************************* GET INDIGENT BY ID *********************************// ADMIN

	//	URL - http://localhost:8081/indigent/{userId}/byIndigentId/{indigentId}

	@RequestMapping(
			path = "/indigent/{userId}/byIndigentId/{indigentId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary getIndigentIdById(
			@PathVariable("userId") String userId,
			@PathVariable("indigentId") String indigentId) {

		return this.indigentService.getIndigentIdById(userId, indigentId);
	}





	//********************************* GET ALL INDIGENT *********************************// ADMIN

	//	URL - http://localhost:8081/indigent/{userId}

	@RequestMapping(
			path = "/indigent/{userId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public IndigentBoundary[] getAllindIgents(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.indigentService.getAllindIgents(userId, size, page).toArray(new IndigentBoundary[0]);
	}





}
