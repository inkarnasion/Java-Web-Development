package metubev1.repository;

import metubev1.domain.entities.Tube;

import java.util.Optional;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Optional<Tube> findByName(String name);
}
