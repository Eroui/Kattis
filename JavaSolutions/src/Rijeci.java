import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Rijeci {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int numA = 1;
		int numB = 0;
		for (int i = 0; i < k; i++) {
			int newA = numB;
			int newB = numA + numB;
			numA = newA;
			numB = newB;
		}
		out.printf("%d %d", numA, numB);
	}
	
	public static void main(String[] args) {
		new Rijeci().go();
	}
}
