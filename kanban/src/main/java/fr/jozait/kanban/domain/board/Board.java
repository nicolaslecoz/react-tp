/* (C)2024 */
package fr.jozait.kanban.domain.board;

import fr.jozait.kanban.domain.BaseEntityWithAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "k_board")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board extends BaseEntityWithAudit {
  private String name;

  private String userId;
}
