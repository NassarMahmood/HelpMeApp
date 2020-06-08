package demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import demo.data.JoiningRequestsEntity;

public interface JoiningRequestsDao extends PagingAndSortingRepository<JoiningRequestsEntity, Long>{

}
