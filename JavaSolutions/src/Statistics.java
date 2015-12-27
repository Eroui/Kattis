import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Statistics {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int currCase = 1;
		while (in.hasNextInt()) {
			int numNums = in.nextInt();
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < numNums; i++) {
				int num = in.nextInt();
				if (num < min) {
					min = num;
				}
				if (num > max) {
					max = num;
				}
			}
			out.printf("Case %d: %d %d %d%n", currCase++, min, max, max-min);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Statistics().go();
	}
}
