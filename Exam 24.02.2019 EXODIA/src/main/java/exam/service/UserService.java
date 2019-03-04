package exam.service;

import java.util.List;

import exam.domain.models.service.UserServiceModel;

public interface UserService {
	boolean registerUser(UserServiceModel userServiceModel);

	UserServiceModel loginUser(UserServiceModel userServiceModel);

	UserServiceModel findByID(String id);

	List<UserServiceModel> getAllUsers();



	void delete(String id);
}
