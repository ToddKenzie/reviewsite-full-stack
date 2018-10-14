package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
public class GameCategoryRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private GameCategoryRepository gameCategoryRepo;
	
	@Test
	public void shouldGenerateGameCategoryID() {
		GameCategory category1 = gameCategoryRepo.save(new GameCategory("category 1"));
		Long catID = category1.getId();
		
		entity.flush();
		entity.clear();
		
		assertThat(catID, notNullValue());
	}
	
	@Test
	public void shouldSaveAndLoadGameCategory() {
		GameCategory category1 = gameCategoryRepo.save(new GameCategory("category 1"));
		Long catID = category1.getId();
		
		entity.flush();
		entity.clear();

		Optional<GameCategory> underTest = gameCategoryRepo.findById(catID);
		GameCategory testCategory = underTest.get();
		assertThat(testCategory, is(category1));
	}

}
