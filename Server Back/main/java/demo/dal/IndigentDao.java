package demo.dal;


import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import demo.data.IndigentEntity;
import demo.data.SodalityEntity;

public interface IndigentDao extends PagingAndSortingRepository<IndigentEntity, String>{

	

	
	Stream<IndigentEntity> findByFisrtNameAndSodality(String firstName , SodalityEntity sodality, Pageable pageable);

	
	Stream<IndigentEntity> findBySodality(SodalityEntity sodality, Pageable pageable);

	
}
