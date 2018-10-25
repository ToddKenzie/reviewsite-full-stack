package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringFormatterTest {
	
	@Test
	public void convertluisToLuis() {
		String underTest = "luis";
		String result = StringFormatter.convertToPascalCaseWithSpace(underTest);
		assertThat(result, is("Luis"));
	}
	
	@Test
	public void convertLUIssToLuiss() {
		String underTest = "LUIss";
		String result = StringFormatter.convertToPascalCaseWithSpace(underTest);
		assertThat(result, is("Luiss"));
	}
	
	@Test
	public void convertluis_luisToLuis_Luis() {
		String underTest = "luis luis";
		String result = StringFormatter.convertToPascalCaseWithSpace(underTest);
		assertThat(result, is("Luis Luis"));
	}

	@Test
	public void convertbiLLy_bo_BOBToBilly_Bo_Bob() {
		String underTest = "biLLy bo BOB";
		String result = StringFormatter.convertToPascalCaseWithSpace(underTest);
		assertThat(result, is("Billy Bo Bob"));
	}

}
