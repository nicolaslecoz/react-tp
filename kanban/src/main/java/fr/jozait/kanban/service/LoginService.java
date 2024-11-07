/* (C)2024 */
package fr.jozait.kanban.service;

import fr.jozait.kanban.domain.User;
import fr.jozait.kanban.domain.login.JwtResponse;
import fr.jozait.kanban.domain.login.LoginRequest;
import fr.jozait.kanban.exception.KanbanException;
import fr.jozait.kanban.repository.UserRepository;
import fr.jozait.kanban.validator.PasswordConstraintsValidator;
import org.passay.PasswordValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  private final JwtTokenProvider jwtTokenProvider;

  public LoginService(
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      JwtTokenProvider jwtTokenProvider) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  public JwtResponse login(LoginRequest loginRequest) {
    Authentication authenticationRequest =
        UsernamePasswordAuthenticationToken.unauthenticated(
            loginRequest.getUsername(), loginRequest.getPassword());
    Authentication authenticationResponse =
        authenticationManager.authenticate(authenticationRequest);
    String token = jwtTokenProvider.generateToken(authenticationResponse);
    User user =
        userRepository
            .findByName(authenticationResponse.getName())
            .orElseThrow(() -> KanbanException.AUTHENTICATION_FAILED);
    return JwtResponse.builder().user(user).jwt(token).build();
  }

  public User register(LoginRequest loginRequest) {
    if (userRepository.existsByName(loginRequest.getUsername())) {
      throw KanbanException.USER_ALREADY_EXISTS;
    }
    var passwordValidator = new PasswordValidator(PasswordConstraintsValidator.RULES);
    var validationResult =
        PasswordConstraintsValidator.validate(passwordValidator, loginRequest.getPassword());
    if (!validationResult.isValid()) {
      throw KanbanException.of(
          PasswordConstraintsValidator.getMessage(passwordValidator, validationResult),
          HttpStatus.BAD_REQUEST);
    }
    User user =
        User.builder()
            .name(loginRequest.getUsername())
            .password(passwordEncoder.encode(loginRequest.getPassword()))
            .build();
    user = userRepository.save(user);
    return user;
  }
}
