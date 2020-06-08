package demo.logic;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dal.AddressDao;
import demo.dal.IndigentDao;
import demo.dal.SodalityDao;
import demo.dal.UserDao;
import demo.data.AddressEntity;
import demo.data.IndigentEntity;
import demo.data.SodalityEntity;
import demo.data.UserEntity;
import demo.data.UserRole;
import demo.logic.Converters.IndigentConverter;
import demo.rest.boundaries.IndigentBoundary;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;

@Service
public class IndigentServiceRdb implements IndigentService{


	private IndigentDao indigentDao;
	private IndigentConverter indigentConverter;
	private Validator validator;

	private UserDao userDao;
	private SodalityDao sodalityDao;
	private AddressDao addressDao;



	@Autowired
	public IndigentServiceRdb(IndigentDao indigentDao, IndigentConverter indigentConverter, Validator validator, SodalityDao sodalityDao, UserDao userDao, AddressDao addressDao) {

		this.indigentDao = indigentDao;
		this.indigentConverter = indigentConverter;
		this.validator = validator;
		this.userDao = userDao;
		this.sodalityDao = sodalityDao;
		this.addressDao = addressDao;
	}





	@Override
	@Transactional
	public IndigentBoundary addIndigent(String userId, IndigentBoundary newIndigent) {

		newIndigent.setActive(true);
		newIndigent.setSignUpTimestamp(new Date());
		this.validator.validateIndigent(newIndigent);

		this.getActiveUserById(userId);
		if(newIndigent.getSodality() != null)
			this.getSodalityById(newIndigent.getSodality().getId());

		this.checkExistIndigent(newIndigent);

		IndigentEntity i = this.indigentConverter.toEntity(newIndigent);

		i.setAddress(this.addressDao.save(i.getAddress()));

		return this.indigentConverter.fromEntity(this.indigentDao.save(i));

	}

	@Override
	@Transactional
	public void updateIndigent(String userId, IndigentBoundary updateIndigent) {

		this.validator.validateIndigent(updateIndigent);

		this.getActiveUserById(userId);

		IndigentEntity indignet = this.indigentConverter.toEntity(updateIndigent);

		IndigentEntity existIndigent = this.getIndigentById(updateIndigent.getIndigentId());

		existIndigent.setFisrtName(indignet.getFisrtName());
		existIndigent.setLastName(indignet.getLastName());
		existIndigent.setBirthdate(indignet.getBirthdate());
		existIndigent.setPhone(indignet.getPhone());

		if(!existIndigent.getAddress().equals(indignet.getAddress())) {

			AddressEntity temp = existIndigent.getAddress();
			existIndigent.setAddress(this.addressDao.save(indignet.getAddress()));
			this.addressDao.delete(temp);
		}

		existIndigent.setEatDays(indignet.getEatDays());

		if(!existIndigent.getNotes().equals(indignet.getNotes()))
			existIndigent.setNotes((indignet.getNotes()));

		this.indigentDao.save(existIndigent);
	}

	@Override
	@Transactional
	public void deletedIndigent(String userId, IndigentBoundary deletedIndigent) {

		this.validator.validateIndigent(deletedIndigent);

		UserEntity user = this.getActiveUserById(userId);


		if(user.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");

		IndigentEntity indignet = this.getIndigentById(deletedIndigent.getIndigentId());

		indignet.setActive(false);

		this.indigentDao.save(indignet);

	}



	@Override
	@Transactional(readOnly = true)
	public IndigentBoundary getIndigentByIdAndSodalityId(String userId, String indigentId, Long sodalityId) {


		UserEntity user = this.getActiveUserById(userId);
		SodalityEntity sodality = this.getSodalityById(sodalityId);

		IndigentEntity indigent = this.getIndigentById(indigentId);

		if(indigent.getSodality() != null && !indigent.getSodality().equals(sodality))
			throw new UnvalidException("The indigent doesn't belongs to this sodality..!");

		if(!user.getRole().equals(UserRole.ADMIN) && !user.getSodality().equals(sodality))
			throw new UnvalidException("The user doesn't belongs to this sodality..!");

		return this.indigentConverter.fromEntity(indigent);

	}



	@Override
	@Transactional(readOnly = true)
	public List<IndigentBoundary> getIndigentsByFirstNameAndSodalityId(String userId, String indigentFisrtName,
			Long sodalityId, int size, int page) {

		SodalityEntity sodality = this.getSodalityById(sodalityId);
		UserEntity user = this.getActiveUserById(userId);

		if(!user.getRole().equals(UserRole.ADMIN) && !user.getSodality().equals(sodality))
			throw new UnvalidException("The user doesn't belongs to this sodality..!");


		return this
				.indigentDao
				.findByFisrtNameAndSodality(indigentFisrtName, sodality, PageRequest.of(page, size))
				.map(this.indigentConverter:: fromEntity)
				.collect(Collectors.toList());

	}


	@Override
	@Transactional(readOnly = true)
	public List<IndigentBoundary> getAllIndigentsBySodalityId(String userId, Long sodalityId, int size, int page) {


		SodalityEntity sodality = this.getSodalityById(sodalityId);
		UserEntity user = this.getActiveUserById(userId);

		if(!user.getRole().equals(UserRole.ADMIN) && !user.getSodality().equals(sodality))
			throw new UnvalidException("The user doesn't belongs to this sodality..!");


		return this
				.indigentDao
				.findBySodality(sodality, PageRequest.of(page, size))
				.map(this.indigentConverter:: fromEntity)
				.collect(Collectors.toList());		
	}




	@Override
	@Transactional(readOnly = true)
	public IndigentBoundary getIndigentIdById(String userId, String indigentId) {



		UserEntity user = this.getActiveUserById(userId);

		if(!user.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");


		return this.indigentConverter.fromEntity(this.getIndigentById(indigentId));

	}

	@Override
	@Transactional(readOnly = true)
	public List<IndigentBoundary> getAllindIgents(String userId, int size, int page) {


		UserEntity user = this.getActiveUserById(userId);

		if(!user.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		return this
				.indigentDao
				.findAll(PageRequest.of(page, size))
				.stream()
				.map(this.indigentConverter:: fromEntity)
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
	private IndigentEntity getIndigentById(String indigentId) {

		return this.indigentDao.findById(indigentId).orElseThrow(()-> new NotFoundException("No indigent could be found with id: "+indigentId));
	}

	@Transactional(readOnly = true)
	private SodalityEntity getSodalityById(Long sodalityId) {

		return this.sodalityDao.findById(sodalityId).orElseThrow(()-> new NotFoundException("No sodality could be found with id: "+sodalityId));
	}


	@Transactional(readOnly = true)
	private void checkExistIndigent(IndigentBoundary indigent) {


		boolean indigenExist = false;

		try {
			IndigentEntity existIndigent = this.indigentDao.findById(indigent.getIndigentId()).orElseThrow(() -> new NotFoundException("No indigent could be found with id: " + indigent.getIndigentId()));

			if(existIndigent.getActive() == true)
				indigenExist = true;

		} catch (Exception e) {
			System.err.println("It is all right the user does not exist");
		}

		if(indigenExist)
			throw new UnvalidException("A indigent with the id: " + indigent.getIndigentId() + " already exists!");
	}









}
