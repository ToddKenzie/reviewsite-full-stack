package module7.reviewsitefullstack;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
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
	public void shouldAddOneTagToRepoViaModel() throws Exception {
		String tagName = "New tag name";
		underTest.addTag(tagName);

		ArgumentCaptor<Tag> tagArgument = ArgumentCaptor.forClass(Tag.class);
		verify(tagRepo).save(tagArgument.capture());
		assertEquals("New Tag Name", tagArgument.getValue().name);
	}


}
