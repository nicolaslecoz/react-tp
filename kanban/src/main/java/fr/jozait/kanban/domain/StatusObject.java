/* (C)2024 */
package fr.jozait.kanban.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class StatusObject<T> {

  private String message;

  private HttpStatus status;

  private T resource;

  public static <U> StatusObject<U> of(String pMessage, HttpStatus pStatus, U pResource) {
    return StatusObject.<U>builder().status(pStatus).message(pMessage).resource(pResource).build();
  }
}
