package module7.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Resource
	private CommentRepository commentRepo;

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

	@GetMapping("/review/{id}")
	public String findReviewAndCommentsForGame(@PathVariable Long id, Model model) throws NoReviewFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
			model.addAttribute("review", review.get());
			model.addAttribute("comments", commentRepo.findByReviewOrderByTimeStampAsc(review.get()));
			return "commentsTemplate";
		} 
		throw new NoReviewFoundException();
	}

	@PostMapping("/review/add")
	public String addComment(Long reviewId, String text, String username) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		commentRepo.save(new Comment(review.get(), text, username));
		return "redirect:/gameReview/review/" + reviewId;
		
	}
	
}
