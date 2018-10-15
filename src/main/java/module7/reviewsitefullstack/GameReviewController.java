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

	@RequestMapping("/game-review")
	public String findOneGameReview(@RequestParam(value="id")long id, Model model) throws NoGameReviewFoundException {
		Optional<GameReview> gameReview = gameReviewRepo.findById(id);
		
		if(gameReview.isPresent()) {
			model.addAttribute("gameReviews", gameReview.get());
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
			model.addAttribute("gameCategories", gameCategory.get());
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
			model.addAttribute("tags", tag.get());
			model.addAttribute("gameReviews", gameReviewRepo.findByTagsContains(tag.get()));
		}
		
	}

	@RequestMapping("/allTags")
	public void findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
	}

}
