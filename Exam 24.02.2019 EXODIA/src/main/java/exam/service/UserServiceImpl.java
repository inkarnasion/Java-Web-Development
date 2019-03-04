package exam.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Inject
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean registerUser(UserServiceModel userServiceModel) {
		boolean result = true;
		User user = this.modelMapper.map(userServiceModel, User.class);
		user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

		if (this.userRepository.save(user) == null) {
			result = false;
		}

		return result;
	}

	@Override
	public UserServiceModel loginUser(UserServiceModel userServiceModel) {

		User user = this.userRepository.findByUserName(userServiceModel.getUsername());

		if (user == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(user.getPassword())) {
			return null;
		}

		return this.modelMapper.map(user, UserServiceModel.class);
	}

	@Override
	public List<UserServiceModel> getAllUsers() {
		List<UserServiceModel> allUsers = this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());

		return allUsers;
	}

	@Override
	public UserServiceModel findByID(String id) {
		User user = this.userRepository.findById(id);

		if (user == null) {
			return null;
		}

		return this.modelMapper.map(user, UserServiceModel.class);
	}


	@Override
	public void delete(String id) {
		this.userRepository.delete(id);
	}
}
