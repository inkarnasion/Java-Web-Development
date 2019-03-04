package exam.web.beans;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import exam.domain.models.binding.DocumentBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.service.DocumentService;

@Named
@RequestScoped
public class UserScheduleBean extends BaseRedirectBean {
	private DocumentService documentService;
	private DocumentBindingModel documentBindingModel;

	public UserScheduleBean() {
		super();
	}

	@Inject
	public UserScheduleBean(DocumentService documentService, ModelMapper modelMapper) {
		super(modelMapper);

		this.documentService = documentService;
		this.initModel();
	}

	private void initModel() {
		this.documentBindingModel = new DocumentBindingModel();
	}

	public DocumentBindingModel getDocumentBindingModel() {
		return documentBindingModel;
	}

	public void setDocumentBindingModel(DocumentBindingModel documentBindingModel) {
		this.documentBindingModel = documentBindingModel;
	}

	public void schedule() throws IOException {
		DocumentServiceModel documentServiceModel = this.modelMapper.map(documentBindingModel, DocumentServiceModel.class);
		String documentId = this.documentService.schedule(documentServiceModel);

		if ("ERROR MESSAGE".equals(documentId)) {
			throw new IllegalArgumentException("The document cannot be scheduled");
		}

		redirect("/details/" + documentId);
	}
}