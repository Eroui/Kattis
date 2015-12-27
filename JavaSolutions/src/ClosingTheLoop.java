import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class ClosingTheLoop {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numSegments = in.nextInt();
			ArrayList<Integer> reds = new ArrayList<Integer>(numSegments);
			ArrayList<Integer> blues = new ArrayList<Integer>(numSegments);
			for (int i = 0; i < numSegments; i++) {
				String next = in.next();
				char c = next.charAt(next.length()-1);
				int n = Integer.parseInt(next.substring(0, next.length()-1));
				if (c == 'R') {
					reds.add(n);
				} else {
					blues.add(n);
				}
			}
			Collections.sort(reds, new Comparator<Integer>() {
				public int compare(Integer one, Integer two) {
					return two - one;
				}
			});;
			Collections.sort(blues, new Comparator<Integer>() {
				public int compare(Integer one, Integer two) {
					return two - one;
				}
			});
			int max = Math.min(reds.size(), blues.size());
			int best = 0;
			for (int i = 0; i < max; i++) {
				best += reds.get(i) + blues.get(i);
			}
			out.printf("Case #%d: %d%n", zzz+1, best-max-max);
		}
	}
	
	public static void main(String[] args) {
		new ClosingTheLoop().go();
	}
}
