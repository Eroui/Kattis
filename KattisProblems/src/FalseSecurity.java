import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class FalseSecurity {
	
	HashMap<String, Character> morseToLetter = new HashMap<>();
	HashMap<Character, String> letterToMorse = new HashMap<>();
	
	public void go() {
		String[] morseArray = {".-","....","---","...-","-...","..",".--.",".--","-.-.",".---","--.-","-..-","-..","-.-",".-.","-.--",".",".-..","...","--..","..-.","--","-","--.","-.","..-","..--",".-.-","---.","----"};
		char[] letterArray = "AHOVBIPWCJQXDKRYELSZFMTGNU_,.?".toCharArray();
		for (int i = 0; i < morseArray.length; i++) {
			morseToLetter.put(morseArray[i], letterArray[i]);
			letterToMorse.put(letterArray[i], morseArray[i]);
		}
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			int[] lens = new int[line.length()];
			StringBuilder morse = new StringBuilder();
			for (int i = 0; i < line.length(); i++) {
				lens[i] = letterToMorse.get(line.charAt(i)).length();
				morse.append(letterToMorse.get(line.charAt(i)));
			}
			StringBuilder output = new StringBuilder();
			for (int i = lens.length-1; i >= 0; i--) {
				output.append(morseToLetter.get(morse.toString().substring(0, lens[i])));
				morse.delete(0, lens[i]);
			}
			out.println(output);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new FalseSecurity().go();
	}
}
