import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Heliocentric {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int currCase = 1;
		while (in.hasNextInt()) {
			int earth = in.nextInt();
			int mars = in.nextInt();
			int i = 0;
			for (; earth != 0 || mars != 0; i++) {
				earth = (earth+1)%365;
				mars = (mars+1)%687;
			}
			out.printf("Case %d: %d%n", currCase++, i);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Heliocentric().go();
	}
}
