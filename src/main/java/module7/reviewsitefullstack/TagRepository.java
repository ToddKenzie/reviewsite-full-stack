package module7.reviewsitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	Collection<Tag> findAllByOrderByNameAsc();

	Collection<Tag> findByGamesContains(Game game);

	Collection<Tag> findByGameExpansionsContains(GameExpansion gameExpansion);

	Tag findByNameIgnoreCase(String tagName);

}
