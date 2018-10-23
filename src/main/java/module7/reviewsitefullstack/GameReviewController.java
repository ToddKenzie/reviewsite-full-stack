package module7.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@Controller
public class GameReviewController {
	
	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Resource
	private GameExpansionRepository gameExpansionRepo;

	@RequestMapping("/game-review")
	public String findOneGameReview(@RequestParam(value="id")Long id, Model model) throws NoGameReviewFoundException {
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

	@RequestMapping("/allGameReviews")
	public String findAllGameReviews(Model model) {
		model.addAttribute("gameReviews", gameReviewRepo.findAllByOrderByNameAsc());
		model.addAttribute("gameCategories", gameCategoryRepo.findAll());
		return "gameReviewsTemplate";
	}
	
//	@RequestMapping("/expansion")
//	public String findOneExpansion(@RequestParam(value="id")long id, Model model) throws NoExpansionFoundException {
//		Optional<GameExpansion> gameXp = gameExpansionRepo.findById(id);
//		
//		if(gameXp.isPresent()) {
//			model.addAttribute("gameExpansion", gameXp.get());
//			model.addAttribute("gameReview", gameReviewRepo.findByGameExpansion(gameXp.get()));
//			model.addAttribute("tags", tagRepo.findByGameExpansionsContains(gameXp.get()));
//			return "singleExpansionTemplate";
//		}
//		throw new NoExpansionFoundException();
//	}
//
//	@RequestMapping("/allExpansions")
//	public String findAllExpansions(Model model) {
//		model.addAttribute("gameExpansions", gameExpansionRepo.findAll());
//		return "allExpansionsTemplate";
//	}

}
