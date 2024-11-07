/* (C)2024 */
package fr.jozait.kanban.repository;

import fr.jozait.kanban.domain.column.Column;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Column, String> {

  @Query(
      "select c from Column c join Board b on b.id = c.boardId where b.userId = :userId and c.boardId = :boardId order by c.rank asc")
  List<Column> findAllByBoardIdAndUserId(String boardId, String userId);

  Optional<Column> findByIdAndBoardId(String columnId, String boardId);
}
