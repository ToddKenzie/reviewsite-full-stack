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
@RequestMapping("/gameCategory")
public class GameCategoryController {
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private GameRepository gameRepo;
	

	@GetMapping("/{id:[\\d]+}")
	public String findOneGameCategory(@PathVariable Long id, Model model) throws NoGameCategoryException {
		Optional<GameCategory> gameCategory = gameCategoryRepo.findById(id);
		
		if(gameCategory.isPresent()) {
			model.addAttribute("gameCategory", gameCategory.get());
			model.addAttribute("games", gameRepo.findByGameCategory(gameCategory.get()));
			return "singleGameCategoryTemplate";
		}
		throw new NoGameCategoryException();
	}

	@GetMapping("/all")
	public String findAllGameCategories(Model model) {
		model.addAttribute("gameCategories", gameCategoryRepo.findAll());
		return "gameCategoriesTemplate";
	}

}
