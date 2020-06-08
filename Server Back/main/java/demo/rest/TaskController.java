package demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.logic.TaskService;
import demo.rest.boundaries.TaskBoundary;

@RestController
public class TaskController {



	private TaskService taskService;



	@Autowired
	public TaskController(TaskService taskService) {

		this.taskService = taskService;
	}








	//**************** ADD TASK ****************

	//	URL - http://localhost:8081/task/{userId}

	@RequestMapping(
			path = "/task/{userId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public TaskBoundary addTask(
			@PathVariable("userId") String userId,
			@RequestBody TaskBoundary task) {

		return this.taskService.addTask(userId, task);
	}




	//**************** UPDATE TASK ****************

	//	URL - http://localhost:8081/task/{userId}

	@RequestMapping(
			path = "/task/{userId}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateTask(
			@PathVariable("userId") String userId,
			@RequestBody TaskBoundary task) {

		this.taskService.updateTask(userId,task);
	}





	//**************** DELETE TASK ****************

	//	URL - http://localhost:8081/task/{userId}

	@RequestMapping(
			path = "/task/{userId}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteTask(
			@PathVariable("userId") String userId,
			@RequestBody TaskBoundary task) {

		this.taskService.deleteTask(userId, task);
	}




	//**************** GET TASK BY ID ****************

	//	URL - http://localhost:8081/task/{userId}/byTaskId/{taskId}

	@RequestMapping(
			path = "/task/{userId}/byTaskId/{taskId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskBoundary getTaskById(
			@PathVariable("userId") String userId,
			@PathVariable("taskId") Long taskId) {

		return this.taskService.getTaskById(userId, taskId);
	}




	//**************** GET ACTIVE TASKS BY VOLUNTEER ID ****************

	//	URL - http://localhost:8081/task/{userId}/byVolunteerId/{VolunteerId}

	@RequestMapping(
			path = "/task/{userId}/byVolunteerId/{VolunteerId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskBoundary[] getActiveTasksByVolunteerId(
			@PathVariable("userId") String userId,
			@PathVariable("VolunteerId") String VolunteerId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.taskService.getTasksByVolunteerId(userId, VolunteerId, page, size).toArray(new TaskBoundary[0]);
	}




	//**************** GET ACTIVE TASKS BY SODALITY ID ****************

	//	URL - http://localhost:8081/task/{userId}/bySodalityId/{SodalityId}

	@RequestMapping(
			path = "/task/{userId}/bySodalityId/{SodalityId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskBoundary[] getActiveTasksBySodalityId(
			@PathVariable("userId") String userId,
			@PathVariable("SodalityId") Long SodalityId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.taskService.getTasksBySodalityId(userId, SodalityId, page, size).toArray(new TaskBoundary[0]);
	}




	//**************** GET ALL TASKS ****************

	//	URL - http://localhost:8081/task/{userId}/all

	@RequestMapping(
			path = "/task/{userId}/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public TaskBoundary[] getAllTasks(
			@PathVariable("userId") String userId,
			@RequestParam(name="size", required = false, defaultValue = "20") int size,
			@RequestParam(name="page", required = false, defaultValue = "0") int page) {

		return this.taskService.getAllTasks(userId, page, size).toArray(new TaskBoundary[0]);
	}







}
