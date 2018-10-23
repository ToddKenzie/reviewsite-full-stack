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
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private GameExpansionRepository gameExpansionRepo;

	@Mock
	private GameExpansion gameXp;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkAndRouteToSingleGameReviewSite() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(gameReview));
		when(gameCategoryRepo.findByGameReviewsContains(gameReview)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGameReview(gameReview)).thenReturn(gameXp);

		mvc.perform(get("/gameReview/1")).andExpect(status().isOk());
		mvc.perform(get("/gameReview/1")).andExpect(view().name(is("singleGameReviewTemplate")));
	}

	@Test
	public void shouldNotBeOkForSingleGameReviewSiteWithInvalidId() throws Exception {
		mvc.perform(get("/gameReview/1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleGameReviewIntoModel() throws Exception {
		when(gameReviewRepo.findById(arbitraryId)).thenReturn(Optional.of(gameReview));
		when(gameCategoryRepo.findByGameReviewsContains(gameReview)).thenReturn(gameCat);
		when(gameExpansionRepo.findByGameReview(gameReview)).thenReturn(gameXp);
		mvc.perform(get("/gameReview/1")).andExpect(model().attribute("gameReview", is(gameReview)));
	}
	
	
	@Test
	public void shouldBeOkAndRouteToAllGameReviewsSite() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(gameReview, secondReview);
		when(gameReviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/gameReview/all")).andExpect(status().isOk());
		mvc.perform(get("/gameReview/all")).andExpect(view().name(is("gameReviewsTemplate")));
	}
	
	@Test
	public void shouldAddAllGameReviewsToModel() throws Exception {
		Collection<GameReview> allReviews = Arrays.asList(gameReview, secondReview);
		when(gameReviewRepo.findAllByOrderByNameAsc()).thenReturn(allReviews);
		mvc.perform(get("/gameReview/all")).andExpect(model().attribute("gameReviews", allReviews));
	}
			
}
