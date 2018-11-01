package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GameRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private GameRepository gameRepo;

	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	Game game1;
	Game game2;
	Game game3;
	
	GameCategory board;
	GameCategory card;
	
	Tag coop;
	Tag competitive;
	
	@Before
	public void setUp() {
		board = gameCategoryRepo.save(new GameCategory("board"));
		card = gameCategoryRepo.save(new GameCategory("card"));
		
		coop = tagRepo.save(new Tag("co-op"));
		competitive = tagRepo.save(new Tag("competitive"));

		game1 = gameRepo.save(new Game("Root", "", "", "", "", "", board, coop, competitive));
		game2 = gameRepo.save(new Game("Concordia", "", "", "", "", "", board, competitive));
		game3 = gameRepo.save(new Game("The Mind", "", "", "", "", "", card, coop));
		
		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldGenerateGameReviewId() {
		Long testID = game1.getId();
		
		assertThat(testID, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadAReview() {
		long testID = game1.getId();
		
		Optional<Game> underTest = gameRepo.findById(testID);
		Game testReview = underTest.get();
		assertThat(testReview, is(game1));
	}
	
	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		long boardID = board.getId();
			
		Optional<GameCategory> testBoard = gameCategoryRepo.findById(boardID);
		GameCategory underTest = testBoard.get();
		assertThat(underTest.getGames(), containsInAnyOrder(game1, game2));
	}
	
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		long tagID = coop.getId();
		
		Optional<Tag> testTag = tagRepo.findById(tagID);
		Tag underTest = testTag.get();
		assertThat(underTest.getGames(), containsInAnyOrder(game1, game3));
	}
	
	
}
