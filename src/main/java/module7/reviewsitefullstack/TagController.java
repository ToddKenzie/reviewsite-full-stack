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
@RequestMapping("/tags")
public class TagController {
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private GameExpansionRepository gameExpansionRepo;
	
	@GetMapping("/{id}")
	public String findOneTag(@PathVariable Long id, Model model) throws NoTagFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tag", tag.get());
			model.addAttribute("gameReviews", gameReviewRepo.findByTagsContains(tag.get()));
			model.addAttribute("gameExpansions", gameExpansionRepo.findByTagsContains(tag.get()));
			return "singleTagTemplate";
		}
		throw new NoTagFoundException();
		
	}

	@GetMapping("/all")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAllByOrderByNameAsc());
		return "allTagsTemplate";
	}

}
