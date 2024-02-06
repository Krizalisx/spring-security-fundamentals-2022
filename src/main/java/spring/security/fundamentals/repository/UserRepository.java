package spring.security.fundamentals.repository;

import jakarta.persistence.NamedEntityGraph;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.security.fundamentals.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
        select u from UserEntity u
        left join fetch Authority a on u.id = a.userEntity.id
        where u.username = :username
        """)
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findByUsername(String username);

}
