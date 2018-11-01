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
public class GameReviewRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private GameReviewRepository gameReviewRepo;

	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	GameReview gReview1;
	GameReview gReview2;
	GameReview gReview3;
	
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

		gReview1 = gameReviewRepo.save(new GameReview("Root", "", "", "", "", "", board, coop, competitive));
		gReview2 = gameReviewRepo.save(new GameReview("Concordia", "", "", "", "", "", board, competitive));
		gReview3 = gameReviewRepo.save(new GameReview("The Mind", "", "", "", "", "", card, coop));
		
		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldGenerateGameReviewId() {
		Long testID = gReview1.getId();
		
		assertThat(testID, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadAReview() {
		long testID = gReview1.getId();
		
		Optional<GameReview> underTest = gameReviewRepo.findById(testID);
		GameReview testReview = underTest.get();
		assertThat(testReview, is(gReview1));
	}
	
	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		long boardID = board.getId();
			
		Optional<GameCategory> testBoard = gameCategoryRepo.findById(boardID);
		GameCategory underTest = testBoard.get();
		assertThat(underTest.getGameReviews(), containsInAnyOrder(gReview1, gReview2));
	}
	
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		long tagID = coop.getId();
		
		Optional<Tag> testTag = tagRepo.findById(tagID);
		Tag underTest = testTag.get();
		assertThat(underTest.getGameReviews(), containsInAnyOrder(gReview1, gReview3));
	}
	
	
}
