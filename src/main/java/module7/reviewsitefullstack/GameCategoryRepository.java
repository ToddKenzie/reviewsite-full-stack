package module7.reviewsitefullstack;

import org.springframework.data.repository.CrudRepository;

public interface GameCategoryRepository extends CrudRepository<GameCategory, Long> {

	GameCategory findByGameReviewsContains(GameReview gameReview);

}
