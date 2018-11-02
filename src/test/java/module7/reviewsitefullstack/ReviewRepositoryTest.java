package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
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
public class ReviewRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private GameRepository gameRepo;
	
	Long reviewId;
	Review review1;
	String reviewText;
	Game game1;
	
	@Before
	public void setUp() {
		game1 = gameRepo.save(new Game("Root", "", "", "", "", "", null));
		reviewText = "This is test text";
		review1 = reviewRepo.save(new Review(reviewText, game1));
		reviewId = review1.getId();
		
		entity.flush();
		entity.clear();
	}
	
	
	@Test
	public void verifyReviewHasID() {
		assertThat(reviewId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadReview() {	
		Optional<Review> underTest = reviewRepo.findById(reviewId);
		Review testReview = underTest.get();
		assertThat(testReview, is(review1));
	}
	
	@Test
	public void reviewShouldContainText() {
		Optional<Review> underTest = reviewRepo.findById(reviewId);
		String testReviewText = underTest.get().getText();
		assertThat(testReviewText, is(reviewText));
	}

	@Test
	public void reviewShouldBeAssociatedWithGame() {
		Optional<Review> underTest = reviewRepo.findById(reviewId);
		Game testGameObject = underTest.get().getGame();
		assertThat(testGameObject, is(game1));
	}
}
