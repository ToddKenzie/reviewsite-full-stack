package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameExpansionRepository extends CrudRepository<GameExpansion, Long> {

	GameExpansion findByGameReview(GameReview gameReview);
	
	Collection<GameExpansion> findByTagsContains(Tag tag);

}
