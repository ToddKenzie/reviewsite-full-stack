package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Collection<Tag> findAllByOrderByNameAsc();

	Collection<Tag> findByGameReviewsContains(GameReview gameReview);

	Collection<Tag> findByGameExpansionsContains(GameExpansion gameExpansion);

}
