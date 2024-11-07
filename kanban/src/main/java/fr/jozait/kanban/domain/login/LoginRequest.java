/* (C)2024 */
package fr.jozait.kanban.domain.login;

import lombok.Data;

@Data
public class LoginRequest {

  private String username;

  private String password;
}
