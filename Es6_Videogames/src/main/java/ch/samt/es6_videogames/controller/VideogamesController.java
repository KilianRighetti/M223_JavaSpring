package ch.samt.es6_videogames.controller;


import ch.samt.es6_videogames.model.Team;
import ch.samt.es6_videogames.service.PlayerService;
import ch.samt.es6_videogames.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ch.samt.es6_videogames.service.GameProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// RequestMapping mette sempre nel percorso "/videogames"
@RequestMapping("/videogames")
@Controller
public class VideogamesController {

    private final GameProfileService gameProfileService;
    private final PlayerService playerService;
    private final TeamService teamService;
    // Il controller NON parla direttamente col database.
    // --> Usa il SERVICE come "intermediario"

    // Dependency Injection:
    // Spring crea CustomerService e lo passa al controller
    @Autowired
    public VideogamesController(GameProfileService gameProfileService, PlayerService playerService, TeamService teamService) {
        this.gameProfileService = gameProfileService;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping
    public String getGameProfiles(Model model) {
        model.addAttribute("gameProfile1000", gameProfileService.getAllGameProfiles()); // MODEL passa dati alla view
        // Ritorna il nome della view da mostrare
        return "gameProfileList";
    }

    @GetMapping("/player")
    public String getPlayers(Model model) {
        model.addAttribute("player1000", playerService.getAllPlayers());
        return "playerList";
    }

    @GetMapping("/team")
    public String getTeams(Model model) {
        List<Team> allTeams = teamService.getAllTeams();
        model.addAttribute("team1000", allTeams);
        return "teamList";
    }



}