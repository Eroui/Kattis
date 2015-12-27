import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class NameThatPermutation {
	
	BigInteger[] factorials = new BigInteger[51];
	
	public void go() {
		for (int i = 0; i < factorials.length; i++) {
			factorials[i] = fact(i);
		}
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			BigInteger k = new BigInteger(in.next());
			ArrayList<Integer> nums = new ArrayList<Integer>(n);
			int[] ans = new int[n];
			for (int i = 0; i < n; i++) {
				nums.add(i);
			}
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
			for (int i = 0; i < n; i++) {
				ans[i]++;
			}
			out.println(Arrays.toString(ans).replaceAll("[\\[\\],]", ""));
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
		new NameThatPermutation().go();
	}
}
