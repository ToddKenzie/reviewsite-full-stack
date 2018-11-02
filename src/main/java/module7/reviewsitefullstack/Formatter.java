package module7.reviewsitefullstack;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Formatter {
	
	private static DateTimeFormatter formatter =
		    DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
		                     .withLocale( Locale.US )
		                     .withZone( ZoneId.systemDefault() );
	
	public static String convertStringToTitleCase(String input) {
		String result = "";
		String[] words = input.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			result = result.concat(words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase());
			if (i < words.length - 1) {
				result = result.concat(" ");
			}
		}
		return result;
	}

	public static String convertInstantToString(Instant timeStamp) {
		return formatter.format(timeStamp);
	}

}
