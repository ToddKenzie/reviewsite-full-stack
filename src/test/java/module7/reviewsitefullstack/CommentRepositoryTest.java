package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
public class CommentRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Resource
	private GameRepository gameRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	Comment comment; 
	Comment comment2; 
	
	long commentId;
	String text;
	String username;
	
	Review review;
	Game game;
	
	
	@Before
	public void setUp() {
		text = "Sample text.";
		username = "JoeBob";
		game = gameRepo.save(new Game("Root", "", "", "", "", "", null));
		review = reviewRepo.save(new Review("reviewText", game));
		
		comment = commentRepo.save(new Comment(review, text, username));
		commentId = comment.getId();
		comment2 = commentRepo.save(new Comment(review, "text2", username));

		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldAddCommentToRepo() {
		assertThat(commentId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Optional<Comment> underTest = commentRepo.findById(commentId);
		Comment testComment = underTest.get();
		assertThat(testComment, is(comment));
	}
	
	@Test
	public void shouldHaveText() {
		Optional<Comment> underTest = commentRepo.findById(commentId);
		String commentText = underTest.get().getText();
		assertThat(commentText, is(text));
	}
	
	@Test
	public void shouldHaveUserName() {
		Optional<Comment> underTest = commentRepo.findById(commentId);
		String commentUsername = underTest.get().getUsername();
		assertThat(commentUsername, is(username));
	}
	
	@Test
	public void shouldAutoSetTimeOnCreation() {
		Optional<Comment> underTest = commentRepo.findById(commentId);
		String commentTimeStamp = underTest.get().getTimeStamp();
		assertThat(commentTimeStamp, notNullValue());
	}
	
	@Test
	public void setRelationshipWithReview() {
		long reviewId = review.getId();
		
		Optional<Review> reviewTest = reviewRepo.findById(reviewId);
		Review underTest = reviewTest.get();
		assertThat(underTest.getComments(), containsInAnyOrder(comment, comment2));
	}

}
