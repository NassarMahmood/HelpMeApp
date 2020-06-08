package demo.dal;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import demo.data.SodalityEntity;
import demo.data.UserEntity;
import java.util.List;


public interface UserDao extends PagingAndSortingRepository<UserEntity, String>{


	UserEntity findByEmail(String Email);

	UserEntity findByEmailAndPassword(String Email, String Password);

	UserEntity findByUserIdAndSodality(String id, SodalityEntity sodality);
			
	List<UserEntity> findBySodality(SodalityEntity sodality, Pageable pageable);

	

}
