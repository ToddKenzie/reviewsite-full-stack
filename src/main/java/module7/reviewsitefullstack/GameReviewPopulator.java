package module7.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameReviewPopulator implements CommandLineRunner{
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private GameReviewRepository gameReviewRepo;
	
	@Resource
	private GameExpansionRepository gameExpansionRepo;

	@Override
	public void run(String...args) throws Exception {
		
		GameCategory board = gameCategoryRepo.save(new GameCategory("board"));
		GameCategory card = gameCategoryRepo.save(new GameCategory("card"));
		
		Tag singlePlayer = tagRepo.save(new Tag("single player"));
		Tag twoPlayer = tagRepo.save(new Tag("2 player"));
		Tag moreThanFive = tagRepo.save(new Tag("more than 5 player"));
		Tag coop = tagRepo.save(new Tag("co-operative"));
		Tag competitive = tagRepo.save(new Tag("competitive"));
		Tag deckbuild = tagRepo.save(new Tag("deck-builder"));
		Tag strategy = tagRepo.save(new Tag("strategy"));
		Tag luck = tagRepo.save(new Tag("luck"));
		Tag ww2 = tagRepo.save(new Tag("WW II"));
		Tag ww1 = tagRepo.save(new Tag("WW I"));
		Tag greece = tagRepo.save(new Tag("Ancient Greece"));
		Tag abst = tagRepo.save(new Tag("abstract"));
		Tag asymmetric = tagRepo.save(new Tag("asymmetric"));
		
		GameReview root = gameReviewRepo.save(new GameReview("Root", board, competitive, twoPlayer, strategy, asymmetric));
		GameReview mind = gameReviewRepo.save(new GameReview("The Mind", card, coop, twoPlayer, strategy, abst));
		
		
	}
}
