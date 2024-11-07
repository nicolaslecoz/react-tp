/* (C)2024 */
package fr.jozait.kanban.controller;

import fr.jozait.kanban.domain.User;
import fr.jozait.kanban.domain.login.JwtResponse;
import fr.jozait.kanban.domain.login.LoginRequest;
import fr.jozait.kanban.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url}")
public class LoginController {

  private final LoginService loginService;

  public LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @Operation(summary = "Se connecter")
  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
    var result = loginService.login(loginRequest);
    return ResponseEntity.ok(result);
  }

  @Operation(summary = "Se créer un compte")
  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody LoginRequest loginRequest) {
    var user = loginService.register(loginRequest);
    return ResponseEntity.ok(user);
  }
}
