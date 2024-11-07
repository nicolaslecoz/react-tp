/* (C)2024 */
package fr.jozait.kanban.service;

import fr.jozait.kanban.domain.CustomUserDetails;
import fr.jozait.kanban.domain.User;
import fr.jozait.kanban.exception.KanbanException;
import fr.jozait.kanban.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByName(username)
            .orElseThrow(() -> KanbanException.AUTHENTICATION_FAILED);

    return CustomUserDetails.of(user.getId(), user.getName(), user.getPassword());
  }
}
