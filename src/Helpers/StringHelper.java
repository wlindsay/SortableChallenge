package Helpers;

public class StringHelper {
	
	/**
	 * Will remove caps, special characters, and spaces from the input string
	 * 
	 * @param s - input string
	 * @return cleaned string
	 */
	static public String cleanString(String s) {
		return s.replaceAll("[^a-z0-9]", "");
	}
}
