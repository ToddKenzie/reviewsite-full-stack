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
		verify(model).addAttribute("gameReviews", reviewA);
	}
	
	@Test
	public void addAllGameReviewsToModel() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(reviewA, reviewB);
		when(gameReviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllGameReviews(model);
		verify(model).addAttribute("gameReviews", allReviews);
	}
	
	@Test
	public void addSingleCategoryToModel() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCatA));
		
		underTest.findOneGameCategory(arbitraryId, model);
		verify(model).addAttribute("gameCategories", gameCatA);
	}
	
	@Test
	public void addAllGameCategoriesToModel() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCatA, gameCatB);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllGameCategories(model);
		verify(model).addAttribute("gameCategories", allCategories);
	}
	
	@Test
	public void addSingleTagToModel() throws Exception {
		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tagA));
		
		underTest.findOneTag(arbitraryId, model);
		verify(model).addAttribute("tags", tagA);
	}
	
	@Test
	public void addAllTagsToModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tagA, tagB);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}

}
