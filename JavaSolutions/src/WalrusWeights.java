import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class WalrusWeights {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int numWeights = in.nextInt();
		int[] weights = new int[numWeights];
		boolean[] dp = new boolean[2001];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = in.nextInt();
		}
		dp[0] = true;
		for (int i = 0; i < weights.length; i++) {
			for (int e = 2000; e >= 0; e--) {
				if (e-weights[i] >= 0) {
					dp[e] |= dp[e-weights[i]];
				}
			}
		}
		int change = 1;
		for (int i = 1000; i < dp.length && i >= 0;) {
			if (dp[i]) {
				out.println(i);
				break;
			}
			i += change;
			if (change > 0) {
				change++;
			} else {
				change--;
			}
			change = -change;
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new WalrusWeights().go();
	}
}
