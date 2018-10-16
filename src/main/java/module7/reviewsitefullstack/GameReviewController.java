package module7.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String findOneGameReview(@RequestParam(value="id")long id, Model model) throws NoGameReviewFoundException {
		Optional<GameReview> gameReview = gameReviewRepo.findById(id);
		
		if(gameReview.isPresent()) {
			model.addAttribute("gameReview", gameReview.get());
			model.addAttribute("gameCategory", gameCategoryRepo.findByGameReviewContains(gameReview.get()));
			model.addAttribute("tags", tagRepo.findByGameReviewContains(gameReview.get()));
			model.addAttribute("gameExpansion", gameExpansionRepo.findByGameReviewContains(gameReview.get()));
			return "singleGameReviewTemplate";
		}
		throw new NoGameReviewFoundException();
	}

	@RequestMapping("/allGameReviews")
	public String findAllGameReviews(Model model) {
		model.addAttribute("gameReviews", gameReviewRepo.findAll());
		return "gameReviewsTemplate";
	}

	@RequestMapping("/game-category")
	public String findOneGameCategory(@RequestParam(value="id")long id, Model model) throws NoGameCategoryException {
		Optional<GameCategory> gameCategory = gameCategoryRepo.findById(id);
		
		if(gameCategory.isPresent()) {
			model.addAttribute("gameCategory", gameCategory.get());
			model.addAttribute("gameReviews", gameReviewRepo.findByGameCategoryContains(gameCategory.get()));
			return "gameCategoryTemplate";
		}
		throw new NoGameCategoryException();
	}

	@RequestMapping("/allGameCategories")
	public String findAllGameCategories(Model model) {
		model.addAttribute("gameCategories", gameCategoryRepo.findAll());
		return "gameCategoriesTemplate";
	}

	@RequestMapping("/tag")
	public void findOneTag(@RequestParam(value="id")long id, Model model) {
		Optional<Tag> tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tag", tag.get());
			model.addAttribute("gameReviews", gameReviewRepo.findByTagsContains(tag.get()));
		}
		
	}

	@RequestMapping("/allTags")
	public void findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
	}

	public void findOneExpansion(long id, Model model) {
		Optional<GameExpansion> gameXp = gameExpansionRepo.findById(id);
		
		if(gameXp.isPresent()) {
			model.addAttribute("gameExpansion", gameXp.get());
			model.addAttribute("tags", tagRepo.findByGameExpansionContains(gameXp.get()));
		}
		
	}

	public void findAllExpansions(Model model) {
		model.addAttribute("gameExpansions", gameExpansionRepo.findAll());
	}

}
