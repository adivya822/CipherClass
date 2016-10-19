import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	private static Dictionary dictionary = Dictionary.buildDictionary("words.txt");

	public static ArrayList<String> getWords(String text) {
		ArrayList<String> words = new ArrayList<String>();
		String word = "";
		for (int i = 0; i < text.length(); i++) {
			String letter = text.substring(i, i + 1);
			if (!letter.equals(" ")) {
				word += letter;
			} else {
				words.add(word);
				word = "";
			}
		}
		return words;
	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String encrypt = "";
		for (int i = 0; i < plain.length(); i++) {
			String newl = "";
			newl = alphabet.substring(i + shift, (i + 1) + shift);
			encrypt += newl;
		}
		return encrypt;
	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String plainText = "";
		for (int i = 0; i < cipher.length(); i++) {
			String newl = "";
			newl = alphabet.substring(i - shift, (i + 1) + shift);
			plainText += newl;
		}

		return plainText;
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {
		String cipher = "";
		for (int i = 0; i < plain.length(); i++) {
			String l = plain.substring(i, i + 1);
			int index = alphabet.indexOf(l);
			int c = i % password.length();
			String q = password.substring(c, c + 1);
			int index2 = alphabet.indexOf(q);
			int TotalIndex = index + index2;
			int y = TotalIndex % alphabet.length();
			String newletter = alphabet.substring(y, y + 1);
			cipher += newletter;
		}
		return cipher;
	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		String plain = "";
		for (int i = 0; i < cipher.length(); i++) {
			// Receive the index value in plainText
			String l = cipher.substring(i, i + 1);
			int index = alphabet.indexOf(l);
			// Receive code index
			int c = i % password.length();
			String q = password.substring(c, c + 1);
			int index2 = alphabet.indexOf(q);
			int TotalIndex = index - index2;
			int y = TotalIndex % alphabet.length();
			String newletter = alphabet.substring(y, y + 1);
			plain += newletter;
		}
		return plain;
		
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plaintext) {
		ArrayList<String> words = getWords(plaintext);
		int count = 0;
		for (int i = 0; i < words.size(); i++) {
			if (dictionary.isWord(words.get(i)))
				count++;
		}
		if (count == words.size())
			return true;
		return false;
	}

	/***
	 * 
	 * @param plaintext
	 *            the phrase to change
	 * @param permutation
	 *            the description of how the letters are to be replaced
	 * @param alphabet	
	 *            the order of letters to use to cipher
	 * @return new encrypted text
	 */
	public String substitutionCipher(String plaintext, int[] permutation, String alphabet) {
		String cipher = "";
		for (int i = 0; i < permutation.length; i++) {
			String newl = "";
			newl = alphabet.substring(i + permutation[i], (i + 1) + permutation[i]);
			cipher += newl;
		}
		return cipher;
	}

	/***
	 * @param permutation
	 *            Array with values
	 * @return whether or not the permutation is valid(contains no multiples of
	 *         any values)
	 */
	public static boolean isValidPermutation(int[] permutation) {
		for (int i = 0; i < permutation.length; i++) {
			for (int j = 0; j < permutation.length; j++) {
				if (i != j) {
					if (permutation[i] == permutation[j])
						return false;
				}
			}
		}
		return true;
	}

	/***
	 * Assigns the array random values
	 * 
	 * @param length
	 *            the length of array that's going to be made
	 * @return permutation array with random values with specific length
	 */
	public static int[] randomPermutation(int length) {
		int[] permutation = new int[length];
		for (int i = 0; i < length; i++) {
			permutation[i] = -1;
		}
		int pos = -1;
		for (int i = 0; i < length; i++) {
			boolean retry = true;
			while (retry) {
				pos = (int) (Math.random() * length);
				if (permutation[pos] == -1) {
					permutation[pos] = i;
					retry = false;
				}
			}
		}
		return permutation;
	}

	public static String getLettersOut(String encrypted, int index, int length) {
		String temp = "";
		for (int i = index; i < encrypted.length(); i = i + length) {
			temp = temp + encrypted.substring(i, i + 1);
		}
		return temp;
	}

	public static String findCodeLetter(String temp, String alphabet) {
		for (int i = 0; i < alphabet.length(); i++) {
			String decoded = rotationCipherDecrypt(temp, i, alphabet);
			LetterBag bag = new LetterBag();
			for (int index = 0; index < decoded.length(); index++) {
				bag.add(decoded.substring(index, index + 1));
			}
			if (getMostFrequent(bag).equals(" "))
				return alphabet.substring(i, i + 1);
		}
		return null;
	}

	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
		int passSize = 3;
		String password = "";
		for (int i = 0; i < passSize; i++) {
			String group = getLettersOut(cipher, i, passSize);
			password += findCodeLetter(group, alphabet);
		}
		return password;
	}

	public static String vigenereCipherCrack(String cipher, int passwordLength, String alphabet) {
		String password = "";
		for (int i = 0; i < passwordLength; i++) {
			String group = getLettersOut(cipher, i, passwordLength);
			password += findCodeLetter(group, alphabet);
		}
		return password;
	}
	public static String rotationCipherCrack(String cipher, String alphabet){
		String crack = "";
		for(int i = 0; i<alphabet.length();i++){
			String plain = rotationCipherDecrypt(cipher, i, alphabet);
			if(plain.getMostFrequent(plain).equals(" ") || plain.getMostFrequent().equals("t")||plain.getMostFrequent().equals("e"))return plain;
		}
		return null;
	}

}