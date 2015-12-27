import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;

import static java.lang.System.out;

public class Lexicography {

	BigInteger[] factorials = new BigInteger[51];
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.next();
			int n = str.length();
			if (str.equals("#") && n == 0) {
				break;
			}
			ArrayList<Character> nums = new ArrayList<>(n);
			BigInteger k = new BigInteger(in.next()).subtract(BigInteger.ONE);
			char[] ans = new char[n];
			for (char c : str.toCharArray()) {
				nums.add(c);
			}
			Collections.sort(nums);
			for (int i = 0; i < n; i++) {
				BigInteger[] next = k.divideAndRemainder(fact(n-1-i));
				try {
					ans[i] = nums.get(next[0].intValue());
				} catch (IndexOutOfBoundsException e) {
					out.println(Arrays.toString(ans).replaceAll("[\\[\\],]", "") + "    " + i);
					e.printStackTrace();
					System.exit(1);
				}
				nums.remove(next[0].intValue());
				k = next[1];
			}
			out.println(ans);
		}
	}
	
	public BigInteger fact(int n) {
		if (n <= 1) {
			return BigInteger.ONE;
		} else if (factorials[n] != null) {
			return factorials[n];
		}
		return new BigInteger(""+n).multiply(fact(n-1));
	}
	
	public static void main(String[] args) {
		new Lexicography().go();
	}
}
