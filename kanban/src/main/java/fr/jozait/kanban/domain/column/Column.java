/* (C)2024 */
package fr.jozait.kanban.domain.column;

import fr.jozait.kanban.domain.BaseEntityWithAudit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "k_column")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Column extends BaseEntityWithAudit {
  private String name;

  private String boardId;

  private Integer rank;

  public static Column of(String pName, String pBoardId, Integer pRank) {
    return new Column(pName, pBoardId, pRank);
  }
}
