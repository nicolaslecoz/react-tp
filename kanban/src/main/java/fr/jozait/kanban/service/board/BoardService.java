/* (C)2024 */
package fr.jozait.kanban.service.board;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.board.Board;
import fr.jozait.kanban.domain.board.BoardDto;
import fr.jozait.kanban.domain.card.CardDto;
import fr.jozait.kanban.domain.column.ColumnDto;
import fr.jozait.kanban.exception.KanbanException;
import fr.jozait.kanban.repository.BoardRepository;
import fr.jozait.kanban.repository.CardRepository;
import fr.jozait.kanban.repository.ColumnRepository;
import fr.jozait.kanban.service.UserService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private static final String DELETE_BOARD_MESSAGE = "The board '%s' has been deleted.";
  private final BoardRepository boardRepository;

  private final ColumnRepository columnRepository;

  private final CardRepository cardRepository;
  private final UserService userService;

  public BoardService(
      BoardRepository boardRepository,
      ColumnRepository columnRepository,
      CardRepository cardRepository,
      UserService userService) {
    this.boardRepository = boardRepository;
    this.columnRepository = columnRepository;
    this.cardRepository = cardRepository;
    this.userService = userService;
  }

  public List<Board> findAuthenticatedUserBoards(Authentication authentication) {
    var userId = userService.getUserId(authentication);

    return boardRepository.findAllByUserIdOrderByName(userId);
  }

  public Board create(String name, Authentication authentication) {
    var userId = userService.getUserId(authentication);

    Board board = new Board(name, userId);
    return boardRepository.save(board);
  }

  public BoardDto findByIdAndUserId(String id, Authentication authentication, Boolean withDetails) {
    var userId = userService.getUserId(authentication);

    var boardOpt = boardRepository.findByIdAndUserId(id, userId);
    boardOpt.orElseThrow(() -> KanbanException.BOARD_DOES_NOT_EXIST);
    var board = boardOpt.get();
    if (!withDetails) {
      return BoardDto.of(board, Collections.emptyList());
    }
    var columnList = columnRepository.findAllByBoardIdAndUserId(board.getId(), userId);
    return BoardDto.builder()
        .board(board)
        .columns(
            columnList.stream()
                .map(
                    c ->
                        ColumnDto.of(
                            c,
                            cardRepository.findAllByColumnIdOrderByRankAsc(c.getId()).stream()
                                .map(card -> CardDto.of(card, board.getId()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList()))
        .build();
  }

  public Board update(String id, String name, Authentication authentication) {

    var boardDto = findByIdAndUserId(id, authentication, false);
    var board = boardDto.getBoard();

    board.setName(name);
    return boardRepository.save(board);
  }

  public StatusObject<Board> delete(String id, Authentication authentication) {
    var board = findByIdAndUserId(id, authentication, false).getBoard();

    boardRepository.deleteById(board.getId());
    return StatusObject.of(DELETE_BOARD_MESSAGE.formatted(board.getName()), HttpStatus.OK, board);
  }
}
