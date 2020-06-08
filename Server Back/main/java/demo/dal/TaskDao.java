package demo.dal;


import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import demo.data.SodalityEntity;
import demo.data.TaskEntity;
import demo.data.UserEntity;

public interface TaskDao extends PagingAndSortingRepository<TaskEntity, Long>{

	Stream<TaskEntity> findBySodality(SodalityEntity sodality, Pageable pageable);


	Stream<TaskEntity> findByVolunteer(UserEntity volunteer, Pageable pageable);

}
