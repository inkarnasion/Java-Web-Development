package metubev1.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <E,K>{
    E save(E e);

    List<E> findAll();

   Optional<E> findById(K id);
}
