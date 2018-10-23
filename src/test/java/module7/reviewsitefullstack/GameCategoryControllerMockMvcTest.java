package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GameCategoryController.class)
public class GameCategoryControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private GameCategoryRepository gameCategoryRepo;
	
	@Mock
	private GameCategory gameCat;
	
	@Mock
	private GameCategory secondCat;

	@MockBean
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview gameReview;
	
	@Mock
	private GameReview secondReview;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkAndRouteToSingleGameCategorySite() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCat));
		mvc.perform(get("/gameCategory/1")).andExpect(status().isOk());
//		mvc.perform(get("/gameCategory/1")).andExpect(view().name(is("singleGameCategoryTemplate")));
	}
	
	@Test
	public void shouldNotBeOkForSingleGameCategorySiteWithInvalidId() throws Exception {
		mvc.perform(get("/gameCategory/0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldAddSingleCategoryIntoModel() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCat));
		mvc.perform(get("/gameCategory/1")).andExpect(model().attribute("gameCategory", gameCat));
	}
	
	
	@Test
	public void shouldBeOkAndRouteToAllGameCategoriesSite() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCat, secondCat);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/gameCategory/all")).andExpect(status().isOk());
		mvc.perform(get("/gameCategory/all")).andExpect(view().name(is("gameCategoriesTemplate")));
	}

	@Test
	public void shouldAddAllCategoriesIntoModel() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCat, secondCat);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/gameCategory/all")).andExpect(model().attribute("gameCategories", allCategories));
	}


}
