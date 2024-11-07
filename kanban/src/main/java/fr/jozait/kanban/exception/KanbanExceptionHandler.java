/* (C)2024 */
package fr.jozait.kanban.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class KanbanExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AuthenticationException.class})
  @ResponseBody
  public ResponseEntity<KanbanException> handleAuthenticationException(
      AuthenticationException notUsed) {
    logger.error(notUsed.toString());
    KanbanException kanbanException = KanbanException.AUTHENTICATION_FAILED;
    return ResponseEntity.status(KanbanException.AUTHENTICATION_FAILED.getStatus())
        .body(kanbanException);
  }

  @ExceptionHandler({KanbanException.class})
  @ResponseBody
  public ResponseEntity<KanbanException> handleKanbanException(KanbanException ex) {
    logger.error(ex.getMessage());
    return ResponseEntity.status(ex.getStatus()).body(ex);
  }

  @ExceptionHandler({Exception.class})
  @ResponseBody
  public ResponseEntity<KanbanException> handleCommonException(Exception ex) {
    logger.error(ex.toString());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(KanbanException.of(ex.getMessage(), HttpStatus.BAD_REQUEST));
  }
}
