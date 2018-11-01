package module7.reviewsitefullstack;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edit-tags")
public class TagRestController {

	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private GameRepository gameReviewRepo;
	
	@RequestMapping("/")
	public Iterable<Tag> findAllTags() {
		return tagRepo.findAll();
	}
	
	@RequestMapping("/gameReview/{id}")
	public Collection<Tag> findAllTagsByGameReview(@PathVariable long id) {
		Optional<Game> gameReview = gameReviewRepo.findById(id);
		return tagRepo.findByGamesContains(gameReview.get());
	}
}
