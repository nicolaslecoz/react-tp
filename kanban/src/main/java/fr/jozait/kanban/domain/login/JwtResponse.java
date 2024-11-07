/* (C)2024 */
package fr.jozait.kanban.domain.login;

import fr.jozait.kanban.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

  private String jwt;

  private User user;
}
