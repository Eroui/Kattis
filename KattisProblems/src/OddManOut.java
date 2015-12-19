import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class OddManOut {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numPeople = in.nextInt();
			HashSet<Integer> set = new HashSet<>();
			for (int i = 0; i < numPeople; i++) {
				int next = in.nextInt();
				if (set.contains(next)) {
					set.remove(next);
				} else {
					set.add(next);
				}
			}
			out.printf("Case #%d: %s%n", zzz+1, set.iterator().next());
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new OddManOut().go();
	}
}
