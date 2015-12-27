import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class LineUp {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		ArrayList<String> inc = new ArrayList<String>(num);
		ArrayList<String> dec = new ArrayList<String>(num);
		ArrayList<String> orig = new ArrayList<String>(num);
		for (int i = 0; i < num; i++) {
			inc.add(in.next());
			dec.add(inc.get(i));
			orig.add(inc.get(i));
		}
		Collections.sort(inc);
		Collections.sort(dec);
		Collections.reverse(dec);
		if (inc.equals(orig)) {
			out.println("INCREASING");
		} else if (dec.equals(orig)) {
			out.println("DECREASING");
		} else {
			out.println("NEITHER");
		}
		in.close();
	}
	
	public static void main(String[] args) {
		new LineUp().go();
	}
}
