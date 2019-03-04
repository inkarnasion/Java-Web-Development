package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class UserLogOutBean extends BaseRedirectBean {

	public void logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		redirect("/");
	}
}
