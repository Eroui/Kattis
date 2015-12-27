import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class BlackFriday {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : nums) {
			if (map.get(i) == null) {
				map.put(i, 0);
			}
			map.put(i, map.get(i)+1);
		}
		int[] ar = new int[6];
		for (int i : map.keySet()) {
			if (map.get(i) == 1) {
				ar[i-1] = i;
			}
		}
		int max = 0;
		for (int i : ar) {
			if (i > max) {
				max = i;
			}
		}
		int index = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == max) {
				index = i;
				break;
			}
		}
		if (max == 0) {
			out.println("none");
		} else {
			out.println(index+1);
		}
	}
	
	public static void main(String[] args) {
		new BlackFriday().go();
	}
}
