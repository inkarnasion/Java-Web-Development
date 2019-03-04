package exam.repository;

import exam.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

	User findByUserName(String userName);
}