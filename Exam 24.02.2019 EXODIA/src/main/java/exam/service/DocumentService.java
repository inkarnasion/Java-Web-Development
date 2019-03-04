package exam.service;

import java.util.List;

import exam.domain.models.service.DocumentServiceModel;

public interface DocumentService {
	DocumentServiceModel findByID(String id);

	List<DocumentServiceModel> getAllDocuments();

	void delete(String id);

	String schedule(DocumentServiceModel documentServiceModel);

	void print(String id);
}
