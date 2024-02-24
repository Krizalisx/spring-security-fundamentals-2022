package spring.security.fundamentals.authorization_server.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.fundamentals.authorization_server.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByClientId(String clientId);

}
