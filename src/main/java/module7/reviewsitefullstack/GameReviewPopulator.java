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
		Tag ninja = tagRepo.save(new Tag("ninjas"));
		
		
		//String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink
		GameReview root = gameReviewRepo.save(new GameReview("Root", "2-4", "60 to 90 min", "Multiple factions are vying for control of the forest (or just reputation) in their own, unique way.", "http://ledergames.com/root/", ".png", board, competitive, twoPlayer, strategy, asymmetric));
		GameReview mind = gameReviewRepo.save(new GameReview("The Mind", "2-4", "20 min", "Simple rules: Play the cards in sequence.  The catch: you can't discuss what's in your hand at all.", "https://www.pandasaurusgames.com/product/the-mind/", ".png", card, coop, twoPlayer, strategy, abst));
		GameReview blackOrchestra = gameReviewRepo.save(new GameReview("Black Orchestra", "1-5", "90 min", "You and your friends play as people inside Germany during the rise of Hitler attempting to kill him and stop his reign of terror.", "https://www.starling.games/black-orchestra/", ".png", board, coop, ww2, strategy, singlePlayer, twoPlayer));
		GameReview concordia = gameReviewRepo.save(new GameReview("Concordia", "2-5", "90 min", "Part board game, part deck-builder: develop your trade network across Ancient Europe to dominate your rivals.", "http://riograndegames.com/Game/1279-Concordia", ".png", board, deckbuild, twoPlayer, strategy, competitive, greece));
		GameReview grizzled = gameReviewRepo.save(new GameReview("The Grizzled", "3-5", "30 min", "Playing as a group of WWI soldiers from France, your objective is not to win the war, just survive it.", "https://cmon.com/product/the-grizzled/the-grizzled", ".png", card, coop, ww1, strategy));
		GameReview wayOfPanda = gameReviewRepo.save(new GameReview("Way of the Panda", "2-4", "60 - 90 min", "Your clan and other panda clans are attempting to retake and rebuild your land from the human ninjas.", "https://cmon.com/product/way-of-the-panda/way-of-the-panda", ".png", board, strategy, competitive, twoPlayer, ninja));
	}
}
