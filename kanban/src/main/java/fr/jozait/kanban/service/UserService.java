/* (C)2024 */
package fr.jozait.kanban.service;

import fr.jozait.kanban.domain.CustomUserDetails;
import fr.jozait.kanban.domain.User;
import fr.jozait.kanban.repository.UserRepository;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAllUsers() {
    return this.userRepository.findAll();
  }

  public String getUserId(Authentication authentication) {
    return ((CustomUserDetails) authentication.getPrincipal()).getUserId();
  }
}
