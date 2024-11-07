/* (C)2024 */
package fr.jozait.kanban.domain.column;

import fr.jozait.kanban.domain.card.CardDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnDto {
  private Column column;

  private List<CardDto> cards;

  public static ColumnDto of(Column c, List<CardDto> cardList) {
    return ColumnDto.builder().cards(cardList).column(c).build();
  }
}
