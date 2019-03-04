package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;

import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;

@Named
@RequestScoped
public class UserLoginBean extends BaseRedirectBean {
	private UserService userService;
	private UserLoginBindingModel userLoginBindingModel;

	public UserLoginBean() {
	}

	@Inject
	public UserLoginBean(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
		this.initModel();
	}

	private void initModel() {
		this.userLoginBindingModel = new UserLoginBindingModel();
	}

	public UserLoginBindingModel getUserLoginBindingModel() {
		return userLoginBindingModel;
	}

	public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
		this.userLoginBindingModel = userLoginBindingModel;
	}

	public void login() throws IOException {
		UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));

		if (userServiceModel == null) {
			FacesContext.getCurrentInstance().addMessage("login-form:user-name", new FacesMessage("Invalid username or password", "Invalid username or password - detailed"));

//			redirect("/login");
		} else {

			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			httpSession.setAttribute("username", userServiceModel.getUsername());
			httpSession.setAttribute("userId", userServiceModel.getId());

			redirect("/home");
		}
	}

	public void valudateUserName(FacesContext context, UIComponent component, Object value) {
		UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));

	}
}
