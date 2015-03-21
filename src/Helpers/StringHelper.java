package Helpers;

public class StringHelper {
	
	/**
	 * Will remove any character but alphanumerics and replace them with a space. Will also make it all lower case.
	 * 
	 * @param s - input string
	 * @return cleaned string
	 */
	static public String cleanString(String s) {
		return s.toLowerCase().replaceAll("[^a-z0-9]", " ");
	}
	
	/**
	 * Will check if either s1 is contained within s2 or the opposite
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
		
		if (cp.isEmpty() || cc.isEmpty()) {
			return false;
		} else if (cp.contains(cc)) {
			String[] split = cp.split(cc);
			
			// In all cases seen, following the product model with another number usually indicates a different product
			if (split.length <= 1 || !Character.isDigit(split[split.length-1].charAt(0))) {
				return true;
			}
			
			return false;
		}
		
		// Will also check if a subset of the product model is present
		return areStringsPartiallySimilar(parent, child);
	}
	
	/**
	 * Will check if a subset of the child string is present within the parent string. That subset is defined
	 * as iterating over the child string while removing parts of the string from the start of the string. This
	 * is done since it was found that those were the only ones that were sometimes omitted.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	static private boolean areStringsPartiallySimilar(String parent, String child) {
		String[] ac = cleanString(child).split("\\s+");
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
