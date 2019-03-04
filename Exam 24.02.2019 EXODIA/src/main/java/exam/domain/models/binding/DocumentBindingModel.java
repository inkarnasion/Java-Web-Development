package exam.domain.models.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentBindingModel {
	private String id;


	private String title;


	private String content;

	public DocumentBindingModel() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}