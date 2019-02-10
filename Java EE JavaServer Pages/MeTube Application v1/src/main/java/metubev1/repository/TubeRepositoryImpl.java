package metubev1.repository;

import metubev1.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TubeRepositoryImpl implements TubeRepository {
    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("metubev1").createEntityManager();
    }

    @Override
    public Optional<Tube> findByName(String name) {
        try {

            Optional<Tube> tube = Optional.of(this.entityManager.createQuery("SELECT t FROM tubes t WHERE t.name=:name", Tube.class).setParameter("name", name).getSingleResult());

            return tube;
        } catch (NoResultException nre) {

            return Optional.empty();
        }

    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> tubes = this.entityManager.createQuery("SELECT t FROM tubes t", Tube.class).getResultList();
        this.entityManager.getTransaction().commit();

        return tubes;
    }

    @Override
    public Optional<Tube> findById(String id) {
        try {
            Optional<Tube> tube = Optional.of(this.entityManager.createQuery("SELECT t FROM tubes t WHERE t.id=:id", Tube.class).setParameter("id", id).getSingleResult());

            return tube;

        } catch (NoResultException nre) {

            return Optional.empty();
        }
    }
}
