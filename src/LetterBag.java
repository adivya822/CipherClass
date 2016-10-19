public class LetterBag {
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static int[] letterFrequencies;

	public LetterBag() {
		letterFrequencies = new int[26];
	}

	/***
	 * checks whole array for total number of letters
	 * 
	 * @return total number of letters
	 */
	public static int getTotalWords() {
		int letters = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			letters += letterFrequencies[i];
		}
		return letters;
	}

	/***
	 * finds number of unique letters
	 * 
	 * @return returns the number of unique letters
	 */
	public static int getNumUniqueLetters() {
		int uniquel = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > 0)
				uniquel++;
		}
		return uniquel;
	}

	/***
	 * 
	 * @param letter
	 *            to check for number of times the letter occurred
	 * @return number of times the letter repeated
	 */
	public static int getNumOccurances(String letter) {
		int index = getIndexForLetter(letter);
		return letterFrequencies[index];
	}

	/***
	 * checks for most frequent letter in the array
	 * 
	 * @return most frequent letter
	 */
	public String getMostFrequent(String text) {
		int mostFreq = 0;
		int index = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > mostFreq) {
				mostFreq = letterFrequencies[i];
				index = i;
			}
		}
		String letter = alphabet.substring(index, index + 1);
		return letter;
	}

	/***
	 * adds a letter to the bag
	 * 
	 * @param letter
	 *            the letter to be added
	 */
	public void add(String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}

	public static int getIndexForLetter(String lower) {
		return alphabet.indexOf(lower);
	}
}
