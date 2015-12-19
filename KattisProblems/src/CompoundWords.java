import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class CompoundWords {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		ArrayList<String> words = new ArrayList<String>();
		while (in.hasNext()) {
			words.add(in.next());
		}
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < words.size(); i++) {
			for (int e = 0; e < words.size(); e++) {
				if (i != e) {
					set.add(words.get(i)+words.get(e));
				}
			}
		}
		out.println(set.toString().replaceAll("[\\[\\] ]", "").replaceAll(",","\n"));
		in.close();
	}
	
	public static void main(String[] args) {
		new CompoundWords().go();
	}
}
