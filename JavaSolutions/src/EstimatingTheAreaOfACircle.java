import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class EstimatingTheAreaOfACircle {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextDouble()) {
			double r = in.nextDouble();
			int n = in.nextInt();
			int c = in.nextInt();
			if (r == 0 && n == 0 && c == 0) {
				break;
			}
			out.println(Math.PI*Math.pow(r, 2) + " " + Math.pow(r*2,2)*c/n);
		}
		
		in.close();
	}
	
	public static void main(String[] args) {
		new EstimatingTheAreaOfACircle().go();
	}
}
