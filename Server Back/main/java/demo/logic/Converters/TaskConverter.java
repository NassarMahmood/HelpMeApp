package demo.logic.Converters;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.data.TaskEntity;
import demo.rest.boundaries.TaskBoundary;
import demo.rest.error.UnvalidException;

@Component
public class TaskConverter {

	
	
	private UserConverter userConverter;
	private SodalityConverter sodalityConverter;
	private IndigentConverter indigentConverter;
	
	
	
	@Autowired
	public TaskConverter(UserConverter userConverter, SodalityConverter sodalityConverter, IndigentConverter indigentConverter) {
		
		this.userConverter = userConverter;
		this.sodalityConverter = sodalityConverter;
		this.indigentConverter = indigentConverter;
	}




	public TaskBoundary fromEntity(TaskEntity task) {


		try {

			return new TaskBoundary(
					task.getId(),
					task.getDate(),
					task.isTaskDone(),
					task.getEayDays(),
					task.getNotes(),
					this.userConverter.fromEntity(task.getVolunteer()),
					this.sodalityConverter.fromEntity(task.getSodality()),
					task.getIndigents().stream().map(this.indigentConverter :: fromEntity).collect(Collectors.toSet()));



		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert task from Entity to Boundary");
		}

	}



	public TaskEntity toEntity(TaskBoundary task) {


		try {

			return new TaskEntity(
					task.getId(), 
					task.getDate(),
					task.isTaskDone(),
					task.getEayDays(),
					task.getNotes(),
					this.userConverter.toEntity(task.getVolunteer()),
					this.sodalityConverter.toEntity(task.getSodality()),
					task.getIndigents().stream().map(this.indigentConverter:: toEntity).collect(Collectors.toSet()));


		} catch (Exception e) {
			throw new UnvalidException("Coulde not convert task from Boundary to Entity");
		}

	}

	
	
	
}
