package demo.logic;

import java.util.List;

import demo.rest.boundaries.TaskBoundary;

public interface TaskService {

	public TaskBoundary addTask(String userId, TaskBoundary task);

	public void updateTask(String userId, TaskBoundary task);

	public void deleteTask(String userId, TaskBoundary task);

	public TaskBoundary getTaskById(String userId, Long taskId);

	public List<TaskBoundary> getTasksByVolunteerId(String userId, String volunteerId, int page, int size);

	public List<TaskBoundary> getTasksBySodalityId(String userId, Long sodalityId, int page, int size);

	public List<TaskBoundary> getAllTasks(String userId, int page, int size);

	
}
