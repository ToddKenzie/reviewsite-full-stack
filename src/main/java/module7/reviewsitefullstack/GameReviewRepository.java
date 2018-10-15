package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameReviewRepository extends CrudRepository<GameReview, Long>{

	Collection<GameReview> findByGameCategoryContains(GameCategory gameCategory);
	
	Collection<GameReview> findByTagsContains(Tag tag);
}
