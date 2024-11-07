/* (C)2024 */
package fr.jozait.kanban.controller;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.column.Column;
import fr.jozait.kanban.service.column.ColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url}/columns")
@SecurityRequirement(name = "KANBAN API")
public class ColumnController {

  private final ColumnService columnService;

  public ColumnController(ColumnService columnService) {
    this.columnService = columnService;
  }

  @Operation(summary = "Récupérer toutes les colonnes d'un board")
  @GetMapping("/board/{boardId}")
  public ResponseEntity<List<Column>> findColumnsByBoardId(
      @PathVariable String boardId, Authentication authentication) {
    List<Column> columns = columnService.findColumnsByBoardId(boardId, authentication);
    return ResponseEntity.ok(columns);
  }

  @Operation(summary = "Créer une colonne dans un board")
  @PostMapping("/board/{boardId}/name/{name}")
  public ResponseEntity<Column> create(
      @PathVariable String boardId, @PathVariable String name, Authentication authentication) {
    Column column = columnService.create(boardId, name, authentication);
    return ResponseEntity.ok(column);
  }

  @Operation(summary = "Mise à jour d'une colonne")
  @PutMapping("/")
  public ResponseEntity<Column> update(@RequestBody Column body, Authentication authentication) {
    Column column = columnService.update(body, authentication);
    return ResponseEntity.ok(column);
  }

  @Operation(summary = "Suppression d'une colonne")
  @DeleteMapping("/{columnId}")
  public ResponseEntity<StatusObject<Column>> delete(
      @PathVariable String columnId, Authentication authentication) {
    StatusObject<Column> result = columnService.delete(columnId, authentication);

    return ResponseEntity.ok(result);
  }
}
