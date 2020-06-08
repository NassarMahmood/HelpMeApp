package demo.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dal.AddressDao;
import demo.dal.SodalityDao;
import demo.dal.UserDao;
import demo.data.AddressEntity;
import demo.data.SodalityEntity;
import demo.data.UserEntity;
import demo.logic.Converters.SodalityConverter;
import demo.rest.boundaries.SodalityBoundary;
import demo.rest.boundaries.UserRole;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;

@Service
public class SodalityServiceRdb implements SodalityService{


	private SodalityDao sodalityDao;
	private SodalityConverter sodalityConverter;
	private Validator validator;
	private UserDao userDao;
	private AddressDao addressDao;


	@Autowired
	public SodalityServiceRdb(SodalityDao sodalityDao, SodalityConverter sodalityConverter, Validator validator, UserDao userDao, AddressDao addressDao) {

		this.sodalityDao = sodalityDao;
		this.sodalityConverter = sodalityConverter;
		this.validator = validator;
		this.userDao = userDao;
		this.addressDao = addressDao;
	}



	@Override
	@Transactional
	public SodalityBoundary addSodality(String userId, SodalityBoundary sodality) {

		this.validator.validateSodality(sodality);

		SodalityEntity newSodality = this.sodalityConverter.toEntity(sodality);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");

		if(actionUser.getRole().equals(UserRole.SODALITYMANAGER) && actionUser.getSodality() != null)
			throw new UnvalidException("The user have sodality!");

		newSodality.addUser(actionUser);
		newSodality.setAddress(this.addressDao.save(newSodality.getAddress()));

		return this.sodalityConverter.fromEntity(this.sodalityDao.save(newSodality));
	}

	@Override
	@Transactional
	public void updateSodality(String userId, SodalityBoundary sodality) {

		this.validator.validateSodality(sodality);

		SodalityEntity updateSodality = this.sodalityConverter.toEntity(sodality);

		UserEntity actionUser = this.getActiveUserById(userId);

		SodalityEntity existingSodality = this.getSodalityById(sodality.getId());

		if(actionUser.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");

		if(actionUser.getRole().equals(UserRole.SODALITYMANAGER) && actionUser.getSodality().equals(existingSodality))
			throw new UnvalidException("The user is not an manager to this sodality!");


		existingSodality.setName(updateSodality.getName());


		if(!existingSodality.getAddress().equals(updateSodality.getAddress())) {

			AddressEntity temp = existingSodality.getAddress();
			existingSodality.setAddress(this.addressDao.save(updateSodality.getAddress()));
			this.addressDao.delete(temp);
		}

		existingSodality.setPhone(updateSodality.getPhone());
		existingSodality.setEmail(updateSodality.getEmail());
		existingSodality.setWebSite(updateSodality.getWebSite());

		this.sodalityDao.save(existingSodality);		
	}



	@Override
	@Transactional
	public void deleteSodality(String userId, SodalityBoundary sodality) {

		this.validator.validateSodality(sodality);

		UserEntity user = this.getActiveUserById(userId);

		SodalityEntity existingSodality = this.getSodalityById(sodality.getId());

		if(user.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("The user do not have a permistion for this action!");

		if(user.getRole().equals(UserRole.SODALITYMANAGER) && user.getSodality().equals(existingSodality))
			throw new UnvalidException("The user is not the manager of this sodality!");

		// TODO delete the sodality from the database...
	}



	@Override
	@Transactional(readOnly = true)
	public SodalityBoundary getSodalityById(String userId, Long sodalityId) {

		this.getActiveUserById(userId);

		return this.sodalityConverter.fromEntity(this.getSodalityById(sodalityId));
	}

	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<SodalityBoundary> getAllSodalities(String userId, int page, int size) {

		this.getActiveUserById(userId);

		return this
				.sodalityDao
				.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.sodalityConverter:: fromEntity)
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
	private SodalityEntity getSodalityById(Long sodalityId) {

		return this.sodalityDao.findById(sodalityId).orElseThrow(() -> new NotFoundException("No sodality could be found with id: " + sodalityId));
	}

}
