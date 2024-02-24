package spring.security.fundamentals.authorization_server.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.fundamentals.authorization_server.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
