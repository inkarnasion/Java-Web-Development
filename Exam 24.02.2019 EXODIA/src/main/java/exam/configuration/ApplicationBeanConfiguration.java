package exam.configuration;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.modelmapper.ModelMapper;

public class ApplicationBeanConfiguration {

	@Produces
	public EntityManager entityManager() {
		return Persistence.createEntityManagerFactory("Exam").createEntityManager();
	}

	@Produces
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
