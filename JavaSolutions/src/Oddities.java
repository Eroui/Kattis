import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Oddities {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int next = in.nextInt();
			if (next%2 == 0) {
				out.printf("%d is even%n", next);
			} else {
				out.printf("%d is odd%n", next);
			}
		}
	}
	
	public static void main(String[] args) {
		new Oddities().go();
	}
}
