/* (C)2024 */
package fr.jozait.kanban.controller;

import fr.jozait.kanban.domain.User;
import fr.jozait.kanban.repository.UserRepository;
import fr.jozait.kanban.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url}/users")
@SecurityRequirement(name = "KANBAN API")
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  public UserController(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> findAll() {
    var users = userRepository.findAll();

    return ResponseEntity.ok(users);
  }
}
