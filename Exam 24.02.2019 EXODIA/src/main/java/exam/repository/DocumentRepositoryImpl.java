package exam.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import exam.domain.entities.Document;

public class DocumentRepositoryImpl implements DocumentRepository {

	private final EntityManager entityManager;

	@Inject
	public DocumentRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Document save(Document entity) {
		Document result;
		this.entityManager.getTransaction().begin();
		try {
			this.entityManager.persist(entity);
			this.entityManager.getTransaction().commit();

			result = entity;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());

			result = null;
		}
		return result;
	}

	@Override
	public Document update(Document entity) {
		Document result;
		this.entityManager.getTransaction().begin();
		try {
			Document updatedUser = this.entityManager.merge(entity);
			this.entityManager.getTransaction().commit();

			result = updatedUser;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
			result = null;
		}
		return result;
	}

	@Override
	public List<Document> findAll() {
		this.entityManager.getTransaction().begin();
		List<Document> users = this.entityManager.createQuery("SELECT u FROM Document u ", Document.class).getResultList();
		this.entityManager.getTransaction().commit();

		return users;
	}

	@Override
	public Document findById(String id) {
		Document result;
		this.entityManager.getTransaction().begin();
		try {
			Document user = this.entityManager.createQuery("SELECT u FROM Document u WHERE u.id = :id", Document.class).setParameter("id", id).getSingleResult();
			this.entityManager.getTransaction().commit();

			result = user;
		} catch (Exception e) {
			this.entityManager.getTransaction().rollback();
			System.out.println(e.getMessage());
			result = null;
		}
		return result;
	}

	@Override
	public void delete(String id) {
		this.entityManager.getTransaction().begin();
		this.entityManager.createQuery("DELETE FROM Document u WHERE u.id = :id").setParameter("id", id).executeUpdate();
		this.entityManager.getTransaction().commit();
	}
}
