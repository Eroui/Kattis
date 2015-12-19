import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class IncreasingSubsequence {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numNums = in.nextInt();
			if (numNums == 0) {
				break;
			}
			int[] nums = new int[numNums];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = in.nextInt();
			}
			int[] dp = new int[numNums];
			Node[] list = new Node[numNums];
			int bestIndex = 0;
			int bestValue = 0;
			for (int i = 0; i < nums.length; i++) {
				int max = 0;
				int maxIndex = i;
				Node curr = new Node(nums[i]);
				for (int e = i-1; e >= 0; e--) {
					if (nums[i] > nums[e] && dp[e] > max) {
						max = Math.max(dp[e], max);
						maxIndex = e;
					}
				}
				curr.next = list[maxIndex];
				list[i] = curr;
				dp[i] = max+1;
				if (dp[i] >= bestValue) {
					bestValue = dp[i];
					bestIndex = i;
				}
			}
			out.printf("%d %s%n", bestValue, list[bestIndex].toString().trim());
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new IncreasingSubsequence().go();
	}
	
	private class Node {
		int val;
		Node next;
		
		public Node(int n) {
			val = n;
		}
		
		public String toString() {
			if (next == null) {
				return val+"";
			}
			return next.toString() + " " + val;
		}
	}
}
