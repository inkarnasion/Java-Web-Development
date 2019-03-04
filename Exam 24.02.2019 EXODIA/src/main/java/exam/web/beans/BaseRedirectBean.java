package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

@Named
@RequestScoped
public class BaseRedirectBean {
	protected ModelMapper modelMapper;


	protected void redirect(String path) throws IOException {
		ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		ctx.redirect(ctx.getRequestContextPath() + path);
	}

	@Inject
	public BaseRedirectBean(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public BaseRedirectBean() {
	}
}