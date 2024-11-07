/* (C)2024 */
package fr.jozait.kanban.repository;

import fr.jozait.kanban.domain.board.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {

  List<Board> findAllByUserIdOrderByName(String userId);

  Optional<Board> findByIdAndUserId(String id, String userId);
}
