/* (C)2024 */
package fr.jozait.kanban.repository;

import fr.jozait.kanban.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByName(String name);

  boolean existsByName(String name);
}
