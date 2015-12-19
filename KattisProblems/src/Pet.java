import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Pet {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int bestIndex = 0;
		int max = 0;
		for (int i = 0; i < 5; i++) {
			int curr = in.nextInt() + in.nextInt() + in.nextInt() + in.nextInt();
			if (curr > max) {
				bestIndex = i+1;
				max = curr;
			}
		}
		out.printf("%d %d", bestIndex, max);
	}
	
	public static void main(String[] args) {
		new Pet().go();
	}
}
