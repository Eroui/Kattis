import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class ReverseBinary {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		out.println(Integer.parseInt(new StringBuilder(Integer.toBinaryString(in.nextInt())).reverse().toString(), 2));
	}
	
	public static void main(String[] args) {
		new ReverseBinary().go();
	}
}
