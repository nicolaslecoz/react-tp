/* (C)2024 */
package fr.jozait.kanban.domain.board;

import fr.jozait.kanban.domain.column.ColumnDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

  private List<ColumnDto> columns;

  private Board board;

  public static BoardDto of(Board b, List<ColumnDto> columnList) {

    return BoardDto.builder().board(b).columns(columnList).build();
  }
}
