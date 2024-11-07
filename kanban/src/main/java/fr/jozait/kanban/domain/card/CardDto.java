/* (C)2024 */
package fr.jozait.kanban.domain.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDto {

  private Card card;

  private String boardId;

  public static CardDto of(Card c, String boardId) {
    return CardDto.builder().boardId(boardId).card(c).build();
  }
}
