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
@WebMvcTest(GameReviewController.class)
public class GameReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview gameReview;
	
	@Mock
	private GameReview secondReview;

	@MockBean
	private GameCategoryRepository gameCategoryRepo;
	
	@Mock
	private GameCategory gameCat;
	
	@Mock
	private GameCategory secondCat;

	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag secondTag;

	@MockBean
	private GameExpansionRepository gameExpansionRepo;

	@Mock
	private GameExpansion gameXp;
	
	@Mock
	private GameExpansion secondXp;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkAndRouteToSingleGameReviewSite() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(gameReview));
		when(gameCategoryRepo.findByGameReviewsContains(gameReview)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGameReview(gameReview)).thenReturn(gameXp);

		mvc.perform(get("/game-review?id=1")).andExpect(status().isOk());
		mvc.perform(get("/game-review?id=1")).andExpect(view().name(is("singleGameReviewTemplate")));
	}

	@Test
	public void shouldNotBeOkForSingleGameReviewSiteWithInvalidId() throws Exception {
		mvc.perform(get("/game-review?id=0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleGameReviewIntoModel() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(gameReview));
		when(gameCategoryRepo.findByGameReviewsContains(gameReview)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGameReview(gameReview)).thenReturn(gameXp);
		mvc.perform(get("/game-review?id=1")).andExpect(model().attribute("gameReview", is(gameReview)));
	}
	
	
	@Test
	public void shouldBeOkAndRouteToAllGameReviewsSite() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(gameReview, secondReview);
		when(gameReviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/allGameReviews")).andExpect(status().isOk());
		mvc.perform(get("/allGameReviews")).andExpect(view().name(is("gameReviewsTemplate")));
	}
	
	@Test
	public void shouldAddAllGameReviewsToModel() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(gameReview, secondReview);
		when(gameReviewRepo.findAllByOrderByNameAsc()).thenReturn(allReviews);
		mvc.perform(get("/allGameReviews")).andExpect(model().attribute("gameReviews", allReviews));
	}
		
	@Test
	public void shouldBeOkAndRouteToSingleExpansionSite() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp));
		when(gameReviewRepo.findByGameExpansion(gameXp)).thenReturn(gameReview);
		mvc.perform(get("/expansion?id=1")).andExpect(status().isOk());
		mvc.perform(get("/expansion?id=1")).andExpect(view().name(is("singleExpansionTemplate")));
	}
	
	@Test
	public void shouldNotBeOkForSingleExpansionSiteWithInvalidId() throws Exception {
		mvc.perform(get("/expansion?id=0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldAddSingleExpansionIntoModel() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp));
		when(gameReviewRepo.findByGameExpansion(gameXp)).thenReturn(gameReview);
		mvc.perform(get("/expansion?id=1")).andExpect(model().attribute("gameExpansion", gameXp));
		
	}
	
	@Test
	public void shouldBeOkAndRouteToAllExpansionsSite() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp, secondXp);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		mvc.perform(get("/allExpansions")).andExpect(status().isOk());
		mvc.perform(get("/allExpansions")).andExpect(view().name(is("allExpansionsTemplate")));
	}

	@Test
	public void shouldAddAllExpansionsIntoModel() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp, secondXp);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		mvc.perform(get("/allExpansions")).andExpect(model().attribute("gameExpansions", allExpansions));
	}
	
}
