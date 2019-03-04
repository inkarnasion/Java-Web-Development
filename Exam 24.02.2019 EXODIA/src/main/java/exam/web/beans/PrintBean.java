package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import exam.domain.models.binding.DocumentBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentViewModel;
import exam.service.DocumentService;

@Named
@RequestScoped
public class PrintBean extends BaseRedirectBean {
	private DocumentService documentService;
	private DocumentViewModel documentViewModel;
	private DocumentBindingModel documentBindingModel;

	public PrintBean() {
		super();
	}

	@Inject
	public PrintBean(DocumentService documentService, ModelMapper modelMapper) {
		super(modelMapper);

		this.documentService = documentService;
		this.initModel();
	}

	private void initModel() {
		this.documentBindingModel = new DocumentBindingModel();

		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		DocumentServiceModel userServiceModel = this.documentService.findByID(id);

		if (userServiceModel == null) {
			throw new IllegalArgumentException("The document does not exist");
		}
		this.documentViewModel = this.modelMapper.map(userServiceModel, DocumentViewModel.class);
	}

	public DocumentBindingModel getDocumentBindingModel() {
		return documentBindingModel;
	}

	public void setDocumentBindingModel(DocumentBindingModel documentBindingModel) {
		this.documentBindingModel = documentBindingModel;
	}

	public DocumentViewModel getDocumentViewModel() {
		return documentViewModel;
	}

	public void setDocumentViewModel(DocumentViewModel documentViewModel) {
		this.documentViewModel = documentViewModel;
	}

	public void print() throws IOException {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

		this.documentService.print(id);

		redirect("/home");
	}
}