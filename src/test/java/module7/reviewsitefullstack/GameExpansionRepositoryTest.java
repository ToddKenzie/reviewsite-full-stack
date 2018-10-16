package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GameExpansionRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private GameExpansionRepository gameExpansionRepo;
	
	//String name, String rangeOfPlayers, String timeToComplete, String synopsis, String weblink, String pictureLink
	@Test
	public void shouldCreateOwnId() {
		GameExpansion gameXp = new GameExpansion("xp 1", "", "", "", "", "", null);
		gameExpansionRepo.save(gameXp);
		Long underTest = gameXp.getId();
		
		entity.flush();
		entity.clear();
		
		assertThat(underTest, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadGameExpansion() {
		GameExpansion gameXp = new GameExpansion("xp 1", "", "", "", "", "", null);
		gameExpansionRepo.save(gameXp);
		Long gameXpID = gameXp.getId();
		
		entity.flush();
		entity.clear();
		
		Optional<GameExpansion> underTest = gameExpansionRepo.findById(gameXpID);
		GameExpansion testXp = underTest.get();
		assertThat(testXp, is(gameXp));
	}

}
