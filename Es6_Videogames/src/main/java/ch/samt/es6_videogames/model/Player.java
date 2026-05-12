package ch.samt.es6_videogames.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @SequenceGenerator(name = "player_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50, message = "Lunghezza consentita tra 2 e 50 caratteri")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Lunghezza consentita tra 2 e 50 caratteri")
    private String lastName;

    @Min(12)
    @Max(122)
    private Integer age;

    @NotBlank
    @Email // IMPORTANTE
    private String email;

    private boolean deleted; // Server x Soft Delete

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private GameProfile gameProfile;
}