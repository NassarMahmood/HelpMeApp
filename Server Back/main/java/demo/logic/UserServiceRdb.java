package demo.logic;

import java.util.Date;
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
import demo.data.UserRole;
import demo.logic.Converters.UserConverter;

import demo.rest.boundaries.UserBoundary;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;


@Service
public class UserServiceRdb implements UserService{



	private UserDao userDao;
	private UserConverter userConverter;
	private Validator validator;
	private SodalityDao sodalityDao;
	private AddressDao addressDao;


	@Autowired
	public UserServiceRdb(UserDao userDao, UserConverter userConverter, Validator validator,
			SodalityDao sodalityDao, AddressDao addressDao) {

		super();
		this.userDao = userDao;
		this.userConverter = userConverter;
		this.validator = validator;
		this.sodalityDao = sodalityDao;
		this.addressDao = addressDao;
	}



	@Override
	@Transactional
	public UserBoundary userLogIn(String userEmail, String password) {


		UserEntity user = this.userDao.findByEmailAndPassword(userEmail, password);

		if(user == null || user.getActive() == false)
			throw new UnvalidException("no user with this email: "+userEmail);

		return this.userConverter.fromEntity(user);		
	}



	@Override
	@Transactional
	public UserBoundary userRegistration(UserBoundary user, String userId) {


		user.setActive(true);
		user.setSignUpTimestamp(new Date());

		this.validator.validateUser(user);

		this.checkExistUser(user);

		UserEntity newUser = this.userConverter.toEntity(user);

		newUser.setAddress(this.addressDao.save(newUser.getAddress()));

		return this.userConverter.fromEntity(this.userDao.save(newUser));


	}

	@Override
	@Transactional
	public UserBoundary addUser(UserBoundary user, String userId, Long sodalityId) {

		user.setActive(true);
		user.setSignUpTimestamp(new Date());

		this.validator.validateUser(user);

		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");

		this.checkExistUser(user);

		UserEntity newUser = this.userConverter.toEntity(user);

		newUser.setAddress(this.addressDao.save(newUser.getAddress()));

		SodalityEntity sodality = getSodalityById(sodalityId);

		sodality.addUser(newUser);
		//		sodality.getUsers().add(newUser);
		//		this.sodalityDao.save(sodality);

		UserEntity saved = this.userDao.save(newUser);
		this.sodalityDao.save(sodality);
		saved.setSodality(sodality);
		return this.userConverter.fromEntity(this.userDao.save(saved));

	}

	
	
	@Override
	@Transactional
	public void updateUser(UserBoundary user, String userId) {

		this.validator.validateUser(user);

		UserEntity userUpdate = this.userConverter.toEntity(user);
		UserEntity actionUser = this.getActiveUserById(userId);

		if(actionUser.getRole().equals(UserRole.VOLUNTEER) && !userUpdate.getUserId().equals(userId))
			throw new UnvalidException("user do not have a permistion for this action!");


		UserEntity existingUser = this.getActiveUserById(user.getUserId());

		existingUser.setFisrtName(userUpdate.getFisrtName());
		existingUser.setLastName(userUpdate.getLastName());
		existingUser.setBirthDate(userUpdate.getBirthDate());
		existingUser.setGender(userUpdate.getGender());
		existingUser.setPhone(userUpdate.getPhone());

		if(!existingUser.getAddress().equals(userUpdate.getAddress())) {

			AddressEntity temp = existingUser.getAddress();
			existingUser.setAddress(this.addressDao.save(userUpdate.getAddress()));
			this.addressDao.delete(temp);
		}

		if(!existingUser.getEmail().equals(userUpdate.getEmail())) {

			UserEntity emailUser = this.userDao.findByEmail(userUpdate.getEmail());

			if(emailUser != null)
				throw new UnvalidException("A user with the email: " + userUpdate.getEmail() + " already exists!");			
		}

		existingUser.setEmail(userUpdate.getEmail());
		existingUser.setPassword(userUpdate.getPassword());

		if(!existingUser.getRole().equals(userUpdate.getRole())){

			if(actionUser.getRole().equals(UserRole.ADMIN))
				existingUser.setRole(userUpdate.getRole());

			if(actionUser.getRole().equals(UserRole.SODALITYMANAGER) && !existingUser.getRole().equals(UserRole.ADMIN))
				existingUser.setRole(userUpdate.getRole());

		}

		this.userDao.save(existingUser);


	}



	@Override
	@Transactional
	public void deleteUser(UserBoundary user, String userId) {

		this.getActiveUserById(userId);

		UserEntity existingUser = this.getActiveUserById(user.getUserId());

		existingUser.setActive(false);

		this.userDao.save(existingUser);		
	}



	@Override
	@Transactional(readOnly = true)
	public UserBoundary getUserById(String userId, String searchUserId) {

		UserEntity actionUser = this.getActiveUserById(userId);

		UserEntity user = this.userDao.findById(searchUserId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + searchUserId));



		if(actionUser.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");				


		if(actionUser.getRole().equals(UserRole.SODALITYMANAGER) && !actionUser.getSodality().equals(user.getSodality()))
			throw new UnvalidException("user do not have a permistion for this action!");				


		return this.userConverter.fromEntity(user);		

	}




	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsersBySodality(String userId, Long sodalityId, int size, int page) {




		UserEntity actionUser = this.getActiveUserById(userId);
		SodalityEntity sodality = this.getSodalityById(sodalityId);

		if(actionUser.getRole().equals(UserRole.VOLUNTEER))
			throw new UnvalidException("user do not have a permistion for this action!");



		return this
				.userDao
				.findBySodality(sodality, PageRequest.of(page, size))
				.stream()
				.map(this.userConverter::fromEntity)
				.collect(Collectors.toList());


	}

	
	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(String userId, int size, int page) {

		UserEntity actionUser = this.getActiveUserById(userId);

		if(!actionUser.getRole().equals(UserRole.ADMIN))
			throw new UnvalidException("user do not have a permistion for this action!");

		return this
				.userDao.
				findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map(this.userConverter::fromEntity)	
				.collect(Collectors.toList());		

	}


	
	@Transactional(readOnly = true)
	private SodalityEntity getSodalityById(Long sodalityId) {

		return this.sodalityDao.findById(sodalityId).orElseThrow(()-> new NotFoundException("No sodality could be found with id: "+sodalityId));
	}


	@Transactional(readOnly = true)
	private UserEntity getActiveUserById(String userId) {

		UserEntity user =  this.userDao.findById(userId).orElseThrow(() -> new NotFoundException("No user could be found with id: " + userId));

		if(!user.getActive())
			throw new UnvalidException("no user with this id: "+userId);

		return user;
	}


	@Transactional(readOnly = true)
	private void checkExistUser(UserBoundary user) {


		boolean userExist = false;

		try {
			UserEntity existUser = this.userDao.findById(user.getUserId()).orElseThrow(() -> new NotFoundException("No user could be found with id: " + user.getUserId()));

			if(existUser.getActive() == true)
				userExist = true;

		} catch (Exception e) {
			System.err.println("It is all right the user does not exist");
		}

		if(userExist)
			throw new UnvalidException("A user with the id: " + user.getUserId() + " already exists!");


		UserEntity exist = this.userDao.findByEmail(user.getEmail());

		if(exist != null && !exist.getUserId().equals(user.getUserId()))
			throw new UnvalidException("A user with the email: " + user.getEmail() + " already exists!");

	}



}
