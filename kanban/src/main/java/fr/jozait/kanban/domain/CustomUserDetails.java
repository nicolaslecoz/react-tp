/* (C)2024 */
package fr.jozait.kanban.domain;

import java.util.Collection;
import java.util.Collections;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Data
public class CustomUserDetails implements UserDetails {

  private String userId;

  private String username;

  private String password;

  public static CustomUserDetails of(String userId, String username, String password) {
    return CustomUserDetails.builder().userId(userId).username(username).password(password).build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
