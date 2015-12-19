import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class HappyPrime {
	
	public void go() {
		long time = System.nanoTime();
		boolean[] happyPrimes = new boolean[10001];
		for (int i = 1; i <= 10000; i++) {
			happyPrimes[i] =  isPrime(i) && isHappy(i);
		}
		Scanner in = new Scanner(System.in);
		int zz = in.nextInt();
		in.nextLine();
		StringBuilder sb = new StringBuilder();
		for (int zzz = 0; zzz < zz; zzz++) {
			int dataSet = in.nextInt();
			int val = in.nextInt();
			if (happyPrimes[val]) {
				sb.append(String.format("%d %d YES%n", dataSet, val));
			} else {
				sb.append(String.format("%d %d NO%n", dataSet, val));
			}
		}
		out.print(sb);
		
		in.close();
	}
	
	public boolean isPrime(int val) {
		if (val == 1) {
			return false;
		}
		for (int i = 2; i*i <= val; i++) {
			if (val % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isHappy(int val) {
		HashSet<Integer> used = new HashSet<Integer>();
		while (val != 1) {
			if (used.contains(val)) {
				return false;
			}
			used.add(val);
			char[] digits = Integer.toString(val).toCharArray();
			val = 0;
			for (char c : digits) {
				val += (c-'0')*(c-'0');
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		new HappyPrime().go();
	}
}
