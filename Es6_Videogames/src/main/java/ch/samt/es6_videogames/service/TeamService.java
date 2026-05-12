package ch.samt.es6_videogames.service;

import ch.samt.es6_videogames.data.TeamRepository;
import ch.samt.es6_videogames.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    // Il service usa il repository per accedere ai dati nel database
    private TeamRepository teamRepository;

    // Dependency Injection:
    // Spring crea il repository e lo passa al service
    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        /*
         * Qui il service delega al repository la lettura di tutti i record.
         * customerRepository.findAll() è un metodo tipico di Spring Data JPA
         * che prende tutti gli oggetti Customer dal database.
         */
        return teamRepository.findAll();
    }

}