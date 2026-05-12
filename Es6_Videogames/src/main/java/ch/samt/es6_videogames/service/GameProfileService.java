package ch.samt.es6_videogames.service;

import ch.samt.es6_videogames.data.GameProfileRepository;
import ch.samt.es6_videogames.model.GameProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameProfileService {

    // Il service usa il repository per accedere ai dati nel database
    private GameProfileRepository gameProfileRepository;

    // Dependency Injection:
    // Spring crea il repository e lo passa al service
    @Autowired
    public GameProfileService(GameProfileRepository gameProfileRepository) {
        this.gameProfileRepository = gameProfileRepository;
    }

    public List<GameProfile> getAllGameProfiles() {
        /*
         * Qui il service delega al repository la lettura di tutti i record.
         * customerRepository.findAll() è un metodo tipico di Spring Data JPA
         * che prende tutti gli oggetti Customer dal database.
         */
        return gameProfileRepository.findAll();
    }

}