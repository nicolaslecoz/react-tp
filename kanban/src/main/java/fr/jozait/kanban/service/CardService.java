/* (C)2024 */
package fr.jozait.kanban.service;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.card.Card;
import fr.jozait.kanban.domain.card.CardCreationForm;
import fr.jozait.kanban.exception.KanbanException;
import fr.jozait.kanban.repository.CardRepository;
import fr.jozait.kanban.service.column.ColumnService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CardService {
  private static final String DELETE_CARD_MESSAGE = "The card '%s' has been deleted.";

  private final CardRepository cardRepository;

  private final ColumnService columnService;

  private final UserService userService;

  public CardService(
      CardRepository cardRepository, ColumnService columnService, UserService userService) {
    this.cardRepository = cardRepository;
    this.columnService = columnService;
    this.userService = userService;
  }

  private Card findById(String id, String columnId, String boardId, Authentication authentication) {
    var userId = userService.getUserId(authentication);
    var cardOpt =
        cardRepository.findByIdAndColumnIdAndBoardIdAndUserId(id, columnId, boardId, userId);
    cardOpt.orElseThrow(() -> KanbanException.CARD_DOES_NOT_EXIST);
    return cardOpt.get();
  }

  public Card create(CardCreationForm body, Authentication authentication) {
    columnService.findByIdAndBoardId(body.getColumnId(), body.getBoardId(), authentication);
    List<Card> cardList = cardRepository.findAllByColumnIdOrderByRankAsc(body.getColumnId());
    var rank = cardList.isEmpty() ? 0 : cardList.getLast().getRank() + 1;
    if (body.getRank() != null) {
      rank = body.getRank();
    }
    Card card = Card.of(body, rank);
    return cardRepository.save(card);
  }

  public StatusObject<Card> deleteById(
      String id, String columnId, String boardId, Authentication authentication) {
    var card = findById(id, columnId, boardId, authentication);
    cardRepository.deleteById(id);
    return StatusObject.of(DELETE_CARD_MESSAGE.formatted(card.getTitle()), HttpStatus.OK, card);
  }

  public Card update(String id, CardCreationForm form, Authentication authentication) {
    var card = findById(id, form.getColumnId(), form.getBoardId(), authentication);
    card.setBody(form.getBody());
    card.setTitle(form.getTitle());
    card.setRank(form.getRank());
    return cardRepository.save(card);
  }

  public Card moveTo(
      String id, CardCreationForm form, String newColumnId, Authentication authentication) {
    var card = findById(id, form.getColumnId(), form.getBoardId(), authentication);
    columnService.findByIdAndBoardId(newColumnId, form.getBoardId(), authentication);
    card.setColumnId(newColumnId);
    return cardRepository.save(card);
  }
}
