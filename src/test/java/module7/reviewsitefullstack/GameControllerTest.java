package module7.reviewsitefullstack;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class GameControllerTest {
	
	@InjectMocks
	private GameController underTest;
	
	@Mock
	private GameRepository gameRepo;
	
	@Mock
	private Game gameA;
	
	@Mock
	private Game gameB;
	
	@Mock
	private GameCategoryRepository gameCategoryRepo;
	
	@Mock
	private GameCategory gameCatA;
	
	@Mock
	private GameCategory gameCatB;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Tag tagA;
	
	@Mock
	private Tag tagB;
	
	@Mock
	private GameExpansionRepository gameExpansionRepo;
	
	@Mock
	private GameExpansion gameXp1;
	
	@Mock
	private GameExpansion gameXp2;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@Mock
	private CommentRepository commentRepo;
	
	@Mock
	private Comment comment;
	
	@Mock
	private Comment comment2;
	
	@Mock
	private Model model;
	
	long arbitraryId = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addSingleReviewToModel() throws Exception {
		when(gameRepo.findById(arbitraryId)).thenReturn(Optional.of(gameA));
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("game", gameA);
	}
	
	@Test
	public void findSingleCategoryForSingleGameReviewToModel() throws Exception {
		when(gameRepo.findById(arbitraryId)).thenReturn(Optional.of(gameA));
		when(gameCategoryRepo.findByGamesContains(gameA)).thenReturn(gameCatA);
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("gameCategory", gameCatA);		
	}
	
	@Test
	public void findAllTagsForSingleGameReviewInModel() throws Exception {
		Collection<Tag> allTagsForReview = Arrays.asList(tagA, tagB);
		when(gameRepo.findById(arbitraryId)).thenReturn(Optional.of(gameA));
		when(tagRepo.findByGamesContains(gameA)).thenReturn(allTagsForReview);
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("tags", allTagsForReview);	
	}
	
	@Test
	public void addAllGameReviewsToModel() throws Exception {
		Collection<Game> allReviews = Arrays.asList(gameA, gameB);
		when(gameRepo.findAllByOrderByNameAsc()).thenReturn(allReviews);
		
		underTest.findAllGameReviews(model);
		verify(model).addAttribute("games", allReviews);
	}
	
	@Test
	public void addReviewAndAllCommentsToModel() throws Exception {
		Collection<Comment> allComments = Arrays.asList(comment, comment2);
		
		when(reviewRepo.findById(arbitraryId)).thenReturn(Optional.of(review));
		when(commentRepo.findByReviewOrderByTimeStampAsc(review)).thenReturn(allComments);
		
		underTest.findReviewAndCommentsForGame(arbitraryId, model);
		verify(model).addAttribute("review", review);
		verify(model).addAttribute("comments", allComments);
		
	}
		
}
