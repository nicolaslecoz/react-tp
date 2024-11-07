/* (C)2024 */
package fr.jozait.kanban.domain.card;

import fr.jozait.kanban.domain.BaseEntityWithAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "k_card")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Card extends BaseEntityWithAudit {

  private String title;

  private String body;

  private String columnId;

  private Integer rank;

  public static Card of(CardCreationForm body, Integer rank) {
    return new Card(body.getTitle(), body.getBody(), body.getColumnId(), rank);
  }
}
