import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Kemija08 {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			sb.append(line.charAt(i));
			if ("aeiou".indexOf(line.charAt(i)) != -1) {
				i+=2;
			}
		}
		out.println(sb);
	}
	
	public static void main(String[] args) {
		new Kemija08().go();
	}
}
