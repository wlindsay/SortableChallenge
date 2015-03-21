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
	
	/**
	 * Will return true if the two strings are identical or if the LevenshteinDistance is equal to the character difference between the two
	 * since this indicates that one simply has added characters over the other
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	static public boolean areStringsSimilar(String s1, String s2) {
		s1 = cleanString(s1);
		s2 = cleanString(s2);
		
		return s1.compareTo(s2) == 0 || s1.contains(s2) || s2.contains(s1);
	}
	
	/**
	 * Will see if the child string is within the parent strings and return true if that is the case. Both strings will also be cleaned first.
	 * 
	 * @param parent - string which should potentially contain the chidl
	 * @param chid - string that is checked to see if it is within the parent
	 * @return boolean
	 */
	static public boolean contains(String parent, String child) {
		parent = cleanString(parent);
		child = cleanString(child);
		
		return parent.contains(child);
	}
}
