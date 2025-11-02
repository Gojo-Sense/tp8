package ma.rest.spring.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entité représentant un compte bancaire")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du compte", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Solde du compte bancaire", example = "5000.50", required = true)
    private double solde;

    @Temporal(TemporalType.DATE)
    @Schema(description = "Date de création du compte", example = "2025-10-27", required = true)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Type de compte bancaire", example = "COURANT", allowableValues = {"COURANT", "EPARGNE"}, required = true)
    private TypeCompte type;

}
