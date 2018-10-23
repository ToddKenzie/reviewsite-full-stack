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

public class GameReviewControllerTest {
	
	@InjectMocks
	private GameReviewController underTest;
	
	@Mock
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview reviewA;
	
	@Mock
	private GameReview reviewB;
	
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
	private Model model;
	
	long arbitraryId = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addSingleReviewToModel() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(reviewA));
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("gameReview", reviewA);
	}
	
	@Test
	public void findSingleCategoryForSingleGameReviewToModel() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(reviewA));
		when(gameCategoryRepo.findByGameReviewsContains(reviewA)).thenReturn(gameCatA);
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("gameCategory", gameCatA);		
	}
	
	@Test
	public void findAllTagsForSingleGameReviewInModel() throws Exception {
		Collection<Tag> allTagsForReview = Arrays.asList(tagA, tagB);
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(reviewA));
		when(tagRepo.findByGameReviewsContains(reviewA)).thenReturn(allTagsForReview);
		
		underTest.findOneGameReview(arbitraryId, model);
		verify(model).addAttribute("tags", allTagsForReview);	
	}
	
	@Test
	public void addAllGameReviewsToModel() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(reviewA, reviewB);
		when(gameReviewRepo.findAllByOrderByNameAsc()).thenReturn(allReviews);
		
		underTest.findAllGameReviews(model);
		verify(model).addAttribute("gameReviews", allReviews);
	}
		
}
