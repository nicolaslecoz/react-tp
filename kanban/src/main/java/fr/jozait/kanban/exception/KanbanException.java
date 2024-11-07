/* (C)2024 */
package fr.jozait.kanban.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonIgnoreProperties(value = {"cause", "stackTrace", "suppressed", "localizedMessage"})
public class KanbanException extends RuntimeException {

  public static KanbanException AUTHENTICATION_FAILED =
      KanbanException.of("Authentication failed. Please log in.", HttpStatus.UNAUTHORIZED);

  public static KanbanException USER_ALREADY_EXISTS =
      KanbanException.of("A user with the given name already exists", HttpStatus.CONFLICT);

  public static KanbanException BOARD_DOES_NOT_EXIST =
      KanbanException.of("Board not found.", HttpStatus.NOT_FOUND);

  public static KanbanException COLUMN_DOES_NOT_EXIST =
      KanbanException.of("Column not found.", HttpStatus.NOT_FOUND);

  public static KanbanException CARD_DOES_NOT_EXIST =
      KanbanException.of("Card not found.", HttpStatus.NOT_FOUND);

  private final String message;

  private final HttpStatus status;

  private final Integer code;

  public KanbanException(String message, HttpStatus status) {
    super(message);
    this.message = message;
    this.status = status;
    this.code = status.value();
  }

  public static KanbanException of(String message, HttpStatus status) {
    return new KanbanException(message, status);
  }
}
