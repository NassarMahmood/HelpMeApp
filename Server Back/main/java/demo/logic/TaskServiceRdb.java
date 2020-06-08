package demo.logic;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dal.SodalityDao;
import demo.dal.TaskDao;
import demo.dal.UserDao;
import demo.data.SodalityEntity;
import demo.data.TaskEntity;
import demo.data.UserEntity;
import demo.data.UserRole;
import demo.logic.Converters.TaskConverter;
import demo.rest.boundaries.IndigentBoundary;
import demo.rest.boundaries.SodalityBoundary;
import demo.rest.boundaries.TaskBoundary;
import demo.rest.boundaries.UserBoundary;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;

@Service
public class TaskServiceRdb implements TaskService{


	private TaskDao taskDao;
	private TaskConverter taskConverter;
	private Validator validator;
	private UserDao userDao;
	private SodalityDao sodalityDao;




	@Autowired	
	public TaskServiceRdb(TaskDao taskDao, TaskConverter taskConverter, Validator validator, UserDao userDao, SodalityDao sodalityDao) {

		super();
		this.taskDao = taskDao;
		this.taskConverter = taskConverter;
		this.validator = validator;
		this.userDao = userDao;
		this.sodalityDao = sodalityDao;
	}




	@Override
	@Transactional
	public TaskBoundary addTask(String userId, TaskBoundary task) {

		UserEntity user = this.getActiveUserById(userId);

		if(user.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");
		
		task.setDate(new Date());
		task.setTaskDone(false);

		return this.taskConverter.fromEntity(this.taskDao.save(this.taskConverter.toEntity(task)));
	}



	@Override
	@Transactional
	public void updateTask(String userId, TaskBoundary task) {

		UserEntity user = this.getActiveUserById(userId);

		if(user.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");	

		TaskEntity existTask = getTaskById(task.getId());
		TaskEntity updateTask = this.taskConverter.toEntity(task);


		existTask.setTaskDone(updateTask.isTaskDone());
		existTask.setEayDays(updateTask.getEayDays());
		existTask.setNotes(updateTask.getNotes());
		existTask.setVolunteer(updateTask.getVolunteer());
		existTask.setSodality(updateTask.getSodality());
		existTask.setIndigents(updateTask.getIndigents());

		this.taskDao.save(existTask);

	}

	@Override
	@Transactional
	public void deleteTask(String userId, TaskBoundary task) {

		UserEntity user = this.getActiveUserById(userId);

		if(user.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");


		this.taskDao.delete(this.taskConverter.toEntity(task));

	}


	@Override
	@Transactional(readOnly = true)
	public TaskBoundary getTaskById(String userId, Long taskId) {

		this.getActiveUserById(userId);

		return this.taskConverter.fromEntity(this.getTaskById(taskId));

	}




	@Override
	@Transactional(readOnly = true)
	public List<TaskBoundary> getTasksByVolunteerId(String userId, String volunteerId, int page, int size) {

		this.getActiveUserById(userId);

		return this.taskDao.findByVolunteer(this.getActiveUserById(volunteerId), PageRequest.of(page, size))
				.map(this.taskConverter::fromEntity).collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public List<TaskBoundary> getTasksBySodalityId(String userId, Long sodalityId, int page, int size) {

		this.getActiveUserById(userId);

		return this
				.taskDao
				.findBySodality(this.getSodalityById(sodalityId), PageRequest.of(page, size))
				.map(this.taskConverter::fromEntity)
				.collect(Collectors.toList());
	}




	@Override
	@Transactional(readOnly = true)
	public List<TaskBoundary> getAllTasks(String userId, int page, int size) {

		this.getActiveUserById(userId);

		return this
				.taskDao
				.findAll(PageRequest.of(page, size))
				.stream()
				.map(this.taskConverter:: fromEntity)
				.collect(Collectors.toList());
	}	



	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new UnvalidException("no user with this id: "+userId);

		return user;
	}



	@Transactional(readOnly = true)
	private TaskEntity getTaskById(Long taskId) {

		return this.taskDao.findById(taskId).orElseThrow(() -> new NotFoundException("No task could be found with id: " + taskId));

	}


	@Transactional(readOnly = true)
	private SodalityEntity getSodalityById(Long sodalityId) {

		return this.sodalityDao.findById(sodalityId).orElseThrow(()-> new NotFoundException("No sodality could be found with id: "+sodalityId));
	}


}
