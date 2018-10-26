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
@RequestMapping("/tags")
public class TagController {
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private GameExpansionRepository gameExpansionRepo;
	
	@GetMapping("/{id:[\\d]+}")
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
	
	@GetMapping("/edit")
	public String findAllTagsForAddPage(Model model) {
		model.addAttribute("tags", tagRepo.findAllByOrderByNameAsc());
		return "editTagsTemplate";
	}

	@PostMapping("/added")
	public String addTag(String tagName) {
		Tag newTag = tagRepo.findByNameIgnoreCase(tagName);
		
		if(newTag==null) {
			String formattedTagName = StringFormatter.convertToPascalCaseWithSpace(tagName);
			newTag = new Tag(formattedTagName);
			tagRepo.save(newTag);
		}
		return "redirect:/tags/edit";
	}

	@PostMapping("/delete")
	public String deleteTagByName(String tagName) {
		Tag tag = tagRepo.findByNameIgnoreCase(tagName);
		
		if(tag != null) {
			tagRepo.delete(tag);
		}
		return "redirect:/tags/edit";
	}
	

}
