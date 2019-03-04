package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import exam.domain.models.binding.UserRegistrationBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;

@Named
@RequestScoped
public class UserRegisterBean extends BaseRedirectBean {
	private UserService userService;
	private UserRegistrationBindingModel userRegistrationBindingModel;

	public UserRegisterBean() {
		super();
	}

	@Inject
	public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
		super(modelMapper);

		this.userService = userService;
		this.initModel();
	}

	private void initModel() {
		this.userRegistrationBindingModel = new UserRegistrationBindingModel();
	}

	public UserRegistrationBindingModel getUserRegistrationBindingModel() {
		return userRegistrationBindingModel;
	}

	public void setUserRegistrationBindingModel(UserRegistrationBindingModel userRegistrationBindingModel) {
		this.userRegistrationBindingModel = userRegistrationBindingModel;
	}

	public void register() throws IOException {
		if (!userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
			throw new IllegalArgumentException("Password do not match!");
		}
		UserServiceModel userServiceModel = this.modelMapper.map(userRegistrationBindingModel, UserServiceModel.class);

		if (!this.userService.registerUser(userServiceModel)) {
			throw new IllegalArgumentException("The user can not be persist - check if all fields are entered or check into database if user already exist!");
		}

		redirect("/login");
	}
}