package demo.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dal.JoiningRequestsDao;
import demo.dal.UserDao;
import demo.data.JoiningRequestsEntity;
import demo.data.UserEntity;
import demo.logic.Converters.JoiningRequestConverter;
import demo.rest.boundaries.JoiningRequestsBoundary;
import demo.rest.error.NotFoundException;
import demo.rest.error.UnvalidException;

@Service
public class JoiningRequestsServiceRdb implements JoiningRequestsService{


	private JoiningRequestsDao joiningRequestsDao;
	private JoiningRequestConverter joiningRequestConverter;
	private UserDao userDao;


	@Autowired
	public JoiningRequestsServiceRdb(JoiningRequestsDao joiningRequestsDao, JoiningRequestConverter joiningRequestConverter, UserDao userDao) {

		this.joiningRequestsDao = joiningRequestsDao;
		this.joiningRequestConverter = joiningRequestConverter;
		this.userDao = userDao;
	}



	@Override
	@Transactional
	public JoiningRequestsBoundary addJioningRequest(String userId, JoiningRequestsBoundary request) {

		UserEntity user = this.getActiveUserById(request.getUser().getUserId());
		
		request.setAcceptable(false);
		request.setRequestDate(new Date());

		if(user.isHaveRequest() == true)
			throw new UnvalidException("you are already sent a join request..!");
		
		user.setHaveRequest(true);
		this.userDao.save(user);

		return this.joiningRequestConverter.fromEntity(this.joiningRequestsDao.save(this.joiningRequestConverter.toEntity(request)));
	}


	@Override
	@Transactional
	public void updateJioningRequest(String userId, JoiningRequestsBoundary request) {

		UserEntity user = this.getActiveUserById(request.getUser().getUserId());

		JoiningRequestsEntity exist = this.getJoinRequestById(request.getId());

		exist.setAcceptable(true);
		user.setSodality(exist.getSodality());

		this.userDao.save(user);
		this.joiningRequestsDao.save(exist);

	}

	@Override
	@Transactional
	public void deleteJioningRequest(String userId, JoiningRequestsBoundary request) {

		this.joiningRequestsDao.delete(this.joiningRequestConverter.toEntity(request));


	}

	@Override
	@Transactional(readOnly = true)
	public List<JoiningRequestsBoundary> getAllJioningRequest(String userId, int page, int size) {


		return this
				.joiningRequestsDao
				.findAll(PageRequest.of(page, size)).stream()
				.map(this.joiningRequestConverter::fromEntity)
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
	private JoiningRequestsEntity getJoinRequestById(Long requestId) {

		return this.joiningRequestsDao.findById(requestId).orElseThrow(() -> new NotFoundException("No Join Request could be found with id: " + requestId));

	}

}
