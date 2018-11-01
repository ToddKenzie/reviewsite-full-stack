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
public class CommentRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private CommentRepository commentRepo;
	
	Comment comment; 
	long commentId;
	String text;
	String username;
	
	
	@Before
	public void setUp() {
		text = "Sample text.";
		username = "JoeBob";
		
		comment = commentRepo.save(new Comment(text, username));
		commentId = comment.getId();

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

}
