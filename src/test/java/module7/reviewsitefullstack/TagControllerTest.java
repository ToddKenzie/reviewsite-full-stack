package module7.reviewsitefullstack;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class TagControllerTest {
	
	@InjectMocks
	private TagController underTest;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Tag tagA;
	
	@Mock
	private Tag tagB;
	
	@Mock
	private Model model;
	
	long arbitraryId = 1;
	
	@Mock
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview reviewA;
	
	@Mock
	private GameReview reviewB;
	
	@Mock
	private GameExpansionRepository gameExpansionRepo;
	
	@Mock
	private GameExpansion gameXp;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
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
		when(tagRepo.findAllByOrderByNameAsc()).thenReturn(allTags);
		
		underTest.findAllTags(model);
		verify(model).addAttribute("tags", allTags);
	}
	
	@Test
	public void shouldAddOneTagToModelByName() throws Exception {
		String tagName = "New tag name";
		underTest.addTag(tagName);

		ArgumentCaptor<Tag> tagArgument = ArgumentCaptor.forClass(Tag.class);
		verify(tagRepo).save(tagArgument.capture());
		assertEquals("New Tag Name", tagArgument.getValue().name);
	}
	
	@Test
	public void shouldDeleteTagFromModelByName() throws Exception {
		String tagName = "Tagname";
		
		when(tagRepo.findByNameIgnoreCase(tagName)).thenReturn(tagA);
		
		underTest.deleteTagByName(tagName);
		verify(tagRepo).delete(tagA);
	}
	
	@Test
	public void shouldDeleteTagFromGameReviewsWithIt() throws Exception {
		String tagName = "Tagname";
		Collection<Tag> allTags = new LinkedList<>(Arrays.asList(tagA, tagB));
		Collection<GameReview> reviewsWithTag = Arrays.asList(reviewA, reviewB);
		
		when(tagRepo.findByNameIgnoreCase(tagName)).thenReturn(tagA);
		when(gameReviewRepo.findByTagsContains(tagA)).thenReturn(reviewsWithTag);
		when(reviewA.getTags()).thenReturn(allTags);
		
		underTest.deleteTagByName(tagName);
		assertThat(allTags, containsInAnyOrder(tagB));
	}

	@Test
	public void shouldDeleteTagFromGameExpansionsWithIt() throws Exception {
		String tagName = "Tagname";
		Collection<Tag> allXpTags = new LinkedList<>(Arrays.asList(tagA, tagB));
		Collection<GameExpansion> xPWithTag = Arrays.asList(gameXp);
		
		when(tagRepo.findByNameIgnoreCase(tagName)).thenReturn(tagA);
		when(gameExpansionRepo.findByTagsContains(tagA)).thenReturn(xPWithTag);
		when(gameXp.getTags()).thenReturn(allXpTags);
		
		underTest.deleteTagByName(tagName);
		assertThat(allXpTags, containsInAnyOrder(tagB));
	}

}
