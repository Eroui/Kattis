import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Eligibility {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			String name = in.next();
			int yearStart = Integer.parseInt(in.next().substring(0, 4));
			int yearBorn = Integer.parseInt(in.next().substring(0,4));
			int numCourses = in.nextInt();
			if (yearStart >= 2010 || yearBorn >= 1991) {
				out.printf("%s eligible%n", name);
			} else if (numCourses > 40) {
				out.printf("%s ineligible%n", name);
			} else {
				out.printf("%s coach petitions%n", name);
			}
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Eligibility().go();
	}
}
