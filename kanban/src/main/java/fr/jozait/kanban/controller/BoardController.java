/* (C)2024 */
package fr.jozait.kanban.controller;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.board.Board;
import fr.jozait.kanban.domain.board.BoardDto;
import fr.jozait.kanban.service.board.BoardService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url}/boards")
@SecurityRequirement(name = "KANBAN API")
public class BoardController {

  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @Operation(summary = "Lister les boards qui m'appartiennent")
  @GetMapping("/")
  public ResponseEntity<List<Board>> findAuthenticatedUserBoards(Authentication authentication) {
    List<Board> boards = boardService.findAuthenticatedUserBoards(authentication);
    return ResponseEntity.ok(boards);
  }

  @Operation(
      summary =
          "Rechercher un board grace à son id, et récupérer les détails de ce que le board contient")
  @GetMapping("/{id}")
  public ResponseEntity<BoardDto> findById(@PathVariable String id, Authentication authentication) {
    var board = boardService.findByIdAndUserId(id, authentication, true);

    return ResponseEntity.ok(board);
  }

  @Operation(summary = "Créer un board")
  @PostMapping("/{name}")
  public ResponseEntity<Board> create(@PathVariable String name, Authentication authentication) {
    Board board = boardService.create(name, authentication);
    return ResponseEntity.ok(board);
  }

  @Operation(summary = "Mettre à jour un board")
  @PutMapping("/{id}/{name}")
  public ResponseEntity<Board> update(
      @PathVariable String id, @PathVariable String name, Authentication authentication) {
    Board board = boardService.update(id, name, authentication);
    return ResponseEntity.ok(board);
  }

  @Operation(summary = "Suppression d'un board")
  @DeleteMapping("/{id}")
  public ResponseEntity<StatusObject<Board>> delete(
      @PathVariable String id, Authentication authentication) {
    StatusObject<Board> result = boardService.delete(id, authentication);

    return ResponseEntity.ok(result);
  }
}
