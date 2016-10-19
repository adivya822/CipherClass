
public class Tester {

	public static void main(String[] args) {
		String plainText = "Nima's pants are on fire!!!?!!";
		String cipherText = Cipher.rotationCipherEncrypt(plainText,3);
	
		System.out.println("Plaintext: " + plainText);
		System.out.println("Cipertext: " + cipherText);
		Dictionary d = Dictionary.buildDictionary("E:\\Arunima_Divya_Backup\\adivya822\\CipherBlankTemplate\\CipherBlankTemplate\\words.txt");
	System.out.println(d.isWord("hi"));
	}

}