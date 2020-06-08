package demo.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import demo.data.AddressEntity;

public interface AddressDao extends PagingAndSortingRepository<AddressEntity, Long>{

}
