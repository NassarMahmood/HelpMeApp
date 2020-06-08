package demo.logic;

import java.util.List;

import demo.rest.boundaries.UserBoundary;

public interface UserService {

	public UserBoundary userLogIn(String userEmail, String password);

	public UserBoundary userRegistration(UserBoundary newUser, String userId);

	public UserBoundary addUser(UserBoundary newUser, String userId, Long sodalityId);

	public void updateUser(UserBoundary updateUser, String userId);

	public void deleteUser(UserBoundary deleteUser, String userId);

	public UserBoundary getUserById(String userId, String searchUserId);

	public List<UserBoundary> getAllUsersBySodality(String userId, Long sodalityId, int size, int page);

	public List<UserBoundary> getAllUsers(String userId, int size, int page);




}
