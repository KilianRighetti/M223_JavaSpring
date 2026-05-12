package ch.samt.es6_videogames.data;

import ch.samt.es6_videogames.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
