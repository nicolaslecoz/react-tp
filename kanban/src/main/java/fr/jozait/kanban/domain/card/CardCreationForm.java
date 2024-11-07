/* (C)2024 */
package fr.jozait.kanban.domain.card;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardCreationForm {
  private String title;

  private String body;

  private String columnId;

  private String boardId;

  @Nullable private Integer rank;
}
