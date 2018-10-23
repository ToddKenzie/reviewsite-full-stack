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
public class GameReviewController {
	
	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Resource
	private GameExpansionRepository gameExpansionRepo;

	@GetMapping("/{id:[\\d]+}")
	public String findOneGameReview(@PathVariable Long id, Model model) throws NoGameReviewFoundException {
		Optional<GameReview> gameReview = gameReviewRepo.findById(id);
		
		if(gameReview.isPresent()) {
			model.addAttribute("gameReview", gameReview.get());
			model.addAttribute("gameCategory", gameCategoryRepo.findByGameReviewsContains(gameReview.get()));
			model.addAttribute("tags", tagRepo.findByGameReviewsContains(gameReview.get()));
			model.addAttribute("gameExpansion", gameExpansionRepo.findByGameReview(gameReview.get()));
			return "singleGameReviewTemplate";
		}
		throw new NoGameReviewFoundException();
	}

	@GetMapping("/all")
	public String findAllGameReviews(Model model) {
		model.addAttribute("gameReviews", gameReviewRepo.findAllByOrderByNameAsc());
		model.addAttribute("gameCategories", gameCategoryRepo.findAll());
		return "gameReviewsTemplate";
	}
	
}
