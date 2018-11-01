package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long>{
	
	Collection<Game> findAllByOrderByNameAsc();

	Collection<Game> findByGameCategory(GameCategory gameCategory);
	
	Collection<Game> findByTagsContains(Tag tag);
	
	Game findByGameExpansion(GameExpansion gameExpansion);
}
