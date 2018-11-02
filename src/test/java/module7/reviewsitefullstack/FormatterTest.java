package module7.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.Instant;

import org.junit.Test;

public class FormatterTest {
	
	@Test
	public void convertluisToLuis() {
		String underTest = "luis";
		String result = Formatter.convertStringToTitleCase(underTest);
		assertThat(result, is("Luis"));
	}
	
	@Test
	public void convertLUIssToLuiss() {
		String underTest = "LUIss";
		String result = Formatter.convertStringToTitleCase(underTest);
		assertThat(result, is("Luiss"));
	}
	
	@Test
	public void convertluis_luisToLuis_Luis() {
		String underTest = "luis luis";
		String result = Formatter.convertStringToTitleCase(underTest);
		assertThat(result, is("Luis Luis"));
	}

	@Test
	public void convertbiLLy_bo_BOBToBilly_Bo_Bob() {
		String underTest = "biLLy bo BOB";
		String result = Formatter.convertStringToTitleCase(underTest);
		assertThat(result, is("Billy Bo Bob"));
	}
	
	@Test
	public void convertCharSequenceToTime() {
		String underTest = "2017-10-05T02:34:56.050Z";
		Instant timeStamp = Instant.parse(underTest);
		String formattedTime = Formatter.convertInstantToString(timeStamp);
		assertThat(formattedTime, is("10/4/17 10:34 PM"));
	}

}
