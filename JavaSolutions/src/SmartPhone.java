import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class SmartPhone {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String target = in.next();
			String curr = in.next();
			String sug1 = in.next();
			String sug2 = in.next();
			String sug3 = in.next();
			out.println(Math.min(Math.min(editDistance(curr, target), editDistance(sug1, target)+1),
					Math.min(editDistance(sug2, target)+1, editDistance(sug3, target)+1)));
		}
		
		in.close();
	}
	
	public int editDistance(String one, String two) {
		int i = 0;
		for (; i < one.length() && i < two.length(); i++) {
			if (one.charAt(i) != two.charAt(i)) {
				break;
			}
		}
		return one.length()-i + two.length()-i;
	}
	
	public static void main(String[] args) {
		new SmartPhone().go();
	}
}
