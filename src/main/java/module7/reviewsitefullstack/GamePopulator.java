package module7.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GamePopulator implements CommandLineRunner {

	@Resource
	private GameCategoryRepository gameCategoryRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private GameRepository gameReviewRepo;

	@Resource
	private GameExpansionRepository gameExpansionRepo;
	
	@Resource 
	private ReviewRepository reviewRepo;

	@Override
	public void run(String... args) throws Exception {

		GameCategory board = gameCategoryRepo.save(new GameCategory("Board"));
		GameCategory card = gameCategoryRepo.save(new GameCategory("Card"));
		GameCategory dice = gameCategoryRepo.save(new GameCategory("Dice"));
		
		Tag singlePlayer = tagRepo.save(new Tag("Single player"));
		Tag twoPlayer = tagRepo.save(new Tag("2 player"));
		Tag moreThanFive = tagRepo.save(new Tag("More than 5 player"));
		Tag coop = tagRepo.save(new Tag("Co-operative"));
		Tag competitive = tagRepo.save(new Tag("Competitive"));
		Tag deckbuild = tagRepo.save(new Tag("Deck-builder"));
		Tag strategy = tagRepo.save(new Tag("Strategy"));
		Tag luck = tagRepo.save(new Tag("Luck"));
		Tag ww2 = tagRepo.save(new Tag("WW II"));
		Tag ww1 = tagRepo.save(new Tag("WW I"));
		Tag greece = tagRepo.save(new Tag("Ancient Greece"));
		Tag abst = tagRepo.save(new Tag("Abstract"));
		Tag asymmetric = tagRepo.save(new Tag("Asymmetric"));
		Tag ninja = tagRepo.save(new Tag("Ninjas"));
		Tag animals = tagRepo.save(new Tag("Animals"));

		// String name, String rangeOfPlayers, String timeToComplete, String synopsis,
		// String weblink, String pictureLink
		Game root = gameReviewRepo.save(new Game("Root", "2-4", "60 to 90 min",
				"Multiple factions are vying for control of the forest (or just reputation) in their own, unique way.",
				"http://ledergames.com/root/", "root.png",
				board, competitive, animals, twoPlayer, strategy, asymmetric));
		Review reviewRoot = reviewRepo.save(new Review("Prow scuttle parrel provost Sail ho shrouds spirits boom mizzenmast yardarm. Pinnace holystone mizzenmast quarter crow's nest nipperkin grog yardarm hempen halter furl. Swab barque interloper chantey doubloon starboard grog black jack gangway rutter. <br /><br /> Deadlights jack lad schooner scallywag dance the hempen jig carouser broadside cable strike colors. Bring a spring upon her cable holystone blow the man down spanker Shiver me timbers to go on account lookout wherry doubloon chase. Belay yo-ho-ho keelhaul squiffy black spot yardarm spyglass sheet transom heave to.",
				root));
		
		Game mind = gameReviewRepo.save(new Game("The Mind", "2-4", "20 min",
				"Simple rules: Play the cards in sequence.  The catch: you can't discuss what's in your hand at all.",
				"https://www.pandasaurusgames.com/product/the-mind/", "the-mind.png",
				card, coop, twoPlayer, strategy, abst));
		Review reviewMind = reviewRepo.save(new Review("Sutler scallywag Yellow Jack jolly boat ho measured fer yer chains rope's end hearties grog blossom draft. Hands holystone jib brigantine hogshead ho pinnace careen chandler scuttle. Gabion pillage barque lookout topgallant shrouds skysail bring a spring upon her cable barkadeer Blimey."
				+ "<br /><br />"
				+ "Quarter bilge water rutters lanyard heave to sheet hang the jib avast salmagundi skysail. Gold Road lugger barque loot aye wench hearties Buccaneer no prey, no pay port. Jack aft scuttle heave to case shot quarter hempen halter sloop Sea Legs heave down.",
				mind));
		
		Game blackOrchestra = gameReviewRepo.save(new Game("Black Orchestra", "1-5", "90 min",
				"You and your friends play as people inside Germany during the rise of Hitler attempting to kill him and stop his reign of terror.",
				"https://www.starling.games/black-orchestra/", "black-orchestra.png",
				board, coop, ww2, strategy, singlePlayer, twoPlayer));
		Review reviewBlackOrch = reviewRepo.save(new Review("Chase guns Letter of Marque wherry trysail matey fathom quarter starboard belaying pin gangplank. Wench Cat o'nine tails ballast rigging sheet fire in the hole rope's end spike mutiny Shiver me timbers. Lookout crow's nest dead men tell no tales gangway clipper snow brigantine spanker schooner hail-shot."
				+ "<br /><br />"
				+ "Lass loaded to the gunwalls grog lateen sail pillage gangplank driver splice the main brace hulk knave. Flogging grog blossom scuttle main sheet salmagundi rum stern provost hearties wench. Lugger blow the man down overhaul swing the lead man-of-war snow furl run a shot across the bow line scourge of the seven seas.",
				blackOrchestra));
		
		Game concordia = gameReviewRepo.save(new Game("Concordia", "2-5", "90 min",
				"Part board game, part deck-builder: develop your trade network across Ancient Europe to dominate your rivals.",
				"http://riograndegames.com/Game/1279-Concordia", "concordia.png",
				board, deckbuild, twoPlayer, strategy, competitive, greece));
		Review reviewConcordia = reviewRepo.save(new Review("Barkadeer hang the jib pink weigh anchor reef sails deadlights chandler Spanish Main tender marooned. Line nipper marooned lugsail lateen sail weigh anchor aye strike colors Shiver me timbers provost. Maroon grapple lanyard log scurvy American Main spanker Jack Ketch yardarm Blimey.\r\n"
				+ "<br /><br />"
				+ "Sheet bilged on her anchor jib wench stern jack swab fire in the hole clap of thunder gangplank. Poop deck gangplank ballast wherry lad starboard fire ship code of conduct reef crack Jennys tea cup. Draft jury mast strike colors gibbet measured fer yer chains lookout avast spyglass scurvy code of conduct.",
				concordia));
		
		Game grizzled = gameReviewRepo.save(new Game("The Grizzled", "3-5", "30 min",
				"Playing as a group of WWI soldiers from France, your objective is not to win the war, just survive it.",
				"https://cmon.com/product/the-grizzled/the-grizzled", "the-grizzled.png",
				card, coop, ww1, strategy));
		Review reviewGrizzled = reviewRepo.save(new Review("Jack Tar jib Jack Ketch jack killick jolly boat lad sutler keel parrel. Rigging bring a spring upon her cable crimp Gold Road lookout landlubber or just lubber gally Jack Ketch topmast heave to. Bowsprit lookout scourge of the seven seas Shiver me timbers American Main keelhaul jury mast clap of thunder black spot knave."
				+ "<br /><br />"
				+ "Gun jolly boat ye scallywag maroon fire in the hole belaying pin Blimey parley bucko. Nipperkin hang the jib reef sails swab jury mast gabion Sea Legs Corsair quarter coxswain. Nelsons folly belaying pin fathom skysail stern Davy Jones' Locker lateen sail Sea Legs wench hardtack.",
				grizzled));
		
		Game wayOfPanda = gameReviewRepo.save(new Game("Way of the Panda", "2-4", "60 - 90 min",
				"Your clan and other panda clans are attempting to retake and rebuild your land from the human ninjas.",
				"https://cmon.com/product/way-of-the-panda/way-of-the-panda", "way-of-the-panda.png",
				board, animals, strategy, competitive, twoPlayer, ninja));
		Review reviewWayOfPanda = reviewRepo.save(new Review("Careen bowsprit bilge water red ensign ye lee chase guns me list Spanish Main. Ho blow the man down salmagundi barque Cat o'nine tails Spanish Main snow crow's nest grapple plunder. Grapple rope's end quarterdeck scuttle fire ship marooned brig me topgallant bring a spring upon her cable."
				+ "<br /><br />"
				+ "Parley crack Jennys tea cup Buccaneer gabion Plate Fleet pinnace log square-rigged belaying pin yardarm. Lee log jury mast chase line Arr draught mizzen quarterdeck Pieces of Eight. Bring a spring upon her cable cackle fruit gangway to go on account matey crack Jennys tea cup Plate Fleet six pounders hempen halter grog blossom.",
				wayOfPanda));
				

		GameExpansion concordiaSalsa = gameExpansionRepo.save(new GameExpansion("Concordia Salsa", "2-5", "90 min",
				"Salsa adds a new double sided board, a new \"wildcard\" resource, and a forum of upgrade cards to change the dynamics of the game.  There are also other boards that can be purchased separately to create new variants of the game.",
				"http://riograndegames.com/Game/1303-Concordia-Salsa", "salsa.png", concordia, twoPlayer, strategy,
				deckbuild, greece, competitive));
		GameExpansion rootRiverFolk = gameExpansionRepo.save(new GameExpansion("Root: The Riverfolk Expansion", "1-6",
				"60 to 90 min",
				"Riverfolk add 2 new factions, an additional Vagabond, and an AI-played Cat faction to increase the opportunities, variants, players, and fun.",
				"https://leder-games.myshopify.com/products/root-the-riverfolk-expansion?variant=19417078726714",
				"river-folk.png", root, singlePlayer, twoPlayer, moreThanFive, competitive, coop, animals, asymmetric,
				strategy));
		GameExpansion grizzledAtYourOrders = gameExpansionRepo.save(new GameExpansion("The Grizzled: At Your Orders!",
				"1-5", "30 min",
				"At Your Orders! adds a task deck that you can modify the difficulty.  This expansion helps balance the game in ways due to the new mechanics introduced.  The expansion also adds a solo-play variant.",
				"https://www.coolstuffinc.com/p/225304", "orders.png", grizzled, coop, singlePlayer, twoPlayer, ww1,
				strategy));

	}
}
