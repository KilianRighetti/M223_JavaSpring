package ch.samt.es6_videogames.data;

import ch.samt.es6_videogames.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> { // LONG è il tipo di dati di "ID"
}
