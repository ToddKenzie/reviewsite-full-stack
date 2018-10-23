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

public class GameExpansionControllerTest {
	
	@InjectMocks
	private GameExpansionController underTest;
	
	@Mock
	private GameExpansionRepository gameExpansionRepo;
	
	@Mock
	private GameExpansion gameXp1;
	
	@Mock
	private GameExpansion gameXp2;

	@Mock
	private GameReviewRepository gameReviewRepo;
	
	@Mock
	private GameReview reviewA;
	
	@Mock
	private GameReview reviewB;
		
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Tag tagA;
	
	@Mock
	private Tag tagB;
	
	
	@Mock
	private Model model;
	
	long arbitraryId = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void addSingleExpansionToModel() throws Exception {
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp1));
		
		underTest.findOneExpansion(arbitraryId, model);
		verify(model).addAttribute("gameExpansion", gameXp1);
	}
	
	@Test
	public void findAllTagsForSingleExpansionToModel() throws Exception {
		Collection<Tag> allTagsForXp = Arrays.asList(tagA, tagB);
		when(gameExpansionRepo.findById(arbitraryId)).thenReturn(Optional.of(gameXp1));
		when(tagRepo.findByGameExpansionsContains(gameXp1)).thenReturn(allTagsForXp);
		
		underTest.findOneExpansion(arbitraryId, model);
		verify(model).addAttribute("tags", allTagsForXp);
	}
	
	@Test
	public void addAllExpansionsToModel() throws Exception {
		Collection<GameExpansion> allExpansions = Arrays.asList(gameXp1, gameXp2);
		when(gameExpansionRepo.findAll()).thenReturn(allExpansions);
		
		underTest.findAllExpansions(model);
		verify(model).addAttribute("gameExpansions", allExpansions);
	}

}
