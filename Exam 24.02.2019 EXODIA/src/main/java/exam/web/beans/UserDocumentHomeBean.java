package exam.web.beans;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import exam.domain.models.view.DocumentViewModel;
import exam.service.DocumentService;

@Named
@RequestScoped
public class UserDocumentHomeBean extends BaseRedirectBean {
	private List<DocumentViewModel> documents;
	private DocumentService documentService;
	private ModelMapper modelMapper;

	public UserDocumentHomeBean() {
	}

	@Inject
	public UserDocumentHomeBean(DocumentService documentService, ModelMapper modelMapper) {
		this.documentService = documentService;
		this.modelMapper = modelMapper;
		this.initModels();
	}

	private void initModels() {
		this.documents = this.documentService.getAllDocuments().stream().map(u -> this.modelMapper.map(u, DocumentViewModel.class)).collect(Collectors.toList());
		documents.forEach(d -> d.setTitle(d.getTitle().length() > 12 ? d.getTitle().substring(0, 12) + "..." : d.getTitle()));
	}

	public List<DocumentViewModel> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentViewModel> documents) {
		this.documents = documents;
	}

	public void print(String documentId) throws IOException {
		redirect("/print/" + documentId);
	}

	public void details(String documentId) throws IOException {
		redirect("/details/" + documentId);
	}
}