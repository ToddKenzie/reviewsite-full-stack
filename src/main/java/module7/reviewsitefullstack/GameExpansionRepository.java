package module7.reviewsitefullstack;

import org.springframework.data.repository.CrudRepository;

public interface GameExpansionRepository extends CrudRepository<GameExpansion, Long> {

	GameExpansion findByGameReviewContains(GameReview gameReview);

}
