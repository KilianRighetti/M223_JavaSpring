package ch.samt.es6_videogames.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videogame_seq")
    @SequenceGenerator(name = "videogame_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "Lunghezza consentita tra 2 e 30 caratteri")
    private String name;

    @NotBlank
    @Size(min = 1, max = 80, message = "Lunghezza consentita tra 1 e 80 caratteri")
    private String country;

    @NotNull
    @PastOrPresent
    private LocalDate foundedAt;

    private boolean deleted; // Serve x Soft Delete


    @OneToMany(mappedBy = "team")
    private List<Player> players;
}