package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

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
@WebMvcTest(TagController.class)
public class TagControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Tag tag;
	
	@Mock
	private Tag secondTag;
	
	@MockBean
	private GameRepository gameRepo;
	
	@Mock
	private Game game;
	
	@Mock
	private Game secondGame;
	
	@MockBean
	private GameExpansionRepository gameExpansionRepo;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkAndRouteToSingleTagSite() throws Exception {
		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tags/1")).andExpect(status().isOk());
		mvc.perform(get("/tags/1")).andExpect(view().name(is("singleTagTemplate")));
	}
	
	@Test
	public void shouldNotBeOkForSingleTagSiteWithInvalidId() throws Exception {
		mvc.perform(get("/tags/0")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldAddSingleTagIntoModel() throws Exception {
		when(tagRepo.findById(arbitraryId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tags/1")).andExpect(model().attribute("tag", tag));
	}

	@Test
	public void shouldBeOkAndRouteToAllTagSite() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, secondTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		mvc.perform(get("/tags/all")).andExpect(status().isOk());
		mvc.perform(get("/tags/all")).andExpect(view().name(is("allTagsTemplate")));
	}
	
	@Test
	public void shouldAddAllTagsIntoModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, secondTag);
		when(tagRepo.findAllByOrderByNameAsc()).thenReturn(allTags);
		mvc.perform(get("/tags/all")).andExpect(model().attribute("tags", allTags));
	}
	
	@Test
	public void onAddShouldRouteToEditSite() throws Exception {
		mvc.perform(post("/tags/added").param("tagName", "Tagname"))
			.andExpect(redirectedUrl("/tags/edit"));
	}
	
	@Test
	public void onDeleteShouldRouteToEditSite() throws Exception {
		mvc.perform(post("/tags/delete").param("tagName", "Tagname"))
			.andExpect(redirectedUrl("/tags/edit"));
	}
	
	@Test
	public void shouldBeOkAndAllTagsAddedToModelForEditSite() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, secondTag);
		when(tagRepo.findAllByOrderByNameAsc()).thenReturn(allTags);
		mvc.perform(get("/tags/edit")).andExpect(status().isOk());
		mvc.perform(get("/tags/edit")).andExpect(model().attribute("tags", allTags));
	}
	

}
