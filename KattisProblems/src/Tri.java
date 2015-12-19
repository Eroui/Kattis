import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Tri {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
		if (a+b == c) {
			out.printf("%d+%d=%d%n", a, b, c);
		} else 
		if (a-b == c) {
			out.printf("%d-%d=%d%n", a, b, c);
		} else 
		if (a*b == c) {
			out.printf("%d*%d=%d%n", a, b, c);
		} else 
		if (a/b == c) {
			out.printf("%d/%d=%d%n", a, b, c);
		} else 
		if (a == b+c) {
			out.printf("%d=%d+%d%n", a, b, c);
		} else 
		if (a == b-c) {
			out.printf("%d=%d-%d%n", a, b, c);
		} else 
		if (a == b*c) {
			out.printf("%d=%d*%d%n", a, b, c);
		} else 
		if (a == b/c) {
			out.printf("%d=%d/%d%n", a, b, c);
		}
	}
	
	public static void main(String[] args) {
		new Tri().go();
	}
}
