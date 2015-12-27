import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Conundrum {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int num = 0;
		for (int i = 0; i < line.length(); i+=3) {
			if (line.charAt(i) != 'P') {
				num++;
			}
		}
		for (int i = 1; i < line.length(); i+=3) {
			if (line.charAt(i) != 'E') {
				num++;
			}
		}
		for (int i = 2; i < line.length(); i+=3) {
			if (line.charAt(i) != 'R') {
				num++;
			}
		}
		out.println(num);
	}
	
	public static void main(String[] args) {
		new Conundrum().go();
	}
}
