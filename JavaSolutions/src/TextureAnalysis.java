import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class TextureAnalysis {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int testCase = 1;
		while (in.hasNextLine()) {
			String next = in.nextLine();
			if (next.equals("END")) {
				break;
			}
			boolean even = true;
			int distance = next.replaceFirst("\\*", "").replaceAll("\\*.*", "").length();
			int currCount = 0;
			for (int i = 1; i < next.length(); i++) {
				if (next.charAt(i) == '.') {
					currCount++;
				} else if (currCount != distance) {
					even = false;
					break;
				} else {
					currCount = 0;
				}
			}
			if (even) {
				out.printf("%d EVEN%n", testCase++);
			} else {
				out.printf("%d NOT EVEN%n", testCase++);
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new TextureAnalysis().go();
	}
}
