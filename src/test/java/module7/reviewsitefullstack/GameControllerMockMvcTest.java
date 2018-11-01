package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private GameRepository gameRepo;
	
	@Mock
	private Game game;
	
	@Mock
	private Game secondGame;

	@MockBean
	private GameCategoryRepository gameCategoryRepo;
	
	@Mock
	private GameCategory gameCat;
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private GameExpansionRepository gameExpansionRepo;

	@Mock
	private GameExpansion gameXp;
	
	@Mock
	private Review review;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	long arbitraryId = 1;
	
	//single test issues due to creation of Review object
	@Test
	public void shouldBeOkAndRouteToSingleGameSite() throws Exception {
		when(gameRepo.findById(arbitraryId)).thenReturn(Optional.of(game));
		when(gameCategoryRepo.findByGamesContains(game)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGame(game)).thenReturn(gameXp);
		when(reviewRepo.findByGame(game)).thenReturn(review);

		mvc.perform(get("/gameReview/1")).andExpect(status().isOk());
		mvc.perform(get("/gameReview/1")).andExpect(view().name(is("singleGameReviewTemplate")));
	}

	@Test
	public void shouldNotBeOkForSingleGameSiteWithInvalidId() throws Exception {
		mvc.perform(get("/gameReview/1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleGameIntoModel() throws Exception {
		when(gameRepo.findById(arbitraryId)).thenReturn(Optional.of(game));
		when(gameCategoryRepo.findByGamesContains(game)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGame(game)).thenReturn(gameXp);
		mvc.perform(get("/gameReview/1")).andExpect(model().attribute("game", is(game)));
	}
	
	
	@Test
	public void shouldBeOkAndRouteToAllGamesSite() throws Exception {
		Collection<Game> allReviews = Arrays.asList(game, secondGame);
		when(gameRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/gameReview/all")).andExpect(status().isOk());
		mvc.perform(get("/gameReview/all")).andExpect(view().name(is("gameReviewsTemplate")));
	}
	
	@Test
	public void shouldAddAllGamesToModel() throws Exception {
		Collection<Game> allReviews = Arrays.asList(game, secondGame);
		when(gameRepo.findAllByOrderByNameAsc()).thenReturn(allReviews);
		mvc.perform(get("/gameReview/all")).andExpect(model().attribute("games", allReviews));
	}
			
}
