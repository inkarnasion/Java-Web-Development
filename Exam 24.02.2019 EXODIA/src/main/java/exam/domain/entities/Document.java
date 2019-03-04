package exam.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document extends BaseEntity {

	@Column(name = "title", nullable = false, unique = true, updatable = false)
	private String title;


	@Column(name = "content", columnDefinition = "TEXT",length = 100000, nullable = false)
	private String content;

	public Document() {
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