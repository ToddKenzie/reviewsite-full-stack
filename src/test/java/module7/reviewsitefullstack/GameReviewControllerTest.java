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
		when(gameReviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllGameReviews(model);
		verify(model).addAttribute("gameReviews", allReviews);
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
		when(gameReviewRepo.findByGameCategoryContains(gameCatA)).thenReturn(allReviewsWithCategory);
		
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
	
	@Test
	public void addSingleTagToModel() throws Exception {
		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tagA));
		
		underTest.findOneTag(arbitraryId, model);
		verify(model).addAttribute("tag", tagA);
	}
	
	@Test
	public void checkForGameReviewsWithSingleTagModel() throws Exception {
		Collection<GameReview> reviewsWithTag = Arrays.asList(reviewA, reviewB);
		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tagA));
		when(gameReviewRepo.findByTagsContains(tagA)).thenReturn(reviewsWithTag);
		
		underTest.findOneTag(arbitraryId, model);
		verify(model).addAttribute("gameReviews", reviewsWithTag);
	}
	
	@Test
	public void addAllTagsToModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tagA, tagB);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}
	
	@Test
	public void addSingleExpansionToModel() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp1));
		
		underTest.findOneExpansion(arbitraryId, model);
		verify(model).addAttribute("gameExpansion", gameXp1);
	}
	
	@Test
	public void findAllTagsForSingleExpansionToModel() throws Exception {
		Collection<Tag> allTagsForXp = Arrays.asList(tagA, tagB);
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp1));
		when(tagRepo.findByGameExpansionsContains(gameXp1)).thenReturn(allTagsForXp);
		
		underTest.findOneExpansion(arbitraryId, model);
		verify(model).addAttribute("tags", allTagsForXp);
	}
	
	@Test
	public void addAllExpansionsToModel() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp1, gameXp2);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		
		underTest.findAllExpansions(model);
		verify(model).addAttribute("gameExpansions", allExpansions);
	}

}
