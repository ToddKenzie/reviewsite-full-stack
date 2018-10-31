package module7.reviewsitefullstack;

public class StringFormatter {
	
	public static String convertToPascalCaseWithSpace(String input) {
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

}
