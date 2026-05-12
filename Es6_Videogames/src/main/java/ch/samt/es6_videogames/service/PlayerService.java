package ch.samt.es6_videogames.service;

import ch.samt.es6_videogames.data.PlayerRepository;
import ch.samt.es6_videogames.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    // Il service usa il repository per accedere ai dati nel database
    private PlayerRepository playerRepository;

    // Dependency Injection:
    // Spring crea il repository e lo passa al service
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        /*
         * Qui il service delega al repository la lettura di tutti i record.
         * customerRepository.findAll() è un metodo tipico di Spring Data JPA
         * che prende tutti gli oggetti Customer dal database.
         */
        return playerRepository.findAll();
    }


}