package ch.samt.es6_videogames.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
    @SequenceGenerator(name = "profile_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50, message = "Lunghezza consentita tra 2 e 50 caratteri")
    private String nickname;

    @PositiveOrZero
    private Integer level;

    @NotBlank
    @Size(min = 2, max = 80, message = "Lunghezza consentita tra 2 e 80 caratteri")
    private String favoriteGame;

    private boolean deleted; // Serve x Soft Delete

    @OneToOne(mappedBy = "gameProfile")
    private Player player;
}