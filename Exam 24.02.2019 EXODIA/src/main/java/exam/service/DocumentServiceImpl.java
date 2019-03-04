package exam.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;

public class DocumentServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	private final ModelMapper modelMapper;

	@Inject
	public DocumentServiceImpl(DocumentRepository userRepository, ModelMapper modelMapper) {
		this.documentRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<DocumentServiceModel> getAllDocuments() {
		List<DocumentServiceModel> allDocuments = this.documentRepository.findAll().stream().map(u -> this.modelMapper.map(u, DocumentServiceModel.class))
				.collect(Collectors.toList());

		return allDocuments;
	}

	@Override
	public DocumentServiceModel findByID(String id) {
		Document document = this.documentRepository.findById(id);

		if (document == null) {
			return null;
		}

		return this.modelMapper.map(document, DocumentServiceModel.class);
	}

	@Override
	public void delete(String id) {
		this.documentRepository.delete(id);
	}

	@Override
	public String schedule(DocumentServiceModel documentServiceModel) {
		String result;
		Document document = this.modelMapper.map(documentServiceModel, Document.class);

		if (this.documentRepository.save(document) == null) {
			result = "ERROR MESSAGE";
		} else {
			result = document.getId();
		}

		return result;
	}

	@Override
	public void print(String id) {
		delete(id);
	}
}