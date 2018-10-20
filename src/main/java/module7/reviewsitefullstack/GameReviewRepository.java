package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameReviewRepository extends CrudRepository<GameReview, Long>{
	
	Collection<GameReview> findAllByOrderByNameAsc();

	Collection<GameReview> findByGameCategory(GameCategory gameCategory);
	
	Collection<GameReview> findByTagsContains(Tag tag);
	
	GameReview findByGameExpansion(GameExpansion gameExpansion);
}
