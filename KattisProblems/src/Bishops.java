import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Bishops {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			out.println(n+Math.max(0, n-2));
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Bishops().go();
	}
}
