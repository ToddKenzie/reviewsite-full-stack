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
public class TagRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private TagRepository tagRepo;
	
	@Test
	public void shouldGenerateTagID() {
		Tag tag1 = tagRepo.save(new Tag("type"));
		Long tagID = tag1.getId();
		
		entity.flush();
		entity.clear();
		
		assertThat(tagID, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag1 = tagRepo.save(new Tag("type"));
		Long tagID = tag1.getId();
		
		entity.flush();
		entity.clear();
		
		Optional<Tag> underTest = tagRepo.findById(tagID);
		Tag testTag = underTest.get();
		assertThat(testTag, is(tag1));
	}

}
