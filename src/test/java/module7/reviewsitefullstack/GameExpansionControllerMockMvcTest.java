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
@WebMvcTest(GameExpansionController.class)
public class GameExpansionControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private GameExpansionRepository gameExpansionRepo;
	
	@Mock
	private GameExpansion gameXp;
	
	@Mock
	private GameExpansion secondXp;
	
	@MockBean
	private GameRepository gameRepo;
	
	@Mock
	private Game game;
	
	@MockBean
	private TagRepository tagRepo;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkAndRouteToSingleExpansionSite() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp));
		when(gameRepo.findByGameExpansion(gameXp)).thenReturn(game);
		mvc.perform(get("/gameExpansion/1")).andExpect(status().isOk());
		mvc.perform(get("/gameExpansion/1")).andExpect(view().name(is("singleExpansionTemplate")));
	}
	
	@Test
	public void shouldNotBeOkForSingleExpansionSiteWithInvalidId() throws Exception {
		mvc.perform(get("/gameExpansion/0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldAddSingleExpansionIntoModel() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp));
		when(gameRepo.findByGameExpansion(gameXp)).thenReturn(game);
		mvc.perform(get("/gameExpansion/1")).andExpect(model().attribute("gameExpansion", gameXp));
		
	}
	
	@Test
	public void shouldBeOkAndRouteToAllExpansionsSite() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp, secondXp);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		mvc.perform(get("/gameExpansion/all")).andExpect(status().isOk());
		mvc.perform(get("/gameExpansion/all")).andExpect(view().name(is("allExpansionsTemplate")));
	}

	@Test
	public void shouldAddAllExpansionsIntoModel() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp, secondXp);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		mvc.perform(get("/gameExpansion/all")).andExpect(model().attribute("gameExpansions", allExpansions));
	}

}
