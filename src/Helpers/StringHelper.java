package Helpers;

public class StringHelper {
	
	/**
	 * Will remove caps, special characters, and spaces from the input string
	 * 
	 * @param s - input string
	 * @return cleaned string
	 */
	static public String cleanString(String s) {
		return s.toLowerCase().replaceAll("[^a-z0-9]", "");
	}
	
	static public String cleanStringwithSpace(String s) {
		return s.toLowerCase().replaceAll("[^a-z0-9]", " ");
	}
	
	/**
	 * Will return true if the two strings are identical or if the LevenshteinDistance is equal to the character difference between the two
	 * since this indicates that one simply has added characters over the other
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	static public boolean areStringsSimilar(String s1, String s2) {
		return contains(s1, s2) || contains(s2, s1);
	}
	
	/**
	 * Will see if the child string is within the parent strings and return true if that is the case. Both strings will also be cleaned first.
	 * 
	 * @param parent - string which should potentially contain the chidl
	 * @param chid - string that is checked to see if it is within the parent
	 * @return boolean
	 */
	static public boolean contains(String parent, String child) {
		String cp = cleanString(parent);
		String cc = cleanString(child);
		
		if (cp.length() == 0 || cc.length() == 0) {
			return false;
		} else if (cp.contains(cc)) {
			return true;
		}
		
		return areStringsPartiallySimilar(parent, child);
	}
	
	/**
	 * Will return true if at least 70% of the child is present in the parent with a minimum of 3 characters
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	static private boolean areStringsPartiallySimilar(String parent, String child) {
		String[] ac = cleanStringwithSpace(child).split("\\s+");
		int matchedCharacters = 0;
		
		for (int i = 1; i < ac.length; i++) {
			int curlength = 0;
			String curString = "";
			
			for (int j = i; j < ac.length; j++) {
				curlength += ac[j].length();
				curString += ac[j];
			}
			
			if (contains(parent, curString)) {
				matchedCharacters = curlength;
				break;
			}
		}
		
		return matchedCharacters > 3;
	}
}
