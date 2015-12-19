import java.io.*;
import java.util.*;
import java.util.regex.*;
import static java.lang.System.out;

public class Ladder {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int a = in.nextInt();
		out.println((int)(Math.ceil(h/Math.sin(Math.toRadians(a)))));
	}
	
	public static void main(String[] args) {
		new Ladder().go();
	}
}
