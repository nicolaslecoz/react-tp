/* (C)2024 */
package fr.jozait.kanban.controller;

import fr.jozait.kanban.domain.StatusObject;
import fr.jozait.kanban.domain.card.Card;
import fr.jozait.kanban.domain.card.CardCreationForm;
import fr.jozait.kanban.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.url}/cards")
@SecurityRequirement(name = "KANBAN API")
public class CardController {

  private final CardService cardService;

  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  @Operation(summary = "Créer une carte")
  @PostMapping("/")
  public ResponseEntity<Card> create(
      @RequestBody CardCreationForm form, Authentication authentication) {
    var card = cardService.create(form, authentication);

    return ResponseEntity.ok(card);
  }

  @Operation(summary = "Suppresion d'une carte")
  @PostMapping("/{id}/delete")
  public ResponseEntity<StatusObject<Card>> delete(
      @PathVariable String id, @RequestBody CardCreationForm form, Authentication authentication) {
    var response =
        cardService.deleteById(id, form.getColumnId(), form.getBoardId(), authentication);
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Mise à jour d'une carte")
  @PutMapping("/{id}")
  public ResponseEntity<Card> update(
      @PathVariable String id, @RequestBody CardCreationForm form, Authentication authentication) {
    var card = cardService.update(id, form, authentication);
    return ResponseEntity.ok(card);
  }

  @Operation(summary = "Déplacer une carte vers une nouvelle colonne")
  @PutMapping("/{id}/move-to/{newColumnId}")
  public ResponseEntity<Card> moveTo(
      @PathVariable String id,
      @RequestBody CardCreationForm form,
      @PathVariable String newColumnId,
      Authentication authentication) {
    var card = cardService.moveTo(id, form, newColumnId, authentication);
    return ResponseEntity.ok(card);
  }
}
