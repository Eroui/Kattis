import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class HayPoints {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numWords = in.nextInt();
		int numSentences = in.nextInt();
		HashMap<String, Integer> pointVals = new HashMap<>();
		for (int i = 0; i < numWords; i++) {
			pointVals.put(in.next(), in.nextInt());
		}
		for (int s = 0; s < numSentences; s++) {
			int sum = 0;
			while (in.hasNext()) {
				String next = in.next();
				if (next.equals(".")) {
					break;
				}
				if (pointVals.get(next) != null) {
					sum += pointVals.get(next);
				}
			}
			out.println(sum);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new HayPoints().go();
	}
}
