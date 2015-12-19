import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Easiest {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			int firstSum = digitSum(n);
			int p = 11;
			while (digitSum(n*p) != firstSum) {
				p++;
			}
			out.println(p);
		}
	}
	
	public int digitSum(int i) {
		int sum = 0;
		while (i > 0) {
			sum += i % 10;
			i /= 10;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		new Easiest().go();
	}
}
