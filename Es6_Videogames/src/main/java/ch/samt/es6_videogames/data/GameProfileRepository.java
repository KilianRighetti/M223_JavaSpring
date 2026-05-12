package ch.samt.es6_videogames.data;

import ch.samt.es6_videogames.model.GameProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameProfileRepository extends JpaRepository<GameProfile, Long> {
}
