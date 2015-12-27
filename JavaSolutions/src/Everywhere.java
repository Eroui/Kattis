import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Everywhere {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numCities = in.nextInt();
			in.nextLine();
			HashSet<String> cities = new HashSet<String>();
			for (int i = 0; i < numCities; i++) {
				cities.add(in.nextLine().trim());
			}
			out.println(cities.size());
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Everywhere().go();
	}
}
