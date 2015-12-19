import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Cold {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		int numBig = 0;
		while (in.hasNextInt()) {
			if (in.nextInt() < 0) {
				numBig++;
			}
		}
		out.println(numBig);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Cold().go();
	}
}
