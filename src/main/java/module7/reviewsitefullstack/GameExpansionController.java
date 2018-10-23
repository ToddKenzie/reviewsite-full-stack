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
@RequestMapping("/gameExpansion")
public class GameExpansionController {
	
	@Resource
	private GameExpansionRepository gameExpansionRepo;

	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private TagRepository tagRepo;

	@GetMapping("/{id:[\\d]+}")
	public String findOneExpansion(@PathVariable long id, Model model) throws NoExpansionFoundException {
		Optional<GameExpansion> gameXp = gameExpansionRepo.findById(id);
		
		if(gameXp.isPresent()) {
			model.addAttribute("gameExpansion", gameXp.get());
			model.addAttribute("gameReview", gameReviewRepo.findByGameExpansion(gameXp.get()));
			model.addAttribute("tags", tagRepo.findByGameExpansionsContains(gameXp.get()));
			return "singleExpansionTemplate";
		}
		throw new NoExpansionFoundException();
	}

	@GetMapping("/all")
	public String findAllExpansions(Model model) {
		model.addAttribute("gameExpansions", gameExpansionRepo.findAll());
		return "allExpansionsTemplate";
	}


}
