package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private ScoreRepository scoreRepo;

    @GetMapping("/games/{name}/scores")
    public List<Score> getGa(@PathVariable String name) {
        Game g = gameRepo.findByName(name);
        return scoreRepo.findByGame(g);
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getGam(@PathVariable String name, @PathVariable Long id) {
        Game g = gameRepo.findByName(name);

        return scoreRepo.findByGameAndId(g, id);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Game deleteGam(@PathVariable String name, @PathVariable Long id) {
        Game g = gameRepo.findByName(name);
        Score s = scoreRepo.findByGameAndId(g, id);
        scoreRepo.deleteById(s.getId());
        return g;
    }

    @PostMapping("/games/{name}/scores")
    public Score postGam(@PathVariable String name, @RequestBody Score score) {
        Game g = gameRepo.findByName(name);
        score.setGame(g);
        return scoreRepo.save(score);
    }
}
