package demo.rest;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.logic.UserService;
import demo.rest.boundaries.UserBoundary;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;





@RestController
public class UserController {




	private UserService userService;





	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}







	//********************************* LOG IN *********************************

	// URL - http://localhost:8081/user/login/{userEmail}/{password}

	@RequestMapping(
			path = "/user/login/{userEmail}/{password}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary userLogin(
			@PathVariable("userEmail") String userEmail,
			@PathVariable("password") String password) {

		
		return this.userService.userLogIn(userEmail, password);
	}









	//********************************* REGISTRATION *********************************

	// URL - http://localhost:8081/user/registration/{userId}

	@RequestMapping(
			path = "/user/registration/{userId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary userRegistration(
			@PathVariable("userId") String userId,
			@RequestBody UserBoundary newUser) {


		return this.userService.userRegistration(newUser, userId);
	}











	//********************************* ADD USER *********************************

	//	URL - http://localhost:8081/user/{userId}/{sodalityId}

	@RequestMapping(
			path = "/user/{userId}/{sodalityId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary addUser(
			@PathVariable("userId") String userId,
			@PathVariable("sodalityId") Long sodalityId,
			@RequestBody UserBoundary newUser) {

		return this.userService.addUser(newUser, userId, sodalityId);
	}





	//********************************* UPDATE USER *********************************

	// URL - http://localhost:8081/user/{userId}

	@RequestMapping(
			path = "/user/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(
			@PathVariable("userId") String userId,
			@RequestBody UserBoundary updateUser) {

		this.userService.updateUser(updateUser, userId);
	}





	//********************************* DELETE USER *********************************// ADMIN

	// URL - http://localhost:8081/user/{userId}

	@RequestMapping(
			path = "/user/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(
			@PathVariable("userId") String userId,
			@RequestBody UserBoundary deleteUser) {

		this.userService.deleteUser(deleteUser, userId);
	}






	//********************************* GET USER BY ID ********************************* // ADMIN

	//	URL - http://localhost:8081/user/{userId}/byUserId/{searchUserId}

	@RequestMapping(
			path = "/user/{userId}/byUserId/{searchUserId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary getUserById(
			@PathVariable("userId") String userId,
			@PathVariable("searchUserId") String searchUserId) {

		return this.userService.getUserById(userId, searchUserId);

	}








	//********************************* GET ALL USERS BY SODALITY *********************************

	//	URL - http://localhost:8081/user/{userId}/{sodalityId}

	@RequestMapping(
			path = "/user/{userId}/{sodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUsersBySodality(
			@PathVariable("userId") String userId,
			@PathVariable("sodalityId") Long sodalityId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.userService.getAllUsersBySodality(userId, sodalityId, size, page).toArray(new UserBoundary[0]);
	}





	//********************************* GET ALL USERS ********************************* // ADMIN

	//	URL - http://localhost:8081/user/{userId}/all
	@RequestMapping(
			path = "/user/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBoundary[] getAllUsers(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.userService.getAllUsers(userId, size, page).toArray(new UserBoundary[0]);
	}







	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleUserNotFoundException(NotFoundException e) {

		String message = e.getMessage();
		if (message == null) {
			message = "user could not be found";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message);

		return errorMessage;
	}


	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleUserUnvalidException(UnvalidException e) {

		String message = e.getMessage();
		if (message == null) {
			message = "Unvalid user";
		}

		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("error", message); 

		return errorMessage;
	}











}
