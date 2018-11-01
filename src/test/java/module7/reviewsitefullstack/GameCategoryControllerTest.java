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
	private GameRepository gameRepo;
	
	@Mock
	private Game gameA;
	
	@Mock
	private Game gameB;
	
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
	public void findAllGamesWithCategoryInModel() throws Exception {
		Collection<Game> allGamesWithCategory = Arrays.asList(gameA, gameB);
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCatA));
		when(gameRepo.findByGameCategory(gameCatA)).thenReturn(allGamesWithCategory);
		
		underTest.findOneGameCategory(arbitraryId, model);
		verify(model).addAttribute("games", allGamesWithCategory);

	}
	
	@Test
	public void addAllGameCategoriesToModel() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCatA, gameCatB);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		
		underTest.findAllGameCategories(model);
		verify(model).addAttribute("gameCategories", allCategories);
	}

}
