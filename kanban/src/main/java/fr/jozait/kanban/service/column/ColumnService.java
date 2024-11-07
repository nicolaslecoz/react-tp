/* (C)2024 */
package fr.jozait.kanban.service.column;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.column.Column;
import fr.jozait.kanban.exception.KanbanException;
import fr.jozait.kanban.repository.ColumnRepository;
import fr.jozait.kanban.service.UserService;
import fr.jozait.kanban.service.board.BoardService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

  private static final String DELETE_COLUMN_MESSAGE = "The column '%s' has been deleted.";

  private final ColumnRepository columnRepository;

  private final BoardService boardService;
  private final UserService userService;

  public ColumnService(
      ColumnRepository columnRepository, BoardService boardService, UserService userService) {
    this.columnRepository = columnRepository;
    this.boardService = boardService;
    this.userService = userService;
  }

  public void findByIdAndBoardId(String id, String boardId, Authentication authentication) {
    boardService.findByIdAndUserId(boardId, authentication, false);
    var columnOpt = columnRepository.findByIdAndBoardId(id, boardId);
    columnOpt.orElseThrow(() -> KanbanException.COLUMN_DOES_NOT_EXIST);
  }

  public List<Column> findColumnsByBoardId(String boardId, Authentication authentication) {
    var userId = userService.getUserId(authentication);
    return columnRepository.findAllByBoardIdAndUserId(boardId, userId);
  }

  public Column create(String boardId, String name, Authentication authentication) {
    var board = boardService.findByIdAndUserId(boardId, authentication, false);
    var existingColumns = findColumnsByBoardId(board.getBoard().getId(), authentication);
    var newRank = existingColumns.isEmpty() ? 0 : existingColumns.getLast().getRank() + 1;
    var column = Column.of(name, board.getBoard().getId(), newRank);
    return columnRepository.save(column);
  }

  public Column update(Column body, Authentication authentication) {
    boardService.findByIdAndUserId(body.getBoardId(), authentication, false);
    return columnRepository.save(body);
  }

  public StatusObject<Column> delete(String columnId, Authentication authentication) {
    var column =
        columnRepository
            .findById(columnId)
            .orElseThrow(() -> KanbanException.COLUMN_DOES_NOT_EXIST);
    boardService.findByIdAndUserId(column.getBoardId(), authentication, false);
    columnRepository.deleteById(columnId);
    return StatusObject.of(
        DELETE_COLUMN_MESSAGE.formatted(column.getName()), HttpStatus.OK, column);
  }
}
