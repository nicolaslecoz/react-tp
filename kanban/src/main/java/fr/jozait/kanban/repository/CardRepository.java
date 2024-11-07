/* (C)2024 */
package fr.jozait.kanban.repository;

import fr.jozait.kanban.domain.card.Card;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

  List<Card> findAllByColumnIdOrderByRankAsc(String columnId);

  @Query(
      "select c from Card c join Column colu on colu.id = c.columnId join Board b on colu.boardId = b.id where c.id = :id and colu.id = :columnId and b.id = :boardId and b.userId = :userId")
  Optional<Card> findByIdAndColumnIdAndBoardIdAndUserId(
      String id, String columnId, String boardId, String userId);
}
