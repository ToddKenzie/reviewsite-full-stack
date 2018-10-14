package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

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

	
	@Test
	public void shouldGenerateGameReviewId() {
		GameReview review1 = gameReviewRepo.save(new GameReview("Root", null));
		Long testID = review1.getId();
		
		entity.flush();
		entity.clear();

		assertThat(testID, notNullValue());
	}
	
	@Test
	public void shouldSaveAndLoadAReview() {
		GameReview review1 = gameReviewRepo.save(new GameReview("Root", null));
		long testID = review1.getId();
		
		entity.flush();
		entity.clear();
		
		Optional<GameReview> underTest = gameReviewRepo.findById(testID);
		GameReview testReview = underTest.get();
		assertThat(testReview, is(review1));
	}
	
	@Test
	public void shouldEstablishReviewToCategoryRelationship() {
		GameCategory board = gameCategoryRepo.save(new GameCategory("board"));
		GameCategory card = gameCategoryRepo.save(new GameCategory("card"));
		long boardID = board.getId();
		
		GameReview review1 = gameReviewRepo.save(new GameReview("Root", board));
		GameReview review2 = gameReviewRepo.save(new GameReview("Concordia", board));
		@SuppressWarnings("unused")
		GameReview review3 = gameReviewRepo.save(new GameReview("The Mind", card));
		
		entity.flush();
		entity.clear();
		
		Optional<GameCategory> testBoard = gameCategoryRepo.findById(boardID);
		GameCategory underTest = testBoard.get();
		assertThat(underTest.getGameReviews(), containsInAnyOrder(review1, review2));
		
	}
	
	@Test
	public void shouldEstablishReviewToTagRelationship() {
		Tag coop = tagRepo.save(new Tag("co-op"));
		Tag competitive = tagRepo.save(new Tag("competitive"));
		long tagID = coop.getId();
		
		GameReview review1 = gameReviewRepo.save(new GameReview("Root", null, coop, competitive));
		@SuppressWarnings("unused")
		GameReview review2 = gameReviewRepo.save(new GameReview("Concordia", null, competitive));
		GameReview review3 = gameReviewRepo.save(new GameReview("The Mind", null, coop));
		
		entity.flush();
		entity.clear();
		
		Optional<Tag> testTag = tagRepo.findById(tagID);
		Tag underTest = testTag.get();
		assertThat(underTest.getGameReviews(), containsInAnyOrder(review1, review3));
	}
	
	
}
