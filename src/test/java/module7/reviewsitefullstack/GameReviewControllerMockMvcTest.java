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
	public void shouldBeOkAndRouteToSingleGameCategorySite() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCat));
		mvc.perform(get("/game-category?id=1")).andExpect(status().isOk());
		mvc.perform(get("/game-category?id=1")).andExpect(view().name(is("singleGameCategoryTemplate")));
	}
	
	@Test
	public void shouldNotBeOkForSingleGameCategorySiteWithInvalidId() throws Exception {
		mvc.perform(get("/game-category?id=0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldAddSingleCategoryIntoModel() throws Exception {
		when(gameCategoryRepo.findById(arbitraryId)).thenReturn(Optional.of(gameCat));
		mvc.perform(get("/game-category?id=1")).andExpect(model().attribute("gameCategory", gameCat));
	}
	
	
	@Test
	public void shouldBeOkAndRouteToAllGameCategoriesSite() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCat, secondCat);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/allGameCategories")).andExpect(status().isOk());
		mvc.perform(get("/allGameCategories")).andExpect(view().name(is("gameCategoriesTemplate")));
	}

	@Test
	public void shouldAddAllCategoriesIntoModel() throws Exception {
		Collection<GameCategory> allCategories = Arrays.asList(gameCat, secondCat);
		when(gameCategoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/allGameCategories")).andExpect(model().attribute("gameCategories", allCategories));
	}

//	@Test
//	public void shouldBeOkAndRouteToSingleTagSite() throws Exception {
//		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tag));
//		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
//		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("singleTagTemplate")));
//	}
//	
//	@Test
//	public void shouldNotBeOkForSingleTagSiteWithInvalidId() throws Exception {
//		mvc.perform(get("/tag?id=0")).andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void shouldAddSingleTagIntoModel() throws Exception {
//		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tag));
//		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tag", tag));
//	}
//
//	@Test
//	public void shouldBeOkAndRouteToAllTagSite() throws Exception {
//		Collection<Tag> allTags = Arrays.asList(tag, secondTag);
//		when(tagRepo.findAll()).thenReturn(allTags);
//		mvc.perform(get("/allTags")).andExpect(status().isOk());
//		mvc.perform(get("/allTags")).andExpect(view().name(is("allTagsTemplate")));
//	}
	
	@Test
	public void shouldAddAllTagsIntoModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, secondTag);
		when(tagRepo.findAllByOrderByNameAsc()).thenReturn(allTags);
		mvc.perform(get("/allTags")).andExpect(model().attribute("tags", allTags));
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
