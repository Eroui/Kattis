import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Apaxiaaans {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine().trim();
		StringBuilder sb = new StringBuilder();
		char prev = ' ';
		for (char c : line.toCharArray()) {
			if (prev != c) {
				sb.append(c);
				prev = c;
			}
		}
		out.println(sb);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Apaxiaaans().go();
	}
}
