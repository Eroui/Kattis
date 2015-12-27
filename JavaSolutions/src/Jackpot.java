import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Jackpot {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		for (int zzz = 0; zzz < zz; zzz++) {
			int numWheels = in.nextInt();
			if (numWheels == 0) {
				continue;
			}
			int[] wheels = new int[numWheels];
			for (int i = 0; i < numWheels; i++) {
				wheels[i] = in.nextInt();
			}
			long lcm = wheels[0];
			for (int i = 1; i < numWheels; i++) {
				lcm = lcm(wheels[i], lcm);
				if (lcm > 1000000000) {
					break;
				}
			}
			if (lcm > 1000000000) {
				out.println("More than a billion.");
			} else {
				out.println(lcm);
			}
		}
		in.close();
	}
	
	public long lcm(long one, long two) {
		if (Math.max(one, two) % Math.min(one, two) == 0) {
			return Math.max(one, two);
		}
		return Math.max(one, two) * Math.min(one, two) / gcd(one, two);
	}
	
	public long gcd(long one, long two) {
		if (two == 0) {
			return one;
		}
		return gcd(two, one%two);
	}
	
	public static void main(String[] args) {
		new Jackpot().go();
	}
}
