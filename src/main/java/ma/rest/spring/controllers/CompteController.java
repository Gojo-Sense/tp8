package ma.rest.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.rest.spring.entities.Compte;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque")
@Tag(name = "Gestion des Comptes", description = "API pour gérer les comptes bancaires (CRUD)")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    // READ: Récupérer tous les comptes
    @Operation(summary = "Récupérer tous les comptes", description = "Retourne la liste de tous les comptes bancaires en JSON ou XML")
    @ApiResponse(responseCode = "200", description = "Liste des comptes récupérée avec succès")
    @GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // READ: Récupérer un compte par son identifiant
    @Operation(summary = "Récupérer un compte par ID", description = "Retourne un compte bancaire spécifique par son identifiant en JSON ou XML")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte trouvé",
                    content = @Content(schema = @Schema(implementation = Compte.class))),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompteById(
            @Parameter(description = "ID du compte à récupérer") @PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> ResponseEntity.ok().body(compte))
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE: Ajouter un nouveau compte
    @Operation(summary = "Créer un nouveau compte", description = "Ajoute un nouveau compte bancaire dans la base de données (accepte JSON et XML)")
    @ApiResponse(responseCode = "200", description = "Compte créé avec succès")
    @PostMapping(value = "/comptes", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public Compte createCompte(
            @Parameter(description = "Informations du compte à créer") @RequestBody Compte compte) {
        return compteRepository.save(compte);
    }

    // UPDATE: Mettre à jour un compte existant
    @Operation(summary = "Mettre à jour un compte", description = "Modifie les informations d'un compte existant (accepte JSON et XML)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @PutMapping(value = "/comptes/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> updateCompte(
            @Parameter(description = "ID du compte à mettre à jour") @PathVariable Long id,
            @Parameter(description = "Nouvelles informations du compte") @RequestBody Compte compteDetails) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compte.setSolde(compteDetails.getSolde());
                    compte.setDateCreation(compteDetails.getDateCreation());
                    compte.setType(compteDetails.getType());
                    Compte updatedCompte = compteRepository.save(compte);
                    return ResponseEntity.ok().body(updatedCompte);
                }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Supprimer un compte
    @Operation(summary = "Supprimer un compte", description = "Supprime un compte bancaire de la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compte supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Compte non trouvé", content = @Content)
    })
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(
            @Parameter(description = "ID du compte à supprimer") @PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compteRepository.delete(compte);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}


