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

public class GameCategoryControllerTest {
	
	@InjectMocks
	private GameCategoryController underTest;
	
	@Mock
	private GameCategoryRepository gameCategoryRepo;
	
	@Mock
	private GameCategory gameCatA;
	
	@Mock
	private GameCategory gameCatB;
	
	@Mock
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview reviewA;
	
	@Mock
	private GameReview reviewB;
	
	@Mock
	private Model model;
	
	long arbitraryId = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addSingleCategoryToModel() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCatA));
		
		underTest.findOneGameCategory(arbitraryId, model);
		verify(model).addAttribute("gameCategory", gameCatA);
	}
	
	@Test
	public void findAllGameReviewsWithCategoryInModel() throws Exception {
		Collection<GameReview> allReviewsWithCategory = Arrays.asList(reviewA, reviewB);
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCatA));
		when(gameReviewRepo.findByGameCategory(gameCatA)).thenReturn(allReviewsWithCategory);
		
		underTest.findOneGameCategory(arbitraryId, model);
		verify(model).addAttribute("gameReviews", allReviewsWithCategory);

	}
	
	@Test
	public void addAllGameCategoriesToModel() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCatA, gameCatB);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllGameCategories(model);
		verify(model).addAttribute("gameCategories", allCategories);
	}

}
