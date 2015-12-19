import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class ACM {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		HashMap<Character, Integer> wrongs = new HashMap<>();
		HashSet<Character> correct = new HashSet<>();
		int total = 0;
		while (in.hasNextInt()) {
			int minutes = in.nextInt();
			if (minutes == -1) {
				break;
			}
			char problem = in.next().charAt(0);
			String result = in.next();
			if (result.equals("right")) {
				correct.add(problem);
				total += minutes;
			} else {
				if (wrongs.get(problem) == null) {
					wrongs.put(problem, 0);
				}
				wrongs.put(problem, wrongs.get(problem)+1);
			}
		}
		for (char c : correct) {
			if (wrongs.get(c) != null) {
				total += wrongs.get(c)*20;
			}
		}
		out.printf("%d %d", correct.size(), total);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new ACM().go();
	}
}
