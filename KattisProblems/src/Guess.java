import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class Guess {
	
	public void go() {
		Scanner in = new Scanner(System.in);
		String s = "";
		int low = 1;
		int high = 1001;
		while (!s.equals("correct")) {
			out.println((high+low)/2);
			s = in.nextLine();
			if (s.equals("lower")) {
				high = (high+low)/2;
			} else if (s.equals("higher")) {
				low = (high+low)/2;
			}
		}
		
//		in.close();
	}
	
	public static void main(String[] args) {
		new Guess().go();
	}
}
