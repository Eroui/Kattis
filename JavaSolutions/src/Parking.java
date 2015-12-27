import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Parking {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int[] ar = new int[101];
		for (int e = 0; e < 3; e++) {
			int start = in.nextInt();
			int end = in.nextInt();
			for (int i = start; i < end; i++) {
				ar[i]++;
			}
		}
		int total = 0;
		for (int i = 0; i < ar.length; i++) {
			switch (ar[i]) {
			case 1:
				total += a;
				break;
			case 2:
				total += b*2;
				break;
			case 3:
				total += c*3;
				break;
			}
		}
		out.println(total);
		
		in.close();
	}
	
	public static void main(String[] args) {
		new Parking().go();
	}
}
