/* (C)2024 */
package fr.jozait.kanban.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "k_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
  @UuidGenerator @Id private String id;

  private String name;

  @JsonIgnore private String password;
}
