package module7.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/gameReview")
public class GameController {
	
	@Resource
	private GameRepository gameRepo;
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Resource
	private GameExpansionRepository gameExpansionRepo;
	
	@Resource
	private ReviewRepository reviewRepo;

	@GetMapping("/{id:[\\d]+}")
	public String findOneGameReview(@PathVariable Long id, Model model) throws NoGameFoundException {
		Optional<Game> game = gameRepo.findById(id);
		
		if(game.isPresent()) {
			model.addAttribute("game", game.get());
			model.addAttribute("gameCategory", gameCategoryRepo.findByGamesContains(game.get()));
			model.addAttribute("tags", tagRepo.findByGamesContains(game.get()));
			model.addAttribute("gameExpansion", gameExpansionRepo.findByGame(game.get()));
			return "singleGameReviewTemplate";
		}
		throw new NoGameFoundException();
	}

	@GetMapping("/all")
	public String findAllGameReviews(Model model) {
		model.addAttribute("games", gameRepo.findAllByOrderByNameAsc());
		model.addAttribute("gameCategories", gameCategoryRepo.findAll());
		return "gameReviewsTemplate";
	}
	
}
