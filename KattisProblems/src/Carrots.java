import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class Carrots {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		out.println(in.nextInt());
	}
	
	public static void main(String[] args) {
		new Carrots().go();
	}
}
