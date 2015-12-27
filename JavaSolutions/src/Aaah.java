import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Aaah {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String one = in.nextLine();
		String two = in.nextLine();
		out.printf("%s", one.length() >= two.length() ? "go" : "no");
	}
	
	public static void main(String[] args) {
		new Aaah().go();
	}
}
